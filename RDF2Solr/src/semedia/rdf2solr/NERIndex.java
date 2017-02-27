package semedia.rdf2solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ner.WikiLeaksMails;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.gramscilab.ner.DataTXTEntityExtractor;
import org.gramscilab.ner.NERMatch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

import semedia.rdf2solr.dbpedia.DBpediaOnt;
import semedia.rdf2solr.tests.NYTNews;

public class NERIndex {

	/**
	 * Configuration
	 * Will go to a separate file....
	 */

	String INDEX_TYPE_ELASTICSEARCH = "ElatsicSearch";
	String INDEX_TYPE_SOLR = "ApacheSolr";
	
	//The index can be of two types: Solr or ElasticSearch
	//String INDEX_TYPE = INDEX_TYPE_ELASTICSEARCH;
	String INDEX_TYPE = INDEX_TYPE_SOLR;
	
	//The URL where the index is available
	String INDEX_URL = "http://gramsciproject.org:8080/solr-leaks-auto/hb/";
	//String INDEX_URL = "http://gramsciproject.org:8080/solr-leaks-auto/news/";
	//String INDEX_URL = "http://localhost:8983/solr/";
	//String INDEX_URL = "http://localhost:9200/news/external/_solr";
	//String INDEX_URL = "http://gramsciproject.org:8080/solr-leaks-auto/leaks/";
	//String INDEX_URL = "http://gramsciproject.org:8080/solr-gramsci-auto/";
	
	Boolean RESET_INDEX_ON_RUN = false;
	
	SolrServer solrServer = new HttpSolrServer(INDEX_URL);;
	
	
	private static final String TAG_TYPES_FACET = "tagType_ss";
	private static final String TAG_CATEGORIES_FACET = "tagCategory_ss"; 
	private static final HashSet<String> CAT_BLACK_LIST =  new HashSet<String>(Arrays.asList("Article Feedback 5 Additional Articles"));

	
	
