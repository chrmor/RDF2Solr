package semedia.rdf2solr.indexconfs;

import java.util.ArrayList;
import java.util.HashMap;

public class GramsciSourceIndexingConfiguration extends Configuration {

	/**
	 * GramsciSource configuration
	 */
	private static final String SOLR_SERVER = "http://localhost:8983/solr/";
	private static final String GRAMSCI_SESAME_URL = "http://metasound.dibet.univpm.it:8080/openrdf-sesame/repositories/";
	private static final String GRAMSCI_SESAME_REPOSITORY_NAME = "gramscisource";
	private static final String GRAMSCI_PREFIXES = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> " +
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/> " +
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> " +
			"PREFIX owl:<http://www.w3.org/2002/07/owl#> " +
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#> " +
			"PREFIX :<http://purl.org/dc/terms/> ";
	private static final String[] GRAMSCI_INDEXING_QUERIES = {
		

		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri <http://purl.org/spar/cito/mentions> ?entity. ?entity ?field ?value. FILTER (?field=<http://purl.org/dc/elements/1.1/type>)}",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri ?field ?value. FILTER (?value=<http://purl.org/gramscisource/ont#Nota> || ?value=skos:Concept) FILTER (?field=rdf:type)}",
		GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?uri <http://purl.org/dc/elements/1.1/description> ?value. }",
		GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?uri ?field ?value. FILTER(?field!=<http://purl.org/dc/elements/1.1/description>) FILTER (isLiteral(?value))}",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri <http://purl.org/dc/terms/description> ?value. }",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type skos:Concept. ?uri ?field ?value. FILTER(?field!=<http://purl.org/dc/terms/description>) FILTER (isLiteral(?value))}",
		GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?uri ?field ?r. ?r rdfs:label ?value.}",
		GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?r ?field ?uri. ?r rdfs:label ?value}",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type skos:Concept. ?uri ?field ?obj. ?obj rdfs:label ?value.}"
		 };
	
	private static HashMap<String, String> GRAMSCI_FACET_QUERIES = new HashMap<String, String>();
	static {
		GRAMSCI_FACET_QUERIES.put("mentions", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_person", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Person>.?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_work", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Work>.?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_place", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Place>.?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_politician", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Politician>.?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_book", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Book>.?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_event", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Event>.?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_language", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Language>.?entity rdfs:label ?value. } ");
		
		GRAMSCI_FACET_QUERIES.put("cited_by", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?entry <http://purl.org/spar/cito/cites> ?text. ?entry rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_type", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type ?value. } ");
		
		
	}
	
	private static final String[] GRAMSCI_ENTITIES_BLACK_LIST = {"http://it.wikipedia.org/wiki/Comitato_Olimpico_Internazionale", "http://it.wikipedia.org/wiki/Cio_%28citt%C3%A0%29","http://it.wikipedia.org/wiki/ISO_639-3"};
	private static ArrayList<String> GRAMSCI_TAGS_BLACK_LIST = new ArrayList<String>() {{ add("CIO"); add("ISO 639-3"); add("2C-B"); add("Annot"); add("ABMA"); add("Abio"); add("Chief Information Officer"); add("ABC"); add("Compagnia di intervento operativo");}};
	
	public GramsciSourceIndexingConfiguration() {
		super(SOLR_SERVER, GRAMSCI_SESAME_URL, GRAMSCI_SESAME_REPOSITORY_NAME, GRAMSCI_PREFIXES, GRAMSCI_INDEXING_QUERIES, GRAMSCI_FACET_QUERIES, GRAMSCI_ENTITIES_BLACK_LIST, GRAMSCI_TAGS_BLACK_LIST, false, true);
	}
	
	
	
}
