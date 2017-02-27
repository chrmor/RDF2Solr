package semedia.rdf2solr.indexconfs;

import java.util.ArrayList;
import java.util.HashMap;



public class WABOntologyIndexingConfig extends Configuration{
	
	
	/**
	 * WAB configuration
	 */
	
	private static final String[] WAB_ENTITIES_BLACK_LIST = {};
	private static final String WAB_SESAME_URL = "http://gramsciproject.org:8080/openrdf-sesame/repositories/";
	//private static final String WAB_SESAME_URL = "http://metasound.dibet.univpm.it:8080/openrdf-sesame/repositories/";
	private static final String WAB_SESAME_REPOSITORY_NAME = "wab";
	private static final String WAB_SOLR_URL = "http://141.20.126.236:8080/solr-wab2/"; 
	private static ArrayList<String> WAB_TAGS_BLACK_LIST = new ArrayList<String>() {};	
	
	private static final String WAB_PREFIXES = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
	+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
	+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
	+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
	+ "PREFIX gramsci: <http://purl.org/gramsciproject/vocab/>"
	+ "PREFIX dc: <http://purl.org/dc/elements/1.1/>"
	+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
	+ "PREFIX dct: <http://purl.org/dc/terms/>"
	+ "PREFIX edm: <http://www.europeana.eu/schemas/edm/>"
	+ "PREFIX scho: <http://purl.org/wittgensteinsource/ont/scholar/0.1/>"
	+ "PREFIX :<http://purl.org/wittgensteinsource/ont/>";
	
//	private static final String WAB_PREFIXES = "PREFIX scho:<http://purl.org/scho/ont/>\n" + 
//			"PREFIX :<http://purl.org/wittgensteinsource/ont/>\n" + 
//			"PREFIX xsl:<http://www.w3.org/1999/XSL/Transform>\n" + 
//			"PREFIX protege:<http://protege.stanford.edu/plugins/owl/protege#>\n" + 
//			"PREFIX tei:<http://www.tei-c.org/ns/1.0>\n" + 
//			"PREFIX xsp:<http://www.owl-ontologies.com/2005/08/07/xsp.owl#>\n" + 
//			"PREFIX xs:<http://www.w3.org/2001/XMLSchema>\n" + 
//			"PREFIX inst:<http://purl.org/wittgensteinsource/ont/instances/>\n" + 
//			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n" + 
//			"PREFIX swrl:<http://www.w3.org/2003/11/swrl#>\n" + 
//			"PREFIX wgs:<http://www.wittgensteinsource.org/>\n" + 
//			"PREFIX flub:<http://ub.uib.no/flibub>\n" + 
//			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\n" + 
//			"PREFIX owl:<http://www.w3.org/2002/07/owl#>\n" + 
//			"PREFIX swrlb:<http://www.w3.org/2003/11/swrlb#>\n" + 
//			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
	
	private static final String[] WAB_INDEXING_QUERIES = {
		
		//Brown book only query
		//WAB_PREFIXES + "select distinct ?uri ?field ?value where {GRAPH <http://wab.uib.no/ontology> {  FILTER (contains(str(?uri),'Ts-310')) ?uri ?field ?value. FILTER (isLiteral(?value)) }}",
		//WAB_PREFIXES + "select distinct ?uri ?field ?value where {GRAPH <http://wab.uib.no/ontology> {  FILTER (contains(str(?uri),'Ts-310')) ?uri ?f ?v. ?v rdfs:label ?value. ?f rdfs:label ?field. }}"
		//Bemerkungs only query	
		WAB_PREFIXES + "select distinct ?uri ?field ?value where {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type scho:SecondarySource. ?uri ?field ?value. FILTER (isLiteral(?value)) }}",
		WAB_PREFIXES + "select distinct ?uri ?field ?value where {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type scho:SecondarySource. ?uri ?f ?v. ?v rdfs:label ?value. ?f rdfs:label ?field. }}",
		WAB_PREFIXES + "select distinct ?uri ?field ?value where {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type :Bemerkung. ?uri ?field ?value. FILTER (isLiteral(?value)) }}",
		WAB_PREFIXES + "select distinct ?uri ?field ?value where {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type :Bemerkung. ?uri ?f ?v. ?v rdfs:label ?value. ?f rdfs:label ?field. }}"
		
//		WAB_PREFIXES + "select distinct ?uri ?field ?value where {?notebook rdf:type <http://purl.org/pundit/ont/ao#Notebook>. ?notebook rdfs:label ?label. FILTER (regex(str(?label),'WAB','i')) ?notebook dcterms:creator ?author. ?notebook <http://purl.org/pundit/ont/ao#includes> ?annotation. ?annotation <http://www.openannotation.org/ns/hasBody> ?graph. ?annotation <http://purl.org/pundit/ont/ao#items> ?items. \n" + 
//				"GRAPH ?graph {\n" + 
//				"    ?frag ?f ?v.\n" + 
//				"}\n" + 
//				"GRAPH ?items {\n" + 
//				"    ?frag dcterms:isPartOf ?uri. " +
//				"    ?v rdfs:label ?value.\n" + 
//				"    ?f rdfs:label ?field.\n" + 
//				"    FILTER NOT EXISTS {?v dcterms:isPartOf ?whole.}\n" + 
//				"}\n" + 
//				"}",
//		WAB_PREFIXES + "select distinct ?uri ?field ?value where {?notebook rdf:type <http://purl.org/pundit/ont/ao#Notebook>. ?notebook rdfs:label ?label. FILTER (regex(str(?label),'WAB','i')) ?notebook dcterms:creator ?author. ?notebook <http://purl.org/pundit/ont/ao#includes> ?annotation. ?annotation <http://www.openannotation.org/ns/hasBody> ?graph. ?annotation <http://purl.org/pundit/ont/ao#items> ?items. \n" + 
//				"GRAPH ?graph {\n" + 
//				"    ?frag ?f ?v.\n" + 
//				"}\n" + 
//				"GRAPH ?items {\n" + 
//				"    ?v dcterms:isPartOf ?value.\n" +
//				"    ?frag dcterms:isPartOf ?uri.\n" + 
//				"    ?f rdfs:label ?field.\n" + 
//				"}\n" + 
//				"\n" + 
//				"}"		
	 };
	
