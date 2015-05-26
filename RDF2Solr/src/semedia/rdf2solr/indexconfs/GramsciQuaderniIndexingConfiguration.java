	package semedia.rdf2solr.indexconfs;

import java.util.ArrayList;
import java.util.HashMap;

public class GramsciQuaderniIndexingConfiguration extends Configuration {

	/**
	 * GramsciSource configuration
	 */
	// private static final String SOLR_SERVER = "http://localhost:8983/solr/";
	//private static final String GRAMSCI_SESAME_URL = "http://metasound.dibet.univpm.it:8080/openrdf-sesame/repositories/";
	
	private static final String SOLR_SERVER = "http://gramsciproject.org:8080/solr-gramsci-quaderni/";	
	private static final String GRAMSCI_SESAME_URL = "http://gramsciproject.org:8080/openrdf-sesame/repositories/";		
	private static final String GRAMSCI_SESAME_REPOSITORY_NAME = "gramsci-native";
	
	private static final String GRAMSCI_PREFIXES = 
							      "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
								+ "PREFIX skos:<http://www.w3.org/2004/02/skos/core#> "
								+ "PREFIX cito: <http://purl.org/spar/cito/> "							
								+ "PREFIX gramsci: <http://purl.org/gramsciproject/vocab/> "
								+ "PREFIX dct: <http://purl.org/dc/terms/> "
								+ "PREFIX dcterms: <http://purl.org/dc/terms/> "
								+ "PREFIX edm: <http://www.europeana.eu/schemas/edm/> "
								+ "PREFIX dc: <http://purl.org/dc/elements/1.1/>";
									
	private static final String[] GRAMSCI_INDEXING_QUERIES = {	
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri <http://purl.org/spar/cito/mentions> ?entity. ?entity ?field ?value. FILTER (?field=<http://purl.org/dc/elements/1.1/type>)}",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri ?field ?value. FILTER (?value=<http://purl.org/gramscisource/ont#Nota> || ?value=skos:Concept) FILTER (?field=rdf:type)}",
		GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri dct:type gramsci:Nota. ?uri <http://purl.org/dc/elements/1.1/description> ?value. }",
		
		// - GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri dct:type gramsci:Nota. ?uri ?field ?value. FILTER(?field!=<http://purl.org/dc/elements/1.1/description>) FILTER (isLiteral(?value))}",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri rdf:type skos:Concept. ?uri <http://purl.org/dc/terms/description> ?value. }",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type skos:Concept. ?uri ?field ?value. FILTER(?field!=<http://purl.org/dc/terms/description>) FILTER (isLiteral(?value))}",
		
		// - GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri dct:type gramsci:Nota. ?uri ?field ?r. ?r rdfs:label ?value.}",
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri dcterms:type <http://purl.org/gramsciproject/vocab/Nota>. ?r ?field ?uri. ?r rdfs:label ?value}"
		//GRAMSCI_PREFIXES + "select distinct ?uri ?field ?value where {?uri rdf:type skos:Concept. ?uri ?field ?obj. ?obj rdfs:label ?value.}"
	};
	
