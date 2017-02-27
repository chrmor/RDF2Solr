package semedia.rdf2solr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.gramscilab.ner.DataTXTEntityExtractor;
import org.gramscilab.ner.AbstractNERService.PageRetrievalMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

/**
 * TODO: 
 * @author christianmorbidoni
 * creare json per timelinejs
 * scrivere automaticamente i file html e js per ajax-solr, aggiungendo le facets (?)
 * trasformare questa classse in una API REST (Michele)
 *
 */

@Path("/v1")
public class AnnotationIndex {
	
	private static final String TAG_TYPES_FACET = "tagType_ss";
	//String SOLR_SERVER = "http://localhost:8983/solr/";
	//String SOLR_SERVER = "http://gramsciproject.org:8080/solr-demo-eswc/";
	String SOLR_SERVER = "http://localhost:8080/solr-demo-eswc/";
	//String AJAX_SOLR_UPDATE_API = "http://ajaxsolr.localhost:8888/demo-eswc/php/update_faceted_browser.php";
	String AJAX_SOLR_UPDATE_API = "http://dizionario.gramsciproject.org/demo-eswc/php/update_faceted_browser.php";
	//String REPOSITORY_URL = "http://as.thepund.it:8080/openrdf-sesame/repositories/pundit";
	String REPOSITORY_URL = "http://as.thepund.it:8080/openrdf-sesame/repositories/pundit_backup";
	String FACETED_BROWSER_INDEX_FILE = "/Users/christianmorbidoni/Documents/projects/AjaxSolr/my-ajax-solr/ajax-solr/index-demo-eswc.html";
	String FACETED_BROWSER_CONF_FILE = "/Users/christianmorbidoni/Documents/projects/AjaxSolr/my-ajax-solr/ajax-solr/demo-eswc/js/demo.js";
	SolrServer solrServer = new HttpSolrServer(SOLR_SERVER);
	
	String dateFacetURL = "http://purl.org/dc/terms/date";
	String dateFacet = "Date_template_ss";
	String placeFacet = "Place_template_ss";
	String personFacet = "Person_template_ss";
	String topicFacet = "Topic_template_ss";
	String nbFacet = "Notebook_ss";
	String commentFacetURL = "http://schema.org/comment";
	private String placeFacetURL = "http://schema.org/place";
	private String personFacetURL = "http://schema.org/actor";
	private String topicFacetURL = "http://xmlns.com/foaf/0.1/topic";
	
