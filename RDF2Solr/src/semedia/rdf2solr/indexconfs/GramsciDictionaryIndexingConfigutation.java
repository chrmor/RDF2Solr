package semedia.rdf2solr.indexconfs;

import java.util.ArrayList;
import java.util.HashMap;

public class GramsciDictionaryIndexingConfigutation  extends Configuration{

	/**
	 * GramsciSource Dictionary configuration
	 */
	private static final String GRAMSCI_DICTIONARY_SOLR_SERVER = "http://metasound.dibet.univpm.it:8080/solr-gramsci-dictionary/";
	
	private static final String GRAMSCI_DICTINARY_SESAME_URL = "http://metasound.dibet.univpm.it:8080/openrdf-sesame/repositories/";
	private static final String GRAMSCI_DICTINARY_SESAME_REPOSITORY_NAME = "gramscisource";
	private static final String GRAMSCI_DICTINARY_PREFIXES = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> " +
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/> " +
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> " +
			"PREFIX owl:<http://www.w3.org/2002/07/owl#> " +
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#> " +
			"PREFIX :<http://purl.org/dc/terms/> ";
	private static final String[] GRAMSCI_DICTINARY_INDEXING_QUERIES = {
		GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri :description ?value. }"
		//GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?uri ?field ?value. FILTER(?field!=<http://purl.org/dc/elements/1.1/description>) FILTER (isLiteral(?value))}",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri <http://purl.org/dc/terms/description> ?value. }",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type skos:Concept. ?uri ?field ?value. FILTER(?field!=<http://purl.org/dc/terms/description>) FILTER (isLiteral(?value))}",
		//GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?uri ?field ?r. ?r rdfs:label ?value.}",
		//GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?r ?field ?uri. ?r rdfs:label ?value}",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type skos:Concept. ?uri ?field ?obj. ?obj rdfs:label ?value.}"
		 };
	
	private static HashMap<String, String> GRAMSCI_DICTINARY_FACET_QUERIES = new HashMap<String, String>();
	static {
		GRAMSCI_DICTINARY_FACET_QUERIES.put("length_i", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri <http://purl.org/gramscisource/ont#length> ?value. ?uri rdf:type skos:Concept.}");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("norm_length_s", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri <http://purl.org/gramscisource/ont#normLength> ?value. ?uri rdf:type skos:Concept. FILTER ( lang(?value) = \"it\" )}");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("cites_quaderno_ss", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri <http://purl.org/spar/cito/cites> ?c. ?c :isPartOf ?nota. ?nota :isPartOf ?quad. ?quad rdfs:label ?value.}");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("cites_nota_ss", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri <http://purl.org/spar/cito/cites> ?c. ?c :isPartOf ?nota. ?nota rdfs:label ?value.}");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("type_ss", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri rdf:type ?t. ?t rdfs:label ?value. FILTER ( ?t != skos:Concept ) FILTER ( lang(?value) = \"it\" )}");
		//GRAMSCI_DICTINARY_FACET_QUERIES.put("type_ss", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. skos:Concept rdfs:label ?value. FILTER ( lang(?value) = \"it\" ) FILTER NOT EXISTS {?uri rdf:type foaf:Person}}");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("topic_ss", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri skos:broader ?t. ?t skos:prefLabel ?value.}");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("label_s", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri skos:prefLabel ?value.}");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("length_i", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri <http://purl.org/gramscisource/ont#length> ?value. ?uri rdf:type skos:Concept.}");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("author_s", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri <http://purl.org/dc/terms/creator> ?auth. ?auth rdfs:label ?value.}");
		/*
		GRAMSCI_DICTINARY_FACET_QUERIES.put("mentions_person", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Person>.?entity rdfs:label ?value. } ");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("mentions_work", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Work>.?entity rdfs:label ?value. } ");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("mentions_place", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Place>.?entity rdfs:label ?value. } ");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("mentions_politician", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Politician>.?entity rdfs:label ?value. } ");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("mentions_book", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Book>.?entity rdfs:label ?value. } ");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("mentions_event", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Event>.?entity rdfs:label ?value. } ");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("mentions_language", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Language>.?entity rdfs:label ?value. } ");
		
		GRAMSCI_DICTINARY_FACET_QUERIES.put("cited_by", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?entry <http://purl.org/spar/cito/cites> ?text. ?entry rdfs:label ?value. } ");
		GRAMSCI_DICTINARY_FACET_QUERIES.put("mentions_type", GRAMSCI_DICTINARY_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type <http://purl.org/gramscisource/ont#Nota>. ?text :isPartOf ?uri. ?text <http://purl.org/spar/cito/mentions> ?entity. ?entity rdf:type ?value. } ");
		*/
		
	}
	
	private static final String[] GRAMSCI_DICTINARY_ENTITIES_BLACK_LIST = {"http://it.wikipedia.org/wiki/Comitato_Olimpico_Internazionale", "http://it.wikipedia.org/wiki/Cio_%28citt%C3%A0%29","http://it.wikipedia.org/wiki/ISO_639-3"};
	private static ArrayList<String> GRAMSCI_DICTINARY_TAGS_BLACK_LIST = new ArrayList<String>() {{ add("CIO"); add("ISO 639-3"); add("2C-B"); add("Annot"); add("ABMA"); add("Abio"); add("Chief Information Officer"); add("ABC"); add("Compagnia di intervento operativo");}};
	
	
	public GramsciDictionaryIndexingConfigutation() {
		super(GRAMSCI_DICTIONARY_SOLR_SERVER, GRAMSCI_DICTINARY_SESAME_URL, GRAMSCI_DICTINARY_SESAME_REPOSITORY_NAME, GRAMSCI_DICTINARY_PREFIXES, GRAMSCI_DICTINARY_INDEXING_QUERIES, GRAMSCI_DICTINARY_FACET_QUERIES, GRAMSCI_DICTINARY_ENTITIES_BLACK_LIST, GRAMSCI_DICTINARY_TAGS_BLACK_LIST);
	}
	
	
	
}