	public void indexFromNER(String pageUrl, String key, String id, ner.Document docToIndex, 
								Boolean indexTypeHierarchy, 
								Boolean indexMostSpecificTypesOnly, 
								Boolean extractTitle,
								Boolean checkEntitiesExists) throws SolrServerException, IOException {

		//If a document with the same ID is already present, just delete it and reindex
		//deleteFromSolr(pageUrl);

		DataTXTEntityExtractor ner = new DataTXTEntityExtractor();
		ner.setAppID(id);
		ner.setAPIKey(key);
		ner.setConfidence("0.8");

		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", pageUrl);

		//TODO: move this to the WikiLeaksMail class...
		String pageTitle;
		Document wd = null;
		try {
			if (extractTitle) {
				wd = Jsoup.parse(new URL(pageUrl),20000);
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if (wd==null) {
				pageTitle = pageUrl.replace("https://", "").replace("http://", "");
			} else {
				pageTitle = wd.title();	
			}
								
			if (pageTitle == null || pageTitle.equals("")) {
				if (pageUrl.contains("dl.gramsciproject.org")) {
					pageTitle = pageUrl.replace("http://dl.gramsciproject.org/quaderno/", "Q ").replace("/nota/", ", ").replace(".html", "");
					HTTPRepository rep = new HTTPRepository("http://gramsciproject.org:8080/openrdf-sesame/repositories/gramsci-native");
					RepositoryConnection connection = rep.getConnection();
					String pageDataUrl = pageUrl.replace(".html", "").replace("dl.", "data.");
					TupleQueryResult tqr = connection.prepareTupleQuery(QueryLanguage.SPARQL, "select ?title where {<" + pageDataUrl + "> <http://purl.org/dc/elements/1.1/title> ?title}").evaluate();
					if (tqr.hasNext()) pageTitle += "- " + tqr.next().getBinding("title").getValue().stringValue();
				} else {
					pageTitle = "Untitled Document";
				}
				
			};
			doc.addField("title_s", pageTitle);
			doc.addField("type_s", "document");
			doc.addField("text", docToIndex.getText());
			
			Map<String,ArrayList<String>> additionalFields = docToIndex.getAdditionalFieldsToIndex();
			if (additionalFields != null) {
				for (String field : additionalFields.keySet()) {
					doc.addField(field, additionalFields.get(field));
				}
			}
		
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		List<String> addedEntitiesDocsValues = new ArrayList<String>();
		List<String> addedTypesFacets = new ArrayList<String>();
		List<String> addedTypesFacetsValues = new ArrayList<String>();
		List<String> addedCategoriesFacets = new ArrayList<String>();
		List<String> addedCategoriesFacetsValues = new ArrayList<String>();

		List<NERMatch> matches = ner.processPage(pageUrl, docToIndex.getText(), key, id);
		if (matches==null) return;
		for (NERMatch nerMatch : matches) {

			System.out.println("Match: " + nerMatch.getLabel() + " " + nerMatch.getLodWikipediaURI() + " " + nerMatch.getConfidence());
			List<String> types = nerMatch.getTypesURI();
			List<String> paths = new ArrayList<String>();
			List<String> mst = new ArrayList<String>();
			try {
				if (indexTypeHierarchy) {
					 mst = getMostSpecificTypes(types);
					 for (String t : mst) {
						 getHierarchyPaths(t, types, 0, t.split("/")[t.split("/").length -1], true, paths);
					 }
					 types = paths;
					 System.out.println(paths);
				} else {
					if (indexMostSpecificTypesOnly) {
						types = getMostSpecificTypes(types);
					}	
					ArrayList<String> noNamepsaces = new ArrayList<String>();
					for (String type : types) {
						noNamepsaces.add(getLocalName(type));
					}
					types = noNamepsaces;
				}
				 
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedQueryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (QueryEvaluationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println("Types:");
			for (String type : types) {
				//System.out.println(type);

				String facet = "type_" + type + "_ss";
				

				//Index the entities as a solr document, linking it to its values
//				if (!(addedEntitiesDocsValues.contains(nerMatch.getLabel() + ":" + facet) || (checkEntitiesExists && existsInSolr(nerMatch.getLabel())) )) {
//					System.out.println("Indexing type " + nerMatch.getLabel() + ":" + facet);
//					SolrInputDocument facetDoc = new SolrInputDocument();
//					facetDoc.addField("id", nerMatch.getLabel());
//					facetDoc.addField("type_s", "entity");
//					HashMap<String, String> part = new HashMap<String, String>();
//					part.put("add", facet);
//					facetDoc.addField("rdf_type_ss", part);
//					solrServer.add(facetDoc);
//					addedEntitiesDocsValues.add(nerMatch.getLabel() + ":" + facet);	
//					System.out.println("Type indexed.");
//				}

				if (!addedTypesFacets.contains(facet)) {
					System.out.println("Indexing " + TAG_TYPES_FACET + " : " + facet);
					HashMap<String, String> partial = new HashMap<String, String>();
					partial.put("add", facet);
					doc.addField(TAG_TYPES_FACET, partial);
					addedTypesFacets.add(facet);
					System.out.println(TAG_TYPES_FACET + " indexed. ");
				}

				if (!addedTypesFacetsValues.contains(facet + ":" + nerMatch.getLabel())) {
					System.out.println("Indexing facet " + facet);
					HashMap<String, String> partialUpdate = new HashMap<String, String>();
					partialUpdate.put("add", nerMatch.getLabel());
					//TODO Boost this value by term occurrences (incremental)
					doc.addField(facet, partialUpdate);
					addedTypesFacetsValues.add(facet + ":" + nerMatch.getLabel());
					System.out.println("facet indexed. ");
				}



			}
			List<String> categories = nerMatch.getCategoriesURI();
			if (categories != null) {
				//System.out.println("Categories:");
				for (String category : categories) {
					//Do not consider categories that have the same name of the instance entity
					if (!category.equals(nerMatch.getLabel()) && !CAT_BLACK_LIST.contains(category)) {
						//System.out.println(category);

						String facet = "cat_" + category.replaceAll(" ", "_").replace("(", "").replace(")", "").replace("'", "’") + "_ss";

						//Index the entities as a solr document, linking it to its values
//						if (!(addedEntitiesDocsValues.contains(nerMatch.getLabel() + ":" + facet) || (checkEntitiesExists && existsInSolr(nerMatch.getLabel())) )) {
//							System.out.println("Indexing category " + nerMatch.getLabel() + ":" + facet);
//							SolrInputDocument facetDoc = new SolrInputDocument();
//							facetDoc.addField("id", nerMatch.getLabel());
//							facetDoc.addField("type_s", "entity");
//							HashMap<String, String> part = new HashMap<String, String>();
//							part.put("add", facet);
//							facetDoc.addField("wikipedia_category_ss", part);
//							solrServer.add(facetDoc);
//							addedEntitiesDocsValues.add(nerMatch.getLabel() + ":" + facet);
//							System.out.println("Category indexed.");
//						}



						if (!addedCategoriesFacets.contains(facet)) {
							System.out.println("Indexing " + TAG_CATEGORIES_FACET + " : " + facet);
							HashMap<String, String> partial = new HashMap<String, String>();
							partial.put("add", facet);
							doc.addField(TAG_CATEGORIES_FACET, partial);
							addedCategoriesFacets.add(facet);	
							System.out.println(TAG_CATEGORIES_FACET + " indexed. ");
						}

						if (!addedCategoriesFacetsValues.contains(facet + ":" + nerMatch.getLabel())) {
							System.out.println("Indexing facet " + facet);
							HashMap<String, String> partialUpdate = new HashMap<String, String>();
							partialUpdate.put("add", nerMatch.getLabel());
							doc.addField(facet, partialUpdate);
							addedCategoriesFacetsValues.add(facet + ":" + nerMatch.getLabel());
							System.out.println("facet indexed. ");
						}	
					}


				}
			} else {
				//System.out.println("NULL");
			}

		}
		System.out.println("Adding Doc to Solr");
		solrServer.add(doc);
		System.out.println("Doc added");

	}

	public void deleteFromSolr(String id) throws SolrServerException, IOException {
		solrServer.deleteByQuery("id:\"" + id +"\"" );
		solrServer.commit();
		//updateFacetdBrowser();
	}

	public Boolean existsInSolr(String id) {
		SolrQuery parameters = new SolrQuery();
		parameters.set("q", "id:\"" + id +"\"");
		Boolean response = true;
		try {
			QueryResponse res = solrServer.query(parameters);
			response = (res.getResults().size() > 0);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public void resetSolr() throws SolrServerException, IOException {
		solrServer.deleteByQuery( "*:*" );
		solrServer.commit();
		//updateFacetdBrowser();
	}

	public void commit() throws SolrServerException, IOException {
		System.out.println("Committing index");
		solrServer.commit();
		System.out.println("Index committed.");
	}

	private Map<String,HashSet<String>> parentChildren = new HashMap<String, HashSet<String>>(); 
	private Map<String,HashSet<String>> childParents = new HashMap<String, HashSet<String>>();
	//private Map<String,HashSet<String>> distinct = new HashMap<String, HashSet<String>>();

	
//	f(B, T[], i, H, write, paths)
//	if (B subclassof T[i]) 
//		H = T[i]/H
//		f(T[i], T[], 0, H, true);
//		f(B, T[], i+1, H, false);
//	else if (i < T.length)
//		f(B, T[], i+1, H, write)
//	else if (write)
//		paths.add(H)
	
	public String getLocalName(String url) {
		return url.split("/")[url.split("/").length -1];
	}
	
	public void getHierarchyPaths(String t, List<String> types, int i, String h, boolean write, List<String> paths) {
		if (isSubClass(t, types.get(i))) {
			h = getLocalName(types.get(i)) + "-SUBCLASS-" + h;
			getHierarchyPaths(types.get(i), types, 0, h, true, paths);
			if (i<types.size()-1)
				getHierarchyPaths(t, types, i+1, h, false, paths);
		} else if (i<types.size()-1) {
			getHierarchyPaths(t, types, i+1, h, write, paths);
		} else if (write) {
			paths.add(h);
		}
	}
	
	//TODO...
	public void indexHierarchyPaths(String t, List<String> types, int i, String h, boolean write, List<String> paths) {
		if (isSubClass(t, types.get(i))) {
			String docId = "type_" + getLocalName(types.get(i)) + "_ss";
			String docType = "class";
			h = getLocalName(types.get(i)) + "-SUBCLASS-" + h;
			getHierarchyPaths(types.get(i), types, 0, h, true, paths);
			if (i<types.size()-1)
				getHierarchyPaths(t, types, i+1, h, false, paths);
		} else if (i<types.size()-1) {
			getHierarchyPaths(t, types, i+1, h, write, paths);
		} else if (write) {
			paths.add(h);
		}
	}
	
	public boolean isSubClass(String t1, String t2) {
		if (parentChildren.containsKey(t2)) {
			if (parentChildren.get(t2).contains(t1)) {
				return true;
			}
		}
		if (childParents.containsKey(t1)){
			if (childParents.get(t1).contains(t2)) {
				return true;
			}
		}
		return false;
	}
	
	public List<String> getMostSpecificTypes(List<String> types) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		List<String> mostSpecificTypes = new ArrayList<String>(); 
		mostSpecificTypes.addAll(types);
		Set<String> toRemove = new HashSet<String>();

		//Use the remote DBpedia endpoint...
		//Repository rep = new HTTPRepository("http://dbpedia.org/sparql");
		//RepositoryConnection connection = rep.getConnection();
		
		//Use the local DBpedia Ontologi DUMP
		RepositoryConnection connection = DBpediaOnt.getConnection();
		
		for (String candidate : mostSpecificTypes) {
			for (String type : types) {
				boolean areDistinct = false;
//				if (distinct.containsKey(candidate)) {
//					if (distinct.get(candidate).contains(type)) {
//						areDistinct = true;
//					}
//				} 
//				if (distinct.containsKey(type)){
//					if (distinct.get(type).contains(candidate)) {
//						areDistinct = true;
//					}
//				}
				if (!type.equals(candidate) && !areDistinct) {
					if (parentChildren.containsKey(candidate)) {
						if (parentChildren.get(candidate).contains(type)) {
							toRemove.add(candidate);
							break;
						}
					}
					if (childParents.containsKey(type)){
						if (childParents.get(type).contains(candidate)) {
							toRemove.add(candidate);
							break;
						}
					}
					String query = "select ?p where {<" + type + "> ?p <" + candidate + ">}";
					System.out.println("Performing query: " + candidate + " " + type);
					TupleQuery tq = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
					TupleQueryResult tqr = tq.evaluate();
//					if (!tqr.hasNext()) {
//						if (distinct.containsKey(candidate)) {
//							distinct.get(candidate).add(type);
//						} else {
//							HashSet<String> dis = new HashSet<String>();
//							dis.add(type);
//							distinct.put(candidate, dis);
//						}
//					}
					boolean parentFound = false;
					while (tqr.hasNext()) {
						String rel = tqr.next().getBinding("p").getValue().stringValue();
						if (rel.equals("http://www.w3.org/2000/01/rdf-schema#subClassOf")) {
							toRemove.add(candidate);
							if (parentChildren.containsKey(candidate)) {
								parentChildren.get(candidate).add(type);
							} else {
								HashSet<String> children = new HashSet<String>();
								children.add(type);
								parentChildren.put(candidate, children);
							}
							if (childParents.containsKey(type)) {
								childParents.get(type).add(candidate);
							} else {
								HashSet<String> parents = new HashSet<String>();
								parents.add(candidate);
								childParents.put(type, parents);
							}
							parentFound = true;
							//break;	
						}
					}
					if (parentFound) {
						//break;
					}	


				}

			}
		}
		mostSpecificTypes.removeAll(toRemove);
		return mostSpecificTypes;
	}

	public static void main(String[] args) throws IOException {
		String key = "32d4bb5df3af7ca6f00c8ab480800ae4";
		String id = "6ad4e649";


		//String[] pages = {"http://www.theguardian.com/politics/blog/live/2015/jun/24/andy-burnham-best-placed-to-improve-labours-election-chances-poll-suggests-politics-live","http://www.theguardian.com/technology/datablog/2014/jan/14/big-data-4-predictions-for-2014", "http://www.bbc.com/news/world-asia-33148880", "http://www.bbc.com/news/world-europe-33253639", "http://dl.gramsciproject.org/quaderno/1/nota/10.html"};
		//String[] pages = {"http://www.theguardian.com/politics/blog/live/2015/jun/24/andy-burnham-best-placed-to-improve-labours-election-chances-poll-suggests-politics-live","http://www.theguardian.com/technology/datablog/2014/jan/14/big-data-4-predictions-for-2014", "http://www.bbc.com/news/world-asia-33148880", "http://www.bbc.com/news/world-europe-33253639","http://money.cnn.com/2015/06/24/technology/google-exec-death-cannes/index.html?iid=SF_LN","http://money.cnn.com/2015/06/23/technology/abortion-drone/index.html?iid=SF_LN","http://www.voanews.com/content/malaysia-finds-suspected-migrant-graves/2789230.html","http://www.voanews.com/content/british-prime-minister-cameron-announces-crack-down-on-illegal-immigration/2780766.html","http://www.theguardian.com/world/2015/may/25/china-nuclear-power-plants-expansion-he-zuoxiu","http://www.theguardian.com/technology/2015/may/26/uk-government-pcs-open-to-hackers-as-paid-windows-xp-support-ends","http://www.technewsworld.com/story/Amazon-Holds-Coming-Out-Party-for-Echos-Alexa-82206.html","http://www.alternet.org/drugs/6-psychedelic-trips-legal-drugs-you-probably-didnt-know-about","http://techcrunch.com/2015/05/28/uber-new-hq/","http://techcrunch.com/2015/06/24/gofundmes-investors-take-a-controlling-stake-in-latest-financing-round/"};
//		String[] pages = {"http://dl.gramsciproject.org/quaderno/1/nota/1.html","http://dl.gramsciproject.org/quaderno/1/nota/2.html","http://dl.gramsciproject.org/quaderno/1/nota/3.html","http://dl.gramsciproject.org/quaderno/1/nota/4.html","http://dl.gramsciproject.org/quaderno/1/nota/5.html",
//							"http://dl.gramsciproject.org/quaderno/1/nota/6.html","http://dl.gramsciproject.org/quaderno/1/nota/7.html","http://dl.gramsciproject.org/quaderno/1/nota/8.html","http://dl.gramsciproject.org/quaderno/1/nota/9.html",
//							"http://dl.gramsciproject.org/quaderno/1/nota/10.html","http://dl.gramsciproject.org/quaderno/1/nota/11.html","http://dl.gramsciproject.org/quaderno/1/nota/12.html","http://dl.gramsciproject.org/quaderno/1/nota/13.html",
//							"http://dl.gramsciproject.org/quaderno/1/nota/14.html","http://dl.gramsciproject.org/quaderno/1/nota/15.html","http://dl.gramsciproject.org/quaderno/1/nota/16.html","http://dl.gramsciproject.org/quaderno/1/nota/17.html",
//							"http://dl.gramsciproject.org/quaderno/1/nota/18.html","http://dl.gramsciproject.org/quaderno/1/nota/19.html","http://dl.gramsciproject.org/quaderno/1/nota/20.html"};

		List<String> pages = new ArrayList<String>();
//		for (int i = 1; i <= 158; i++) {
//			pages.add("http://dl.gramsciproject.org/quaderno/1/nota/" + i +".html");
//		}
//		for (int i = 1; i <= 150; i++) {
//			pages.add("http://dl.gramsciproject.org/quaderno/2/nota/" + i +".html");
//		}
//		for (int i = 1; i <= 166; i++) {
//			pages.add("http://dl.gramsciproject.org/quaderno/3/nota/" + i +".html");
//		}
//		for (int i = 0; i <= 95; i++) {
//			pages.add("http://dl.gramsciproject.org/quaderno/4/nota/" + i +".html");
//		}
//		for (int i = 1; i <= 161; i++) {
//			pages.add("http://dl.gramsciproject.org/quaderno/5/nota/" + i +".html");
//		}
//		for (int i = 1; i <= 211; i++) {
//			pages.add("http://dl.gramsciproject.org/quaderno/6/nota/" + i +".html");
//		}
//		for (int i = 0; i <= 108; i++) {
//			pages.add("http://dl.gramsciproject.org/quaderno/7/nota/" + i +".html");
//		}
//		for (int i = 0; i <= 245; i++) {
//			pages.add("http://dl.gramsciproject.org/quaderno/8/nota/" + i +".html");
//		}
		
		//pages.add("https://wikileaks.org/hackingteam/emails/emailid/165299");
		
		NERIndex ni = new NERIndex();
		
		try {
			
			if (ni.RESET_INDEX_ON_RUN) {
				ni.resetSolr();
			}
			
			
			//Index WikiLeaks Mails
//			Set<WikiLeaks.Document> mails = new HashSet<WikiLeaks.Document>();
//			//mails = WikiLeaksMails.getItems(1088500,1088600);
//			//mails = WikiLeaksMails.getItemsFromRange(165000,165010);
//			//mails = WikiLeaksMails.getItems(105100,105150);
//			
//			Set<String> urls = WikiLeaksMails.getMailUrlsFromSender("Vincenzetti", 10, 60);
//			Set<String> nonIndexedIds = new HashSet<String>();
//			for (String url : urls) {
//				if (ni.existsInSolr(url)) {
//					System.out.println("Mail " + url + " already present, skipping...");
//				} else {
//					nonIndexedIds.add(url);
//				}
//			}
//			
//			for (String url : nonIndexedIds) {
//				WikiLeaksMails.populateItems(url, mails);
//			}
//			
//			
//			for (WikiLeaks.Document doc : mails) {
//				String pageUrl = doc.getUrl();
//				System.out.println("Indexing mail " + pageUrl + "...");
//				ni.indexFromNER(pageUrl, key, id, doc, false, true, false);
//				System.out.println("Mail " + pageUrl + " indexed.");					
//			}

			
			// Index NYT news...
			
			int start = 30;
			int end = 40;
			int indexed = 0;
			int skipped = 0;
			
			int solrbuffer = 10;
			
			Set<ner.Document> news;
			
			int ys = 2015;
			int ms = 1;
			int ds = 1;
			
			Date startDate = new Date();
			int y = ys;
			for (int m = ms;m <= 10; m += 1 ) {
				for (int d = ds;d <= 31; d += 1) {
					int dateStart = y * 10000 + m * 100 + d;
					try {
						news = NYTNews.getNYTNews(start, end, null,Integer.toString(dateStart));
					} catch (IOException e) {
						e.printStackTrace();
						continue;
					}
					System.out.println("Ready to process " + news.size() + " documents...");
					for (ner.Document doc : news) {
						String url = doc.getUrl();
						if (ni.existsInSolr(url)) {
							System.out.println("Skipping item " + url + " already indexed.");
							skipped ++;
						} else {
							System.out.println("Indexing item " + url + " ...");
							try {
								ni.indexFromNER(url, key, id, doc, false, true, true, false);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							indexed++;
							if (indexed % solrbuffer == 0) {
								ni.commit();
							}
						}
					}
				}
			}
			
			Date endDate = new Date();
			
			//Index a page
//			for (String pageUrl : pages) {
//				ni.indexFromNER(pageUrl, key, id, null, false, false, false);	
//			}
			if (indexed % solrbuffer != 0) ni.commit();
			System.out.println(indexed + " documents indexed (" + skipped + " skipped) in " + (endDate.getTime()-startDate.getTime()) + " milleseconds.");
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