	private void updateFacetdBrowser () throws IOException {
		URL updateAPI = new URL(AJAX_SOLR_UPDATE_API);
		updateAPI.openStream().close();;
		
//		String indexFile = Utils.readFile(FACETED_BROWSER_INDEX_FILE);
//		if (!indexFile.contains(facet)) {
//			System.out.println("Adding facet to index: " + facet);
//			indexFile = indexFile.replace("<!--Auto-facets-here-->", "<!--Auto-facets-here-->\n<p class=\"h5\">" + facetLabel +"</p>\n<div class=\"tagcloud panel-facet\" id=\"" + facet + "\"></div>\n<hr/>");
//			Utils.writeToFile(indexFile, FACETED_BROWSER_INDEX_FILE);	
//		}
//		
//		String confFile = Utils.readFile(FACETED_BROWSER_CONF_FILE);
//		if (!confFile.contains(facet)) {
//			System.out.println("Adding facet to conf: " + facet);
//			confFile = confFile.replace("/*auto-facets-mapping-here*/", ",'" + facet +"':'" + facetLabel + "'/*auto-facets-mapping-here*/");
//			confFile = confFile.replace("/*auto-facets-here*/", ",'" + facet +"'/*auto-facets-here*/");
//			confFile = confFile.replace("/*auto-facets-autocomplete-here*/", ",'" + facet +"'/*auto-facets-autocomplete-here*/");
//			confFile = confFile.replace("/*auto-facets-request-here*/", ",'" + facet +"'/*auto-facets-request-here*/");
//			Utils.writeToFile(confFile, FACETED_BROWSER_CONF_FILE);	
//		}
		
		
		
	}

	
public void indexEventTemplateAnnotations(String annotationId, RepositoryConnection conn) throws RepositoryException, MalformedQueryException, QueryEvaluationException, MalformedURLException, IOException, SolrServerException {
		
		//Query the Pundit server and retrieve annotation data
				//CASE 1: Annotations of a fragment of a page that identifies something of a given type 
				
	
				
				//String commentFacet = "Comment_ss";
				//ERROR : ?pl <http://www.w3.org/2000/01/rdf-schema#label> ?place. ?pr <http://www.w3.org/2000/01/rdf-schema#label> ?person. ?tp <http://www.w3.org/2000/01/rdf-schema#label> ?topic.
				String query = "select ?page ?item ?date ?place ?person ?topic ?comment ?notebook ?notebookId ?author where {?ann <http://purl.org/dc/terms/creator> ?au. ?au <http://xmlns.com/foaf/0.1/name> ?author. ?ann <http://purl.org/pundit/ont/ao#id> \"" + annotationId + "\". ?ann <http://purl.org/pundit/ont/ao#hasPageContext> ?page. ?ann <http://purl.org/pundit/ont/ao#isIncludedIn> ?nb. ?nb <http://www.w3.org/2000/01/rdf-schema#label> ?notebook. ?nb <http://purl.org/pundit/ont/ao#id> ?notebookId. ?ann <http://www.openannotation.org/ns/hasBody> ?graph. ?ann <http://purl.org/pundit/ont/ao#items> ?items. graph ?graph { ?x <" + dateFacetURL + "> ?date. OPTIONAL { ?x <" + placeFacetURL  + "> ?place. } OPTIONAL {?x <" + personFacetURL  + "> ?person.} OPTIONAL {?x <" + topicFacetURL  + "> ?topic.} OPTIONAL {?x <" + commentFacetURL + "> ?comment}} graph ?items { OPTIONAL {?x <http://purl.org/dc/terms/isPartOf> ?item. }} }";
				System.out.println("SPARQL QUERY: " + query);
				TupleQuery tq = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
				TupleQueryResult tqr = tq.evaluate();
				
				while (tqr.hasNext()) {
					BindingSet bs = tqr.next();
					String annotatedPage = bs.getBinding("page").getValue().stringValue();
					String originalPage = annotatedPage;
					if (annotatedPage.contains("http://feed.thepund.it")) {
						String piece = originalPage.split("[?]b=")[1];
						originalPage = URLDecoder.decode(piece.split("[&]")[0], "UTF-8"); 
					}
					String originalWebsite = originalPage.replace("http://", "").split("/")[0];
					String pageTitle = Jsoup.parse(new URL(originalPage),2000).title();
					//String pageText = Jsoup.parse(new URL(originalPage),2000).text();
					
					//System.out.println("page: " + pageTitle + "(" + originalPage + ")\nfacet: " + facet + "(" + facetURL + ")\nfacet value: " + facetValue + "(" + facetValueURL + ")");
					//System.out.println("TEXT:\n" + pageText);
					
					//solrServer.deleteByQuery( "*:*" );
					
					SolrInputDocument doc = new SolrInputDocument();
					doc.addField("id", originalPage);
					doc.addField("Source_s", originalWebsite);
					//doc.addField("content_s", pageText);
					doc.addField("title_s", pageTitle);
					
					String nb = bs.getBinding("notebook").getValue().stringValue();
					String nbid = bs.getBinding("notebookId").getValue().stringValue();
					String author = bs.getBinding("author").getValue().stringValue();
					HashMap<String, String> partialNb = new HashMap<String, String>();
					partialNb.put("add", "{\"id\":\"" + nbid + "\", \"name\":\"" + nb + "\", \"author\":\"" + author + "\"}");
					doc.addField(nbFacet, partialNb);
					
					String date = bs.getBinding("date").getValue().stringValue();
					HashMap<String, String> dateUpdate = new HashMap<String, String>();
					dateUpdate.put("set", date);
					doc.addField(dateFacet, dateUpdate);
					
					if (bs.getBinding("place")!=null) {
						String place = bs.getBinding("place").getValue().stringValue();
						HashMap<String, String> placeUpdate = new HashMap<String, String>();
						placeUpdate.put("set", place);
						doc.addField(placeFacet, placeUpdate);	
					}
					
					if (bs.getBinding("person")!=null) {
						String person = bs.getBinding("person").getValue().stringValue();
						HashMap<String, String> personUpdate = new HashMap<String, String>();
						personUpdate.put("set", person);
						doc.addField(personFacet, personUpdate);
					}
					
					if (bs.getBinding("topic")!=null) {
						String topic = bs.getBinding("topic").getValue().stringValue();
						HashMap<String, String> topicUpdate = new HashMap<String, String>();
						topicUpdate.put("set", topic);
						doc.addField(topicFacet, topicUpdate);
					}
					solrServer.add(doc);
				}
		
	}
	
//TODO: caso annotazione intera pagina?
public void indexDateTemplateAnnotations(String annotationId, RepositoryConnection conn) throws RepositoryException, MalformedQueryException, QueryEvaluationException, MalformedURLException, IOException, SolrServerException {
		
		//Query the Pundit server and retrieve annotation data
				//CASE 1: Annotations of a fragment of a page that identifies something of a given type 
				
	
				
				//String commentFacet = "Comment_ss";
				
				String query = "select ?page ?item ?date ?comment ?notebook ?notebookId ?author where {?ann <http://purl.org/dc/terms/creator> ?au. ?au <http://xmlns.com/foaf/0.1/name> ?author. ?ann <http://purl.org/pundit/ont/ao#id> \"" + annotationId + "\". ?ann <http://purl.org/pundit/ont/ao#hasPageContext> ?page. ?ann <http://purl.org/pundit/ont/ao#isIncludedIn> ?nb. ?nb <http://www.w3.org/2000/01/rdf-schema#label> ?notebook. ?nb <http://purl.org/pundit/ont/ao#id> ?notebookId. ?ann <http://www.openannotation.org/ns/hasBody> ?graph. ?ann <http://purl.org/pundit/ont/ao#items> ?items. graph ?graph { ?x <" + dateFacetURL + "> ?date. OPTIONAL {?x <" + commentFacetURL + "> ?comment}} graph ?items { ?x <http://purl.org/dc/terms/isPartOf> ?item. }}";
				System.out.println("SPARQL QUERY: " + query);
				TupleQuery tq = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
				TupleQueryResult tqr = tq.evaluate();
				
				while (tqr.hasNext()) {
					BindingSet bs = tqr.next();
					String annotatedPage = bs.getBinding("page").getValue().stringValue();
					String originalPage = annotatedPage;
					if (annotatedPage.contains("http://feed.thepund.it")) {
						String piece = originalPage.split("[?]b=")[1];
						originalPage = URLDecoder.decode(piece.split("[&]")[0], "UTF-8"); 
					}
					String originalWebsite = originalPage.replace("http://", "").split("/")[0];
					String pageTitle = Jsoup.parse(new URL(originalPage),2000).title();
					//String pageText = Jsoup.parse(new URL(originalPage),2000).text();
					
					//System.out.println("page: " + pageTitle + "(" + originalPage + ")\nfacet: " + facet + "(" + facetURL + ")\nfacet value: " + facetValue + "(" + facetValueURL + ")");
					//System.out.println("TEXT:\n" + pageText);
					
					//solrServer.deleteByQuery( "*:*" );
					
					SolrInputDocument doc = new SolrInputDocument();
					doc.addField("id", originalPage);
					doc.addField("Source_s", originalWebsite);
					//doc.addField("content_s", pageText);
					doc.addField("title_s", pageTitle);
					
					String nb = bs.getBinding("notebook").getValue().stringValue();
					String nbid = bs.getBinding("notebookId").getValue().stringValue();
					String author = bs.getBinding("author").getValue().stringValue();
					HashMap<String, String> partialNb = new HashMap<String, String>();
					partialNb.put("add", "{\"id\":\"" + nbid + "\", \"name\":\"" + nb + "\", \"author\":\"" + author + "\"}");
					doc.addField(nbFacet, partialNb);
					
					String date = bs.getBinding("date").getValue().stringValue();
					HashMap<String, String> partialUpdate = new HashMap<String, String>();
					partialUpdate.put("add", date);
					doc.addField(dateFacet, partialUpdate);
					
					solrServer.add(doc);
				}
		
	}
	