	private static HashMap<String, String> WAB_FACET_QUERIES = new HashMap<String, String>();
	static {
		WAB_FACET_QUERIES.put("facet_published_in_ss", WAB_PREFIXES + "select distinct ?uri ?value where { {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type ?type. FILTER (?type = scho:SecondarySource || ?type = :Bemerkung) ?v :isWorkPublishedFrom ?uri. ?v rdfs:label ?value. }} UNION {GRAPH <http://wab.uib.no/ontology> {  ?uri :isPublishedInWork ?v. ?v rdfs:label ?value. }} }");
		WAB_FACET_QUERIES.put("facet_discusses_ss", WAB_PREFIXES + "select distinct ?uri ?value where { {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type ?type. FILTER (?type = scho:SecondarySource || ?type = :Bemerkung) ?v :isDiscussedIn ?uri. ?v rdfs:label ?value. FILTER ( (LANG(?value)='en') || (LANG(?value)='') ) }} UNION {GRAPH <http://wab.uib.no/ontology> {  ?uri :discusses ?v. ?v rdfs:label ?value. FILTER ( (LANG(?value)='en') || (LANG(?value)='') ) }} }");
		WAB_FACET_QUERIES.put("facet_refersTo_ss", WAB_PREFIXES + "select distinct ?uri ?value where { {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type ?type. FILTER (?type = scho:SecondarySource || ?type = :Bemerkung) ?v :isReferredToIn ?uri. ?v rdfs:label ?value. }} UNION {GRAPH <http://wab.uib.no/ontology> {  ?uri :refersTo ?v. ?v rdfs:label ?value. }} }");
		WAB_FACET_QUERIES.put("facet_date_ss", WAB_PREFIXES + "select distinct ?uri ?value where  {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type ?type. FILTER (?type = scho:SecondarySource || ?type = :Bemerkung) ?v :isDateOf ?uri. ?v rdfs:label ?value}}");
		WAB_FACET_QUERIES.put("label_s", WAB_PREFIXES + "select distinct ?uri ?value where  {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type ?type. FILTER (?type = scho:SecondarySource || ?type = :Bemerkung) ?uri rdfs:label ?value. FILTER ( (LANG(?value)='en') || (LANG(?value)='') )}}");
		WAB_FACET_QUERIES.put("part_ss", WAB_PREFIXES + "select distinct ?uri ?value where  {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type ?type. FILTER (?type = scho:SecondarySource || ?type = :Bemerkung) ?value :hasPart ?uri. }}");
		WAB_FACET_QUERIES.put("facet_type_ss", WAB_PREFIXES + "select distinct ?uri ?value where  {GRAPH <http://wab.uib.no/ontology> { ?uri rdf:type ?type.?type rdfs:label ?value. FILTER (?type = scho:SecondarySource || ?type = :Bemerkung) FILTER ( (LANG(?value)='en') || (LANG(?value)='') ) }}");

	}

	public WABOntologyIndexingConfig() {
		
		super(WAB_SOLR_URL, WAB_SESAME_URL, WAB_SESAME_REPOSITORY_NAME, WAB_PREFIXES,
				WAB_INDEXING_QUERIES, WAB_FACET_QUERIES, null, null, null, WAB_ENTITIES_BLACK_LIST, WAB_TAGS_BLACK_LIST, false, true);

	}

	
	
}
