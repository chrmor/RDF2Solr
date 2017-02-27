package semedia.rdf2solr.indexconfs;

import java.util.ArrayList;
import java.util.HashMap;

public class GramsciNomiIndexingConfiguration extends Configuration {
	
	private static final String SOLR_SERVER = "http://gramsciproject.org:8080/solr-gramsci-nomi/";	
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
								+ "PREFIX dc: <http://purl.org/dc/elements/1.1/> "
								+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>";
									
	private static final String[] GRAMSCI_INDEXING_QUERIES = {	

	};
	
	private static HashMap<String, String> GRAMSCI_FACET_QUERIES = new HashMap<String, String>();
	static {
		// GRAMSCI_FACET_QUERIES.put("nome_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/annotations/> { ?part dct:isPartOf ?dluri. ?part <http://purl.org/gramsciproject/vocab/correspondsTo> ?v. } graph <http://data.gramsciproject.org/nomi/> { ?v skos:prefLabel ?value. } ?uri edm:isShownAt ?dluri. ?uri dct:type gramsci:Nota. }");
		GRAMSCI_FACET_QUERIES.put("nome_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/> { ?uri rdf:type foaf:Person. ?uri skos:prefLabel ?value }}");
		GRAMSCI_FACET_QUERIES.put("quaderno_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/annotations/> { ?f gramsci:correspondsTo ?uri. ?f dct:isPartOf ?dlUri. } ?nota edm:isShownAt ?dlUri. ?nota dct:isPartOf ?q. ?q rdfs:label ?value.}");
		GRAMSCI_FACET_QUERIES.put("quaderno_struct_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value ?title where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/annotations/> { ?f gramsci:correspondsTo ?uri. ?f dct:isPartOf ?dlUri. } ?nota edm:isShownAt ?dlUri. ?nota dct:isPartOf ?q. ?q rdfs:label ?value. ?q dc:title ?title.}");
		GRAMSCI_FACET_QUERIES.put("note_count_i", GRAMSCI_PREFIXES + "select distinct ?uri (COUNT(distinct ?dluri) as ?value) where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/annotations/> { ?frag gramsci:correspondsTo ?uri. ?frag dct:isPartOf ?dluri.}} GROUP BY ?uri");
		GRAMSCI_FACET_QUERIES.put("total_count_i", GRAMSCI_PREFIXES + "select distinct ?uri (COUNT(distinct ?frag) as ?value) where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/annotations/> { ?frag gramsci:correspondsTo ?uri. }} GROUP BY ?uri");
		GRAMSCI_FACET_QUERIES.put("quaderno_count_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value ?qtitle (COUNT(distinct ?frag) as ?count) where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/annotations/> { ?frag gramsci:correspondsTo ?uri. ?frag dct:isPartOf ?dluri. } ?nota edm:isShownAt ?dluri. ?nota dct:isPartOf ?quaderno. ?quaderno rdfs:label ?value. ?quaderno dc:title ?qtitle.} GROUP BY ?uri ?value ?qtitle");
		
		//TUTTE LE DIVERSE GRAFIE DEL NOME NEI SINGOLI QUADERNI
		GRAMSCI_FACET_QUERIES.put("quaderno_grafie_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value ?label ?title (COUNT(distinct ?frag) as ?count) where { graph <http://data.gramsciproject.org/nomi/annotations/> { ?frag gramsci:correspondsTo ?uri. ?frag dct:isPartOf ?dluri. ?frag rdfs:label ?value. } graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } ?nota edm:isShownAt ?dluri. ?nota dc:title ?title. ?nota rdfs:label ?label. } GROUP BY ?uri ?value ?nota ?label ?title");
		//LE OCCORRENZE TOTALI DI OGNI SINGOLA GRAFIA DEL NOME
		GRAMSCI_FACET_QUERIES.put("grafie_count_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value (COUNT(distinct ?frag) as ?count) where { graph <http://data.gramsciproject.org/nomi/annotations/> { ?frag gramsci:correspondsTo ?uri. ?frag dct:isPartOf ?dluri. ?frag rdfs:label ?value.} graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } } GROUP BY ?uri ?value");
		
		//TUTTE DIVERSE AGGETTIVAZIONI DEL NOME NEI QUADERNI
		GRAMSCI_FACET_QUERIES.put("quaderno_aggettivi_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value ?label ?title (COUNT(distinct ?frag) as ?count) where { graph <http://data.gramsciproject.org/nomi/annotations/> { ?frag gramsci:derivedFrom ?uri. ?frag dct:isPartOf ?dluri. ?frag rdfs:label ?value.} graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } ?nota edm:isShownAt ?dluri. ?nota dc:title ?title. ?nota rdfs:label ?label. } GROUP BY ?uri ?value ?nota ?label ?title");		
		//LE OCCORRENZE TOTALI DI OGNI SINGOLA AGGETTIVAZIONE DEL NOME
		GRAMSCI_FACET_QUERIES.put("aggettivi_count_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value (COUNT(distinct ?frag) as ?count) where { graph <http://data.gramsciproject.org/nomi/annotations/> { ?frag gramsci:derivedFrom ?uri. ?frag dct:isPartOf ?dluri. ?frag rdfs:label ?value.} graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } } GROUP BY ?uri ?value");
		
		
		GRAMSCI_FACET_QUERIES.put("dizionario_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/> { ?uri rdf:type foaf:Person.} graph <http://data.gramsciproject.org/thesaurus/> { ?urith skos:closeMatch ?uri. ?urith skos:closeMatch ?uridizio.} graph <http://data.gramsciproject.org/dizionario/> { ?uridizio skos:prefLabel ?value.}}");
		GRAMSCI_FACET_QUERIES.put("media_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/> { ?uri rdf:type foaf:Person.} graph <http://data.gramsciproject.org/thesaurus/> { ?urith skos:closeMatch ?uri. ?urith skos:closeMatch ?uridizio.} graph <http://data.gramsciproject.org/dizionario/> { ?uridizio skos:prefLabel ?label.} graph <http://data.gramsciproject.org/media/> {?urimedia dct:subject ?uridizio. ?urimedia dct:title ?value.}}");
		GRAMSCI_FACET_QUERIES.put("treccani_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/> { ?uri rdf:type foaf:Person. ?uri skos:exactMatch ?value. ?value dc:type \"Treccani Link\".}}");
		GRAMSCI_FACET_QUERIES.put("alt_link_ss", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/> { ?uri rdf:type foaf:Person. ?uri skos:exactMatch ?value. ?value dc:type \"Alternative Link\".}}");
		GRAMSCI_FACET_QUERIES.put("description_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/> { ?uri rdf:type foaf:Person. ?uri dc:description ?value.}}");
		GRAMSCI_FACET_QUERIES.put("comment_s", GRAMSCI_PREFIXES + "select distinct ?uri ?value where { graph <http://data.gramsciproject.org/nomi/> {?uri skos:inScheme <http://data.gramsciproject.org/nomi/Indice+dei+nomi>. } graph <http://data.gramsciproject.org/nomi/> { ?uri rdf:type foaf:Person. ?uri rdfs:comment ?value.}}");

	}							
	
	private static final String[] GRAMSCI_ENTITIES_BLACK_LIST = {"http://it.wikipedia.org/wiki/Comitato_Olimpico_Internazionale", "http://it.wikipedia.org/wiki/Cio_%28citt%C3%A0%29","http://it.wikipedia.org/wiki/ISO_639-3"};
	private static ArrayList<String> GRAMSCI_TAGS_BLACK_LIST = new ArrayList<String>() {{ add("CIO"); add("ISO 639-3"); add("2C-B"); add("Annot"); add("ABMA"); add("Abio"); add("Chief Information Officer"); add("ABC"); add("Compagnia di intervento operativo"); }};
	
	public GramsciNomiIndexingConfiguration() {
		super(SOLR_SERVER, GRAMSCI_SESAME_URL, GRAMSCI_SESAME_REPOSITORY_NAME, GRAMSCI_PREFIXES, GRAMSCI_INDEXING_QUERIES, GRAMSCI_FACET_QUERIES, null, null, null, GRAMSCI_ENTITIES_BLACK_LIST, GRAMSCI_TAGS_BLACK_LIST, false, true);
	}		

}