	public void indexEntitiesMentionAnnotations(String annotationId, RepositoryConnection conn) throws RepositoryException, MalformedQueryException, QueryEvaluationException, SolrServerException, IOException {
		
		//Query the Pundit server and retrieve annotation data
				//CASE 1: Annotations of a fragment of a page that identifies something of a given type 
				String query = "select ?page ?item ?p ?pLabel ?obj ?objLabel ?objType ?objTypeLabel ?notebook ?notebookId ?author ?x where {?ann <http://purl.org/dc/terms/creator> ?au. ?au <http://xmlns.com/foaf/0.1/name> ?author. ?ann <http://purl.org/pundit/ont/ao#id> \"" + annotationId + "\". ?ann <http://purl.org/pundit/ont/ao#hasPageContext> ?page. ?ann <http://purl.org/pundit/ont/ao#isIncludedIn> ?nb. ?nb <http://www.w3.org/2000/01/rdf-schema#label> ?notebook. ?nb <http://purl.org/pundit/ont/ao#id> ?notebookId. ?ann <http://www.openannotation.org/ns/hasBody> ?graph. ?ann <http://purl.org/pundit/ont/ao#items> ?items. graph ?graph { ?x ?p ?obj. } graph ?items {  ?obj <http://www.w3.org/2000/01/rdf-schema#label> ?objLabel. ?obj <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?objType. ?p <http://www.w3.org/2000/01/rdf-schema#label> ?pLabel. ?objType <http://www.w3.org/2000/01/rdf-schema#label> ?objTypeLabel OPTIONAL {?x <http://purl.org/dc/terms/isPartOf> ?item.}}}";
				System.out.println("SPARQL QUERY: " + query);
				TupleQuery tq = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
				TupleQueryResult tqr = tq.evaluate();
				
				if (!tqr.hasNext()) {
					
				}
				
				while (tqr.hasNext()) {
					BindingSet bs = tqr.next();
					String annotatedPage = bs.getBinding("page").getValue().stringValue();
					String originalPage = annotatedPage;
					if (annotatedPage.contains("http://feed.thepund.it")) {
						String piece = originalPage.split("[?]b=")[1];
						originalPage = URLDecoder.decode(piece.split("[&]")[0], "UTF-8"); 
					}
					String originalWebsite = originalPage.replace("http://", "").split("/")[0];
					String facetURL = bs.getBinding("objType").getValue().stringValue();
					String facet = bs.getBinding("objTypeLabel").getValue().stringValue().replaceAll(" ", "") + "_ss";
					String facetLabel = bs.getBinding("objTypeLabel").getValue().stringValue().replaceAll(" ", "");
					String facetValueURL = bs.getBinding("obj").getValue().stringValue();
					String facetValue = bs.getBinding("objLabel").getValue().stringValue();
					
					
					//String pageText = Jsoup.parse(new URL(originalPage),2000).text();
					
					//System.out.println("page: " + pageTitle + "(" + originalPage + ")\nfacet: " + facet + "(" + facetURL + ")\nfacet value: " + facetValue + "(" + facetValueURL + ")");
					//System.out.println("TEXT:\n" + pageText);
					
					//solrServer.deleteByQuery( "*:*" );
					
					SolrInputDocument doc = new SolrInputDocument();
					doc.addField("id", originalPage);
					doc.addField("Source_s", originalWebsite);
					//doc.addField("content_s", pageText);
					
					String pageTitle;
					try {
						Document wd = Jsoup.parse(new URL(originalPage),20000);
						pageTitle = wd.title();					
						if (pageTitle == null || pageTitle.equals("")) pageTitle = "Untitled Document";
						doc.addField("title_s", pageTitle);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String nb = bs.getBinding("notebook").getValue().stringValue();
					String nbid = bs.getBinding("notebookId").getValue().stringValue();
					String author = bs.getBinding("author").getValue().stringValue();
					HashMap<String, String> partialNb = new HashMap<String, String>();
					partialNb.put("add", "{\"id\":\"" + nbid + "\", \"name\":\"" + nb + "\", \"author\":\"" + author + "\"}");
					doc.addField(nbFacet, partialNb);
					
					HashMap<String, String> partial = new HashMap<String, String>();
					partial.put("add", facet);
					doc.addField(TAG_TYPES_FACET, partial);
					
					
					HashMap<String, String> partialUpdate = new HashMap<String, String>();
					//String jsonValue = "{" + annotationId + ":" + facetValue + "}";
					partialUpdate.put("add", facetValue);
					doc.addField(facet, partialUpdate);
					
					solrServer.add(doc);
				}
		
	}
	
	
	@POST
	@Path("index")
	public Response indexAnnotation(String annotationId) {
		System.out.println("RECEIVED ANNOTATION ID: " + annotationId);
		annotationId = annotationId.replace("{\"annotationID\":\"", "").replace("\"}", "");
		
		//Initialize the repository
		Repository rep = new HTTPRepository(REPOSITORY_URL);
		RepositoryConnection conn;
		try {
			System.out.println("Indexing annottion" + annotationId + "...");
			conn = rep.getConnection();
			indexEntitiesMentionAnnotations(annotationId, conn);
			indexEventTemplateAnnotations(annotationId, conn);
			
			solrServer.commit();
			conn.close();
			//updateFacetdBrowser();
			System.out.println("Annotation " + annotationId + " indexed in Solr.");
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		
	}
	
	@POST
	@Path("reset")
	public void resetSolr() throws SolrServerException, IOException {
		solrServer.deleteByQuery( "*:*" );
		solrServer.commit();
		//updateFacetdBrowser();
	}
	
	public static void main(String[] args) {
		AnnotationIndex ai = new AnnotationIndex();
			ai.indexAnnotation("{\"annotationID\":\"832b7761\"}");

//			try {
//				ai.resetSolr();
//			} catch (SolrServerException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}
}
