package semedia.rdf2solr.indexconfs;

import java.util.ArrayList;
import java.util.HashMap;

public class Configuration {
	
	private String solr_server;
	private String sesame_url;
	private String sesame_repository_name;
	private String prefixes;
	/**
	 * Adds the given SPARQL queries to the RDFtoSolr indexing chain.
	 * 
	 * Queries must have three variables:
	 * ?uri the uris of the resources to be indexed
	 * ?field the Solr field (a RDF property URI or a Literal)
	 * ?value the value of the Solr field (the URI of a resource or a Literal)
	 * 
	 * Note: in the case the ?field and ?value are URIs, their rdfs:label(if present) is used as label in Solr. 
	 * If no rdfs:label is available, the URI's local name will be used.
	 */
	private String[] indexing_queries;
	private String[] entities_black_list;
	private ArrayList<String> tags_black_list;
	private HashMap<String, String> facetQueries;
	private boolean useUrisAsIds;
	private boolean resetIndex;  
	
	public Configuration(String solr_server, String sesame_url, String sesame_repository_name, 
			String prefixes, String[] indexing_queries, HashMap<String, String> facetsQueries, String[] entities_black_list, 
			ArrayList<String> tags_black_list, boolean useUrisAsIds, boolean resetIndex) {
		
		this.solr_server = solr_server;
		this.sesame_url = sesame_url;
		this.sesame_repository_name = sesame_repository_name;
		this.prefixes = prefixes;
		this.indexing_queries = indexing_queries;
		this.facetQueries = facetsQueries;
		this.entities_black_list = entities_black_list;
		this.tags_black_list = tags_black_list;
		this.useUrisAsIds = useUrisAsIds;
		this.resetIndex = resetIndex;
		
	}
	
	public String getSolr_server() {
		return solr_server;
	}
	
	public void setSolr_server(String solr_server) {
		this.solr_server = solr_server;
	}
	
	public String getSesame_url() {
		return sesame_url;
	}
	
	public void setSesame_url(String sesame_url) {
		this.sesame_url = sesame_url;
	}
	
	public String getSesame_repository_name() {
		return sesame_repository_name;
	}
	
	public void setSesame_repository_name(String sesame_repository_name) {
		this.sesame_repository_name = sesame_repository_name;
	}
	
	public String getPrefixes() {
		return prefixes;
	}
	
	public void setPrefixes(String prefixes) {
		this.prefixes = prefixes;
	}
	
	public String[] getIndexing_queries() {
		return indexing_queries;
	}
	
	public void setIndexing_queries(String[] indexing_queries) {
		this.indexing_queries = indexing_queries;
	}
	
	public String[] getEntities_black_list() {
		return entities_black_list;
	}
	
	public void setEntities_black_list(String[] entities_black_list) {
		this.entities_black_list = entities_black_list;
	}
	
	public ArrayList<String> getTags_black_list() {
		return tags_black_list;
	}
	
	public void setTags_black_list(ArrayList<String> tags_black_list) {
		this.tags_black_list = tags_black_list;
	}

	public HashMap<String, String> getFacetQueries() {
		return facetQueries;
	}
	
	public void setFacetQueries(HashMap<String, String> facetQueries) {
		this.facetQueries = facetQueries;
	}
	public boolean getUseUrisAsIds() {
		return useUrisAsIds;
	}
	public boolean getResetIndex() {
		return resetIndex;
	}

}