	private static HashMap<String, String> GRAMSCI_FACET_QUERIES = new HashMap<String, String>();
	static {
		GRAMSCI_FACET_QUERIES.put("mentions_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { ?uri dct:type gramsci:Nota. ?text dcterms:isPartOf ?uri. ?text cito:discusses ?entity. ?entity rdfs:label ?value. }");
		GRAMSCI_FACET_QUERIES.put("mentions_person_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/ner/entities/> { ?text dcterms:isPartOf ?uri. ?text cito:discusses ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Person>. ?entity rdfs:label ?value. } }");
		GRAMSCI_FACET_QUERIES.put("mentions_work_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { ?uri dct:type gramsci:Nota. ?text dct:isPartOf ?uri. ?text cito:discusses ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Work>.?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_place_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { ?uri dct:type gramsci:Nota. ?text dct:isPartOf ?uri. ?text cito:discusses ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Place>. ?entity rdfs:label ?value. }  ");
		GRAMSCI_FACET_QUERIES.put("mentions_politician_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { ?uri dct:type gramsci:Nota. ?text dct:isPartOf ?uri. ?text cito:discusses ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Politician>. ?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_book_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { ?uri dct:type gramsci:Nota. ?text dct:isPartOf ?uri. ?text cito:discusses ?entity. ?entity rdf:type <http://dbpedia.org/ontology/WrittenWork>.?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_event_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { ?uri dct:type gramsci:Nota. ?text dct:isPartOf ?uri. ?text cito:discusses ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Event>. ?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_language_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { ?uri dct:type gramsci:Nota. ?text dct:isPartOf ?uri. ?text cito:discusses ?entity. ?entity rdf:type <http://dbpedia.org/ontology/Language>. ?entity rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("cited_by_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri dct:type <http://purl.org/gramsciproject/vocab/Nota>. ?text dct:isPartOf ?uri. ?entry <http://purl.org/spar/cito/cites> ?text. ?entry rdfs:label ?value.} ");		
		GRAMSCI_FACET_QUERIES.put("mentions_type_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { ?uri dct:type gramsci:Nota. ?text dct:isPartOf ?uri. ?text cito:discusses ?entity. ?entity rdf:type ?value. } ");
		GRAMSCI_FACET_QUERIES.put("mentions_subject_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { ?uri dct:type gramsci:Nota. ?text dct:isPartOf ?uri. ?text cito:discusses ?entity. ?entity dc:type ?value. } ");
		GRAMSCI_FACET_QUERIES.put("label_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri dct:type gramsci:Nota. ?uri rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("label_tpl", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri dct:type gramsci:Nota. ?uri rdfs:label ?value. } ");
		GRAMSCI_FACET_QUERIES.put("topic_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri dct:type <http://purl.org/gramsciproject/vocab/Nota>. ?topic <http://purl.org/net/cito/isDiscussedBy> ?uri. ?topic rdf:type skos:Concept. ?topic rdfs:label ?value }");
		GRAMSCI_FACET_QUERIES.put("isShownAt_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri dct:type <http://purl.org/gramsciproject/vocab/Nota>. ?uri edm:isShownAt ?value.}");
		GRAMSCI_FACET_QUERIES.put("quaderno_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value where {?uri dct:type <http://purl.org/gramsciproject/vocab/Nota>. ?uri dct:isPartOf ?q. ?q dct:type gramsci:Quaderno. ?q rdfs:label ?value.}");
		GRAMSCI_FACET_QUERIES.put("nome_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/annotations> { ?part dct:isPartOf ?dluri. ?part <http://purl.org/gramsciproject/vocab/corresponsTo> ?v. } graph <http://data.gramsciproject.org/nomi/> { ?v skos:prefLabel ?value. } ?uri edm:isShownAt ?dluri. ?uri dct:type gramsci:Nota. }");
	}	
	
	
		
		
	
	private static final String[] GRAMSCI_ENTITIES_BLACK_LIST = {"http://it.wikipedia.org/wiki/Comitato_Olimpico_Internazionale", "http://it.wikipedia.org/wiki/Cio_%28citt%C3%A0%29","http://it.wikipedia.org/wiki/ISO_639-3"};
	private static ArrayList<String> GRAMSCI_TAGS_BLACK_LIST = new ArrayList<String>() {{ add("CIO"); add("ISO 639-3"); add("2C-B"); add("Annot"); add("ABMA"); add("Abio"); add("Chief Information Officer"); add("ABC"); add("Compagnia di intervento operativo");}};
	
	public GramsciQuaderniIndexingConfiguration() {
		super(SOLR_SERVER, GRAMSCI_SESAME_URL, GRAMSCI_SESAME_REPOSITORY_NAME, GRAMSCI_PREFIXES, GRAMSCI_INDEXING_QUERIES, GRAMSCI_FACET_QUERIES, null, null, null, GRAMSCI_ENTITIES_BLACK_LIST, GRAMSCI_TAGS_BLACK_LIST, false, true);
	}		
	
}
