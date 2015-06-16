package semedia.rdf2solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.openrdf.model.Resource;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

import semedia.rdf2solr.indexconfs.Configuration;
import semedia.rdf2solr.indexconfs.GramsciNomiIndexingConfiguration;

public class Index {

	
	//private static final String SOLR_SERVER = "http://metasound.dibet.univpm.it:8080/solr-dev/";
	//private static final String AJAX_SOLR_CONF_JS = "/Users/christianmorbidoni/ajax-solr-master/examples/rdf/js/reuters.js";
	//private static final String AJAX_SOLR_CONF_HTML = "/Users/christianmorbidoni/ajax-solr-master/examples/rdf/index.html";

	private static final int MAX_LENGTH_FIELDS = 4000;
	private Configuration configuration;
	private SolrServer server;
	private Repository rep;
	private HashMap<String, SolrInputDocument> solrDocs;
	private int startingIndex = 0; 
	
	public Index(Configuration configuration) {
		this.solrDocs = new HashMap<String, SolrInputDocument>();
		this.configuration = configuration;
		this.server = new HttpSolrServer(configuration.getSolr_server());
		this.rep = new HTTPRepository(configuration.getSesame_url() + configuration.getSesame_repository_name());
		
	}
	
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
		this.server = new HttpSolrServer(configuration.getSolr_server());
		this.rep = new HTTPRepository(configuration.getSesame_url() + configuration.getSesame_repository_name());
	}
	
	public void resetSolrIndex() throws SolrServerException, IOException {
		this.server.deleteByQuery( "*:*" );
	}
	
	
	
	public static void main(String[] args) throws RepositoryException, QueryEvaluationException, MalformedQueryException, IOException, SolrServerException {

		
		//Configuration conf = new DM2EIndexingConfiguration();
		//Configuration conf = new GramsciQuaderniIndexingConfiguration();
		//Configuration conf = new GramsciMediaIndexingConfigutation();
		Configuration conf = new GramsciNomiIndexingConfiguration();
		
		//Configuration conf = new GramsciDictionaryIndexingConfigutation();
		Index index = new Index(conf);
		//Index index = new Index(WAB_CONFIGURATION);
		if (index.configuration.getResetIndex()) {
			index.resetSolrIndex();
		}
		
//		SolrQuery query = new SolrQuery();
//		SolrDocumentList res;
//		do {
//			query.setQuery( "id:" + index.startingIndex );
//		    res = index.server.query( query ).getResults();
//		    index.startingIndex++;
//		} while (!res.isEmpty());
		
	    
	    
		System.out.println("Building index...");
		index.doIndex();
		System.out.println("Index built.");
		//index.setConfiguration(ANN_GRAMSCI_CONFIGURATION);
		//index.doIndex();
		System.out.println("Committing index...");
		index.commit();
		System.out.println("Index committed.");
		
	}
	
	
	private void commit() throws SolrServerException, IOException {
		if (this.solrDocs.values().size()>0) server.add(this.solrDocs.values());
		server.commit();
		
//		SolrQuery sq = new SolrQuery();
//	    sq.setQuery( "*:*" );
//	    QueryResponse rsp = server.query( sq );
//	    SolrDocumentList docs = rsp.getResults();
//	    
//	    Iterator<SolrDocument> iter = docs.iterator();
//	    while (iter.hasNext()) {
//	    	System.out.println(iter.next().toString());
//	    }

	}
	
	public void doIndex() throws RepositoryException, MalformedQueryException, QueryEvaluationException, SolrServerException, IOException {
		
				RepositoryConnection conn = rep.getConnection();
				
				for (String q : configuration.getIndexing_queries()) {
					System.out.println("Query:\n" + q);
					indexByQuery(q, conn, solrDocs, "text");
				}
				
				if (configuration.getFacetQueries() != null) {
					for (String field : configuration.getFacetQueries().keySet()) {
						String q = configuration.getFacetQueries().get(field);
						System.out.println("Facet Query for field :\n" + field + "\n" + q);
						indexByQuery(q, conn, solrDocs, field);
					}
				}
				
				
				//PUNDIT ONLY DO NOT MODIFY
				if (configuration.getPunditQueries() != null) {
					for (String endpoint : configuration.getPunditQueries().keySet()) {
						Repository rep = new HTTPRepository(endpoint);
						RepositoryConnection connection = rep.getConnection();
						HashMap<String, ArrayList<String>> facetQueries = configuration.getPunditQueries().get(endpoint);
						for (String field : facetQueries.keySet()) {
							ArrayList<String> qs = facetQueries.get(field);
							for (String q : qs) {
								System.out.println("Facet Query for field :\n" + field + "\n" + q);
								TupleQuery tq = connection.prepareTupleQuery(QueryLanguage.SPARQL, q);
								TupleQueryResult tqr = tq.evaluate();
								while (tqr.hasNext()) {
									BindingSet bs = tqr.next();
									String uri = ((Resource)bs.getBinding("uri").getValue()).stringValue();
									//Hack (BUR data sucks!)
									if (uri.startsWith("http://burckhardtsource.org")) {
										uri = uri.replaceAll("letter","api.php/tc");
									}
									Value value = bs.getBinding("value").getValue();
									Set<String> punditFacetsQueries = configuration.getPunditFacetsQueries();
									for (String query : punditFacetsQueries) {
										query = query.replaceAll("annotatedObject", "<" + uri + ">");
										System.out.println(query);
										indexByQuery(query, conn, solrDocs, field, value.stringValue());
									}
									HashMap<String, String> annotatedSubItemsQueries = configuration.getAnnotatedSubitemsQueries();
									for (String annotatedSubitemsField : annotatedSubItemsQueries.keySet()) {
										String asq = annotatedSubItemsQueries.get(annotatedSubitemsField);
										asq = asq.replaceAll("annotatedObject", "<" + uri + ">");
										indexByQuery(asq, conn, solrDocs, annotatedSubitemsField, "{\"subItem\":\"SUBITEMURI\",\"annotation\":" + value + "}");
									}
								}	
							}
						}
					}
					
				} 
				
				//Add missing text fields to indexed documents. This is needed to appropriately display them in Ajax-Solr
				for (SolrInputDocument doc : solrDocs.values()) {
					if (doc.getField("text") == null) {
						String uri = (String)doc.getField("uri_ss").getValue();
						//if (uri.contains("wittgensteinsource.org")) {
						if (false) {
							String sigla = uri.split("/")[uri.split("/").length - 1];
							String escSigla = URLEncoder.encode(sigla,"UTF-8");
							uri = uri.replace(sigla, escSigla);
							String text = getNoteText(uri);
							if (text != null) {
								text = text.replaceAll("\t", "");
								doc.setField("text", text, 1.0f);	
							}
							
						} else {
							doc.setField("text", "NO DESCRIPTION AVAILABLE FOR THIS ITEM");	
						}
						
					}
				}
				

	}
	
	/**
	 * Given a SPARQL query with at least a ?uri and a ?value variables are binded, 
	 * indexes the Documents identified by each match of the ?uri variable, 
	 * adding fields equals to each match of the ?field variable and corresponding values, 
	 * matched by the ?value variable.
	 * If no matches are found for the ?field variable, the default field is used, if provided.
	 * @param query The SPARQL query
	 * @param conn a connection to a Sesame Repository
	 * @param solrDocs A set of Solr Input Documents to be updated with the new fields
	 * @param defaultField an optional default field
	 * @throws RepositoryException
	 * @throws MalformedQueryException
	 * @throws QueryEvaluationException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws SolrServerException 
	 */
	private void indexByQuery(String query, RepositoryConnection conn, HashMap<String, SolrInputDocument> solrDocs, String defaultField) throws RepositoryException, MalformedQueryException, QueryEvaluationException, MalformedURLException, IOException, SolrServerException {
		indexByQuery(query, conn, solrDocs, defaultField, null); 
	}
	
	private void indexByQuery(String query, RepositoryConnection conn, HashMap<String, SolrInputDocument> solrDocs, String defaultField, String defaultValue) throws RepositoryException, MalformedQueryException, QueryEvaluationException, MalformedURLException, IOException, SolrServerException {
	
		// Execute the query ....
		TupleQuery tquery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult res = tquery.evaluate();
		
		// foreach result
		while (res.hasNext()) {
		
			BindingSet bs = res.next();
			String uri = bs.getBinding("uri").getValue().stringValue();
			String field;
			
			// if no value has been matched skip ... 
			if (defaultValue==null && bs.getBinding("value") == null) continue;
			
			// if the ?field variable has a match ... 
			if (bs.getBinding("field")!=null) {
				Value fv = bs.getBinding("field").getValue();
				// if the value is a RDF resource we treat it accordingly by checking for rdf:type and adding a special field
				if (fv instanceof Resource) {
					if (fv.stringValue().equals("http://purl.org/dc/elements/1.1/type")) {
						field = "topic_ss";	
					} else {
						field = truncate(fv.stringValue()) + "_ss";	
					}
						
				} else {
					field = fv.stringValue() + "_ss";
				}
			
			// Else if the default filed is provided use it instead ...
			} else if (defaultField != null){
				field = defaultField;
			} else {
				return;
			}
			
			field = field.replaceAll(" ", "_");
			
			
			
			String val;
			if (defaultValue != null) {
				
				if (bs.getBinding("subItem")!=null) {
					//USED IN DM2E - DO NOT MODIFY
					String subItemUri = bs.getBinding("subItem").getValue().stringValue();
					val = defaultValue.replaceAll("SUBITEMURI", subItemUri);
				} else {
					val = defaultValue;
				}
				
			} else {
				Value valval = bs.getBinding("value").getValue();
				// If the value is not a RDF resource we skip some unwanted chars
				if (valval instanceof Resource) {
						val = valval.stringValue();		
				} else {
					//val = valval.stringValue().replaceAll("\\[", "").replaceAll("\\]", "");
					val = valval.stringValue();
				}	
			}
			
			
			if (val.length() > MAX_LENGTH_FIELDS) {
				val = val.substring(0, MAX_LENGTH_FIELDS) + "...";
			}
			
			System.out.println(uri + " " + field + " " + val);
			
			SolrInputDocument doc;
			// Check if the document already exists. In this case add fields to the existing document...
			if (solrDocs.containsKey(normalizeWWWUri(uri))) {
				doc = solrDocs.get(normalizeWWWUri(uri));
			} else {
				// Create a new document
				doc = new SolrInputDocument();
				// Assign an incremental ID ...				
				if (configuration.getUseUrisAsIds()) {
					doc.addField("id", uri);
				} else {
					doc.addField("id", startingIndex++);
				}
				
				
				
				// Add the URI field (mandatory) ...
				doc.addField("uri_ss", normalizeWWWUri(uri));
			
				//TODO: togliere: queste informazioni vanno messe ni DATI!
				if (uri.contains("gramsciproject.org/quaderni")) {
					String quaderno = normalizeWWWUri(uri).split("quaderno/")[1].split("/nota")[0].replace("10-II", "10.5");
					quaderno = quaderno.replace("10-I", "10");
					String nota = normalizeWWWUri(uri).split("nota/")[1].split("%20")[0];
					doc.addField("quaderno_f", Float.parseFloat(quaderno));
					doc.addField("nota_i", Integer.parseInt(nota));	
				}
				
				// store the doc in the buffer ...
				solrDocs.put(normalizeWWWUri(uri), doc);
			}
			
			
			
			// The text field has a special meaning: it contains text for the full text index.
			if (field.equals("text")) {
				doc.setField(field, val, 1.0f);
			} else if (configuration.getTags_black_list()==null || !configuration.getTags_black_list().contains(val)) {
				
				//IF EXISTS ?count THEN val = {"value":val, "count":?count}
				if (bs.getBinding("count")!=null) {
					String count = bs.getBinding("count").getValue().stringValue();
					val = "{\"value\":\"" + val + "\",\"count\":\"" + count + "\"}";
				}
				
				if (field.endsWith("_s")) {
					doc.setField(field, val, 1.0f);
				} else {
					doc.addField(field, val, 1.0f);
				}
				
			}
			
		}
	}
	

	
	private String normalizeWWWUri(String uri) {
		if (uri.contains("www.")) {
			return uri.replace("www.gramscisource.org","gramscisource.org");	
		} else {
			return uri;
		}
		
	}

	private String truncate(String uri) {
		String trunc;
		if (uri.contains("#")) {
			trunc = "#";
		} else {
			trunc = "/";
		}
		
		String[] pieces = uri.split(trunc);
		
		return pieces[pieces.length -1];
	}

	
	
	private HashMap<String, String> notesTextsCache = new HashMap<String, String>();

	public String getNoteText(String uri) throws HttpException, IOException {

		if (notesTextsCache.containsKey(uri)) {
			System.out.println("*******************ALREADY PRESENT, GETTING CACHED TEXT....***************");
			return notesTextsCache.get(uri);
		} else {
			String url = uri + ".html";
			String text = "";

			HttpClient client = new HttpClient();
			GetMethod get = new GetMethod(url);
			client.executeMethod(get);
			System.out.println(url + "STATUS: " + get.getStatusCode());
			if (get.getStatusCode()==200) {
				String html = get.getResponseBodyAsString();
				html = html.split("<body>")[1].split("<script>")[0];
				text = Utils.stripHTMLTags(html).replaceAll("\n", " ").replaceAll("  ", "").replaceAll(" � ", "");

				notesTextsCache.put(uri, text);
				return text;	
			} else {
				return null;
			}
			
		}

	} 
	
}

