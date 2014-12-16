package semedia.rdf2solr.indexconfs;

import java.util.ArrayList;
import java.util.HashMap;

public class GramsciMediaIndexingConfigutation  extends Configuration{

	// private static final String GRAMSCI_SOLR_SERVER = "http://localhost:8983/solr/";
	// private static final String GRAMSCI_SOLR_SERVER = "http://metasound.dibet.univpm.it:8080/solr-gramsci-dictionary/";	
	private static final String GRAMSCI_SOLR_SERVER = "http://gramsciproject.org:8080/solr-gramsci-media/";
		
	private static final String GRAMSCI_SESAME_URL = "http://gramsciproject.org:8080/openrdf-sesame/repositories/";	
	private static final String GRAMSCI_SESAME_REPOSITORY_NAME = "gramsci-native";
	
	private static final String GRAMSCI_PREFIXES = 
						      "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> "
							+ "PREFIX foaf:<http://xmlns.com/foaf/0.1/> "
							+ "PREFIX owl:<http://www.w3.org/2002/07/owl#> "
							+ "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
							+ "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
							+ "PREFIX skos:<http://www.w3.org/2004/02/skos/core#> "
							+ "PREFIX :<http://purl.org/dc/elements/1.1/> "
							+ "PREFIX cito:<http://purl.org/net/cito/> "
							+ "PREFIX dcterms:<http://purl.org/dc/terms/> "
							+ "PREFIX gramsci:<http://purl.org/gramscisource/ont#> "
							+ "PREFIX dbpedia:<http://it.dbpedia.org/resource/> "
							+ "PREFIX gs:<http://gramscisource.org/> "
							+ "PREFIX oa:<http://www.openannotation.org/ns/> "
							+ "PREFIX dct:<http://purl.org/dc/terms/> "
							+ "PREFIX sesame:<http://www.openrdf.org/schema/sesame#> "
							+ "PREFIX fn:<http://www.w3.org/2005/xpath-functions> "
							+ "PREFIX dc:<http://purl.org/dc/elements/1.1/> "
							+ "PREFIX edm:<http://www.europeana.eu/schemas/edm/> ";	
	
	private static final String[] GRAMSCI_MEDIA_INDEXING_QUERIES = {
		GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri dc:description ?value. }"		
	};
	
	private static HashMap<String, String> GRAMSCI_FACET_QUERIES = new HashMap<String, String>();
	
	static {
		GRAMSCI_FACET_QUERIES.put("title_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri dct:title ?value. }");
		GRAMSCI_FACET_QUERIES.put("description_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri dc:description ?value. }");
		GRAMSCI_FACET_QUERIES.put("type_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri dc:type ?value. }");
		GRAMSCI_FACET_QUERIES.put("ctype_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri edm:type ?value. }");
		GRAMSCI_FACET_QUERIES.put("language_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri dct:language ?value. }");
		GRAMSCI_FACET_QUERIES.put("date_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri dc:date ?value. }");
		GRAMSCI_FACET_QUERIES.put("subject_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri dc:subject ?value. }");
		GRAMSCI_FACET_QUERIES.put("contributor_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri dc:contributor ?value. }");
		GRAMSCI_FACET_QUERIES.put("shownAt_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value from <http://purl.org/gramcsiproject/media/> where {?uri edm:isShownAt ?value. }");
	}
	
	private static final String[] GRAMSCI_ENTITIES_BLACK_LIST = {};
	private static ArrayList<String> GRAMSCI_TAGS_BLACK_LIST = new ArrayList<String>();	
	
	public GramsciMediaIndexingConfigutation() {
		super(GRAMSCI_SOLR_SERVER, GRAMSCI_SESAME_URL, GRAMSCI_SESAME_REPOSITORY_NAME, GRAMSCI_PREFIXES, GRAMSCI_MEDIA_INDEXING_QUERIES, GRAMSCI_FACET_QUERIES, GRAMSCI_ENTITIES_BLACK_LIST, GRAMSCI_TAGS_BLACK_LIST, false, true);
	}
	
}
