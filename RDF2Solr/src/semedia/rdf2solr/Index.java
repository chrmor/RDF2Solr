package semedia.rdf2solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

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
import semedia.rdf2solr.indexconfs.DM2EIndexingConfiguration;
import semedia.rdf2solr.indexconfs.WABOntologyIndexingConfig;

public class Index {

	
	//private static final String SOLR_SERVER = "http://metasound.dibet.univpm.it:8080/solr-dev/";
	//private static final String AJAX_SOLR_CONF_JS = "/Users/christianmorbidoni/ajax-solr-master/examples/rdf/js/reuters.js";
	//private static final String AJAX_SOLR_CONF_HTML = "/Users/christianmorbidoni/ajax-solr-master/examples/rdf/index.html";

	private static final int MAX_LENGTH_FIELDS = 4000;
	private Configuration configuration;
	private SolrServer server;
	private Repository rep;
	private HashMap<String, SolrInputDocument> solrDocs;
	
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

		Configuration conf = new DM2EIndexingConfiguration();
		Index index = new Index(conf);
		//Index index = new Index(WAB_CONFIGURATION);
		index.resetSolrIndex();
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
	 */
	private void indexByQuery(String query, RepositoryConnection conn, HashMap<String, SolrInputDocument> solrDocs, String defaultField) throws RepositoryException, MalformedQueryException, QueryEvaluationException, MalformedURLException, IOException {
	
		// Execute the query ....
		TupleQuery tquery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult res = tquery.evaluate();
		
		int id = 0;
		
		// foreach result
		while (res.hasNext()) {
		
			BindingSet bs = res.next();
			String uri = bs.getBinding("uri").getValue().stringValue();
			String field;
			
			// if no value has been matched skip ... 
			if (bs.getBinding("value") == null) continue;
			
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
			
			Value valval = bs.getBinding("value").getValue();
			String val;
			// If the value is not a RDF resource we skip some unwanted chars
			if (valval instanceof Resource) {
				if (valval.stringValue().contains("wittgensteinsource.org") || uri.contains("wittgensteinsource.org")) {
					val = truncate(valval.stringValue()).replace("_n", "").replace("_d","");		
				} else {
					val = valval.stringValue();		
				}
				
			} else {
				//val = valval.stringValue().replaceAll("\\[", "").replaceAll("\\]", "");
				val = valval.stringValue();
			}
			
			if (val.length() > MAX_LENGTH_FIELDS) {
				val = val.substring(0, MAX_LENGTH_FIELDS) + "...";
			}
			
			System.out.println(uri + " " + field + " " + val);
			
			SolrInputDocument doc;
			// Check if the document already exists. In this case add fields to the existinf document...
			if (solrDocs.containsKey(normalizeWWWUri(uri))) {
				doc = solrDocs.get(normalizeWWWUri(uri));
			} else {
				// Create a new document
				doc = new SolrInputDocument();
				// Assign an incremental ID ...
				doc.addField("id", id++);
				// Add the URI field (mandatory) ...
				doc.addField("uri_ss", normalizeWWWUri(uri));
			
				//TODO: togliere: quete informazioni vanno messe ni DATI!
				if (uri.contains("gramscisource.org")) {
					String quaderno = normalizeWWWUri(uri).split("quaderno/")[1].split("/nota")[0].replace("10a", "10").replace("10b", "10.5");
					String nota = normalizeWWWUri(uri).split("nota/")[1];
					doc.addField("quaderno_f", Float.parseFloat(quaderno));
					doc.addField("nota_i", Integer.parseInt(nota));	
				}
				
				// store the doc in the buffer ...
				solrDocs.put(normalizeWWWUri(uri), doc);
			}
			
			
			
			// The text field ha a special meaning: it contains text for the full text index.
			if (field.equals("text")) {
				doc.setField(field, val, 1.0f);
			} else if (configuration.getTags_black_list()==null || !configuration.getTags_black_list().contains(val)) {
				doc.addField(field, val, 1.0f);
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

