package semedia.rdf2solr.indexconfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class DM2EIndexingConfiguration extends Configuration {

	/**
	 * DM2E configuration
	 */
	//private static final String DM2E_SOLR_SERVER = "http://metasound.dibet.univpm.it:8080/solr-dm2e/";
	private static final String DM2E_SOLR_SERVER = "http://141.20.126.236:8080/solr-dm2e/";
	//private static final String DM2E_SOLR_SERVER = "http://localhost:8983/solr/";
	private static final String DM2E_SESAME_URL = "http://localhost:9997/";
	private static final String DM2E_SESAME_REPOSITORY_NAME = "dm2e-direct/sparql";
	private static final String DM2E_PREFIXES = "PREFIX edm:<http://www.europeana.eu/schemas/edm/>" +
			"PREFIX dm2e:<http://data.dm2e.eu/schemas/dm2e/0.1/>" +
			"PREFIX ore:<http://www.openarchives.org/ore/terms/>" +
			"PREFIX geonames:<http://www.geonames.org/ontology#>" +
			"PREFIX meta:<http://example.org/metadata#>" +
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>" +
			"PREFIX prvTypes:<http://purl.org/net/provenance/types#>" +
			"PREFIX tei2edm:<http://sbb.spk-berlin.de/dm2e/tei2edm>" +
			"PREFIX dbpedia:<http://localhost:8080/resource/>" +
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
			"PREFIX prv:<http://purl.org/net/provenance/ns#>" +
			"PREFIX dc:<http://purl.org/dc/elements/1.1/>" +
			"PREFIX ir:<http://www.ontologydesignpatterns.org/cp/owl/informationrealization.owl#>" +
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>" +
			"PREFIX yago:<http://localhost:8080/class/yago/>" +
			"PREFIX j.0:<http://onto.dm2e.eu/UNOFFICIAL/>" +
			"PREFIX void:<http://rdfs.org/ns/void#>" +
			"PREFIX units:<http://dbpedia.org/units/>" +
			"PREFIX dct:<http://purl.org/dc/terms/>" +
			"PREFIX p:<http://localhost:8080/property/>" +
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>" +
			"PREFIX owl:<http://www.w3.org/2002/07/owl#>" +
			"PREFIX wgs84:<http://www.w3.org/2003/01/geo/wgs84_pos#>" +
			"PREFIX doap:<http://usefulinc.com/ns/doap#>" +
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>" +
			"PREFIX xalan:<http://xml.apache.org/xalan>" +
			"PREFIX j.1:<http://example.org/>" +
			"PREFIX j.2:<http://example.org/>" +
			"PREFIX j.3:<http://example.org/>";
	
	
//	private static final String namedGraphsFilter = "FILTER ("
//											+ "?g = <http://data.dm2e.eu/data/dataset/nli/manuscripts/20141015123629962> || " 
//											+ "?g = <http://data.dm2e.eu/data/dataset/sbb/kpe_DE-1a_995/20140908183450392> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/mpiwg/ismi/20141023164116478> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140829133247065> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20141007162514171> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140908184105626> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20141007122653391> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/bbaw/dta/20140829221549669> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140909215957962> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140829133659854> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/sbb/manumed/20140908183243860> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/ub-ffm/mshebr/20140829145221395> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/bas/codsupra/20140910165705363> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140830013040893> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/sbb/kpe_DE-Ha179_37172/20140908183432110> || "
//											+ "?g = <http://data.dm2e.eu/data/dataset/sbb/kpe_DE-1a_8535/20140908183508838>"
//										+ ")";
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/bas/codsupra/20150109170547566>"
//		+ ")";
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/bbaw/dta/20141209170514098>"
//		+ ")";

	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/brandeis/scwp/20150107214355089>"
//		+ ")";

//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/cjh/lbiarchive/20141126124504174>"
//		+ ")";

	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/cjh/lbilibrary/20141126124859870>"
//		+ ")";

//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/cjh/lbiperiodicals/20141126124926417>"
//		+ ")";	
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/cjh/yivoarchive/20141126125955374>"
//		+ ")";	
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/cjh/yivolibrary/20141126130144265>"
//		+ ")";	
	
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/ecorr/burckhardtsource/20150114171613721>"
//		+ ")";	
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140830013040893>"
//		+ ")";	

	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/jdc/nyar1418/20150108174431966>"
//		+ ")";	
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20150113133045860>"
//		+ ")";	
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/mpiwg/ismi/20150113104637149>"
//		+ ")";	
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20150113133849657>"
//		+ ")";	
	
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/nli/archives/20150115185856288>"
//		+ ")";	

//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/nli/books/20150115185601757>"
//		+ ")";	

//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/nli/manuscripts/20150115185551759>"
//		+ ")";	
	
	
	//DONE
//	private static final String namedGraphsFilter = "FILTER ("
//			+ "?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140908184105626>"
//		+ ")";	
////	

	
//	    
//	private static final String typesFilter = "FILTER (\n" + 
//    	    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
//    	    "                ||\n" + 
//    	    "                ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
//    	    "                ||\n" + 
//    	    "                ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
//    	    "                ||\n" + 
//    	    "                ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
//    	    "                ||\n" +
//    	    "                ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
//    	    "            )\n";
	
	private static final String typesFilter = "";
	
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/uib/wab/20140829133247065>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/nli/books/20150115185601757>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/nli/archives/20150115185856288>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/nli/manuscripts/20150115185551759>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/onb/abo/20140908184105626>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/onb/codices/20140829133659854>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/mpiwg/rara/20150113133849657>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/mpiwg/ismi/20150113104637149>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/mpiwg/harriot/20150113133045860>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/jdc/nyar1418/20150108174431966>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/gei/gei-digital/20140830013040893>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/ecorr/burckhardtsource/20150114171613721>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/cjh/yivolibrary/20141126130144265>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/cjh/yivoarchive/20141126125955374>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/cjh/lbiperiodicals/20141126124926417>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/cjh/lbilibrary/20141126124859870>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/cjh/lbiarchive/20141126124504174>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/brandeis/scwp/20150107214355089>";
	private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/bbaw/dta/20141209170514098>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/bas/codsupra/20150109170547566>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/ub-ffm/mshebr/20140829145221395>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/ub-ffm/msma/20150108181637060>";
	//private static String namedGraphToQuery = "<http://data.dm2e.eu/data/dataset/uber/dingler/20140909215957962>";
	
	//private static String langFilter = "FILTER(lang(?value)='en')";
	private static String langFilter = "";
	
	 
	private static HashMap<String, String> DM2E_FACET_QUERIES = new HashMap<String, String>();
	static {
		DM2E_FACET_QUERIES.put("inDataset_s", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>. ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.  ?g <http://www.w3.org/2000/01/rdf-schema#label> ?value. } }");
		DM2E_FACET_QUERIES.put("isShownAt_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?agg <http://www.europeana.eu/schemas/edm/isShownAt> ?value.} }");
		DM2E_FACET_QUERIES.put("isShownBy_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?agg <http://www.europeana.eu/schemas/edm/isShownBy> ?value.} }");
		DM2E_FACET_QUERIES.put("object_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?agg <http://www.europeana.eu/schemas/edm/object> ?value.} }");
		DM2E_FACET_QUERIES.put("title_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://purl.org/dc/elements/1.1/title> ?value. } }");
		DM2E_FACET_QUERIES.put("description_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://purl.org/dc/elements/1.1/description> ?value. } }");
		DM2E_FACET_QUERIES.put("holdingInstitution_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://onto.dm2e.eu/schemas/dm2e/holdingInstitution> ?v. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value. } }");
		DM2E_FACET_QUERIES.put("publisher_s", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://purl.org/dc/elements/1.1/publisher> ?v. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value. } }");
		DM2E_FACET_QUERIES.put("dataProvider_s", "select distinct ?uri ?value where { {graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?agg <http://www.europeana.eu/schemas/edm/dataProvider> ?v. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value. " + langFilter + " }} UNION {graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?agg <http://www.europeana.eu/schemas/edm/dataProvider> ?value. " + langFilter + " FILTER (isLiteral(?value)) }}}");
		DM2E_FACET_QUERIES.put("provider_s", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?agg <http://www.europeana.eu/schemas/edm/provider> ?v. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value. } }");
		DM2E_FACET_QUERIES.put("author_ss", "select distinct ?uri ?value where { {graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://purl.org/dc/elements/1.1/creator> ?v. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value. }} UNION {graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://purl.org/spar/pro/author> ?v. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value. }}}");
		DM2E_FACET_QUERIES.put("issued_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://purl.org/dc/terms/issued> ?v. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value. } }");
		DM2E_FACET_QUERIES.put("language_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://purl.org/dc/elements/1.1/language> ?value. } }");
		DM2E_FACET_QUERIES.put("subject_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://purl.org/dc/elements/1.1/subject> ?v. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value. FILTER (!regex(?value,\"[.]$\",\"i\"))} }");
		DM2E_FACET_QUERIES.put("printedAt_ss", "select distinct ?uri ?value where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .  ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. ?uri <http://onto.dm2e.eu/schemas/dm2e/printedAt> ?v. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value. } }");
		
	}
	
	private static HashMap<String, ArrayList<String>> PUNDIT_ANNOTATIONS_QUERIES = new HashMap<String, ArrayList<String>>();
	private static String DatasetAnnotationsFilter = "";
	static {
		if (namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/brandeis/scwp/20150107214355089>")) {
			DatasetAnnotationsFilter = "FILTER regex(str(?uri),'bir-test.unet.brandeis.edu','i')";
		} else if (namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/bbaw/dta/20141209170514098>")) {
			DatasetAnnotationsFilter = "FILTER regex(str(?uri),'media.dwds.de','i')";
		} else if (namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/gei/gei-digital/20140830013040893>")) {
			DatasetAnnotationsFilter = "FILTER regex(str(?uri),'gei-digital.gei.de','i')";
		} else if (namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/ecorr/burckhardtsource/20150114171613721>")) {
			DatasetAnnotationsFilter = "";
		}
		if (namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/brandeis/scwp/20150107214355089>") 
				|| namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/bbaw/dta/20141209170514098>") 
				|| namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/gei/gei-digital/20140830013040893>") 
				|| namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/ecorr/burckhardtsource/20150114171613721>")) {
			
			PUNDIT_ANNOTATIONS_QUERIES.put("annotated_with_place_ss", new ArrayList<String>() {{
				add("select distinct ?uri ?value where { ?ann <http://www.openannotation.org/ns/hasBody> ?graph. graph ?graph { ?frag <http://purl.org/net7/korbo/item/73666> ?v. } ?ann <http://purl.org/pundit/ont/ao#items> ?itemsGraph. graph ?itemsGraph { ?frag  <http://purl.org/dc/terms/isPartOf> ?uri.  ?v <http://www.w3.org/2000/01/rdf-schema#label> ?value. " + DatasetAnnotationsFilter + "}}");
				}});
				PUNDIT_ANNOTATIONS_QUERIES.put("annotated_with_person_ss", new ArrayList<String>() {{
					add("select distinct ?uri ?value where { ?ann <http://www.openannotation.org/ns/hasBody> ?graph. graph ?graph { ?frag <http://purl.org/net7/korbo/item/73667> ?v. } ?ann <http://purl.org/pundit/ont/ao#items> ?itemsGraph. graph ?itemsGraph { ?frag  <http://purl.org/dc/terms/isPartOf> ?uri.  ?v <http://www.w3.org/2000/01/rdf-schema#label> ?value. " + DatasetAnnotationsFilter + " }}");
				}});			
		}
		
		
	}
	
	private static HashSet<String> PUNDIT_FACETS_QUERIES = new HashSet<String>();
	static {
		if (namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/ecorr/burckhardtsource/20150114171613721>")) {
			PUNDIT_FACETS_QUERIES.add("select distinct ?uri where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://onto.dm2e.eu/schemas/dm2e/hasAnnotatableVersionAt> annotatedObject }}");
		} else if (namedGraphToQuery.equals("<http://data.dm2e.eu/data/dataset/brandeis/scwp/20150107214355089>")){
			PUNDIT_FACETS_QUERIES.add("select distinct ?uri where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri. ?agg <http://www.europeana.eu/schemas/edm/object> annotatedObject. }}");
		}
		PUNDIT_FACETS_QUERIES.add("select distinct ?uri where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?page. ?agg <http://onto.dm2e.eu/schemas/dm2e/hasAnnotatableVersionAt> annotatedObject. ?page <http://purl.org/dc/terms/isPartOf> ?uri }}");
	}
	
	private static HashMap<String,String> ANNOTATED_SUBITEMS_QUERIES = new HashMap<String,String>();
	static {
		ANNOTATED_SUBITEMS_QUERIES.put("annotated_page_ss", "select distinct ?uri ?subItem where { graph " + namedGraphToQuery + " { ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?subItem. ?agg <http://onto.dm2e.eu/schemas/dm2e/hasAnnotatableVersionAt> annotatedObject. ?subItem <http://purl.org/dc/terms/isPartOf> ?uri }}");
	}
	
	private static HashMap<String, HashMap<String, ArrayList<String>>> PUNDIT_QUERIES = new HashMap<String, HashMap<String,ArrayList<String>>>();
	private static String PUNDIT_SPARQL_ENDPOINT = "http://as.thepund.it:8080/openrdf-sesame/repositories/pundit";
	static {
		
		PUNDIT_QUERIES.put(PUNDIT_SPARQL_ENDPOINT, PUNDIT_ANNOTATIONS_QUERIES); 
	}
	
	private static final String[] DM2E_INDEXING_QUERIES = {

//		//GET ALL rdf:labels of related objects
//		"    select distinct ?uri ?field ?value where {\n" + 
//			    "        graph ?g {\n" +
//			    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
//			    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
//			    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
//			    		typesFilter +
//			    "            ?uri ?field ?v. \n" + 
//			    "            ?v <http://www.w3.org/2000/01/rdf-schema#label> ?value.\n" + 
//			    "        }\n" +
//			    		 namedGraphsFilter + 
//			    "    }",	
//
//		
//		
//			    "    select distinct ?uri ?field ?value where {\n" + 
//	    	    "        graph ?g {\n" +
//	    	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
//	    	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
//	    	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
//	    	    		typesFilter +
//	    	    "            ?uri ?field ?v. \n" + 
//	    	    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n"+ 
//	    	    "            OPTIONAL {?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value1. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value2. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value3. ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value4. }\n"+ 
//	    	    "        }\n" +
//	    	    		 namedGraphsFilter + 
//	    	    "    }\n",
//		
//		//ALTERNATIVE FOR ONB ABO
//		
////		    "    select distinct ?uri ?field ?value where {\n" + 
////    	    "        graph ?g {\n" +
////    	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
////    	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
////    	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
////    	    		typesFilter +
////    	    "            ?uri ?field ?v. \n" + 
////    	    "			 FILTER (?field=<http://purl.org/dc/elements/1.1/creator>)" + 
////    	    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
////    	    "        }\n" +
////    	    		 namedGraphsFilter + 
////    	    "    }\n",
////		    "    select distinct ?uri ?field ?value where {\n" + 
////		    	    "        graph ?g {\n" +
////		    	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
////		    	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
////		    	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
////		    	    		typesFilter +
////		    	    "            ?uri ?field ?v. \n" + 
////		    	    "			 FILTER (?field=<http://purl.org/dc/elements/1.1/subject>)" + 
////		    	    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
////		    	    "        }\n" +
////		    	    		 namedGraphsFilter + 
////		    	    "    }\n",
////		"    select distinct ?uri ?field ?value where {\n" + 
////	    "        graph ?g {\n" +
////	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
////	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
////	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
////	    		typesFilter +
////	    "            ?uri ?field ?v. \n" + 
////	    "			 FILTER (?field=<http://purl.org/dc/elements/1.1/publisher>)" + 
////	    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
////	    "        }\n" +
////	    		 namedGraphsFilter + 
////	    "    }\n",
////		"    select distinct ?uri ?field ?value where {\n" + 
////	    "        graph ?g {\n" +
////	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
////	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
////	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
////	    		typesFilter +
////	    "            ?uri ?field ?v. \n" + 
////	    "			 FILTER (?field=<http://onto.dm2e.eu/schemas/dm2e/publishedAt>)" + 
////	    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
////	    "        }\n" +
////	    		 namedGraphsFilter + 
////	    "    }\n",
////		"    select distinct ?uri ?field ?value where {\n" + 
////	    "        graph ?g {\n" +
////	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
////	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
////	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
////	    		typesFilter +
////	    "            ?uri ?field ?v. \n" + 
////	    "			 FILTER (?field=<http://purl.org/dc/terms/issued>)" + 
////	    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
////	    "        }\n" +
////	    		 namedGraphsFilter + 
////	    "    }\n", 
//		    	    
//	    
//		//GET ALL related literals
//	    "            \n" + 
//	    	    "    select distinct ?uri ?field ?value where {\n" + 
//	    	    "        graph ?g {\n" +
//	    	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
//	    	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
//	    	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
////	    	    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
//	    	    		typesFilter + 
//	    	    "            ?uri ?field ?value.\n" + 
//	    	    "            FILTER (isLiteral(?value))\n" + 
//	    	    "        }\n" +
//	    	    		namedGraphsFilter + 
//	    	    "    }       \n", 
//		
//
//
//   
//    "    select distinct ?uri ?field ?value where {\n" + 
//    "        graph ?g {\n" +
//    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
//    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
//    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
//    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
//    	typesFilter +
//    "            ?uri ?field ?value.\n" + 
//    "            FILTER (!isLiteral(?value))\n" + 
//    "            FILTER NOT EXISTS {?value <http://www.w3.org/2004/02/skos/core#prefLabel> ?label}\n" +
//    "            FILTER NOT EXISTS {?value <http://www.w3.org/2000/01/rdf-schema#label> ?label}\n" +
//    "        }\n" +
//    		namedGraphsFilter + 
//    "    }       \n", 
//    "    select distinct ?uri ?field ?value where {\n" + 
//    	    "        graph ?g {\n" +
//    	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
//    	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
//    	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
//    	    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
//    	    	typesFilter +
//    	    "            ?agg ?field ?v. \n" + 
//    	    "            ?v <http://www.w3.org/2000/01/rdf-schema#label> ?value.\n" + 
//    	    "        }\n" +
//    	    		namedGraphsFilter + 
//    	    "    }\n", 
//    	    
//    	    
//    	   
//    	    
//    "    select distinct ?uri ?field ?value where {\n" + 
//    "        graph ?g {\n" +
//    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
//    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
//    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" +  
//    		typesFilter +
//    "            ?agg ?field ?v. \n" + 
//    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
//    "        }\n" +
//    		namedGraphsFilter + 
//    "    }\n", 
//    	    
//    	    //ALTERNATICE FOR ONB
////    	    "    select distinct ?uri ?field ?value where {\n" + 
////    	    "        graph ?g {\n" +
////    	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
////    	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
////    	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
////    	    		typesFilter +
////    	    "            ?agg ?field ?v. \n" + 
////    	    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
////    	    "        }\n"
////    	    + "		 FILTER (?field = <http://www.europeana.eu/schemas/edm/dataProvider>)" +
////    	    		namedGraphsFilter + 
////    	    "    }\n", 
////    	    "    select distinct ?uri ?field ?value where {\n" + 
////    	    "        graph ?g {\n" +
////    	    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
////    	    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
////    	    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
////    	    		typesFilter +
////    	    "            ?agg ?field ?v. \n" + 
////    	    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
////    	    "        }\n" + 
////    	    "		 FILTER (?field = <http://www.europeana.eu/schemas/edm/provider>)" +
////    	    		namedGraphsFilter + 
////    	    "    }\n", 
////    	    
//    	    
//    
//    "    select distinct ?uri ?field ?value where {\n" + 
//    "        graph ?g {\n" +
//    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
//    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
//    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
//    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
//    		typesFilter +
//    "            ?agg ?field ?value.\n" + 
//    "            FILTER (isLiteral(?value))\n" + 
//    "        }\n" +
//    		namedGraphsFilter + 
//    "    }\n", 
//    
//    "    select distinct ?uri ?field ?value where {\n" + 
//    "        graph ?g {\n" +
//    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
//    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
//    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
//    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
//    		typesFilter +
//    "            ?agg ?field ?value.\n" + 
//    "            FILTER (!isLiteral(?value))\n" + 
//    "            FILTER NOT EXISTS {?value <http://www.w3.org/2004/02/skos/core#prefLabel> ?label}\n" +
//    "            FILTER NOT EXISTS {?value <http://www.w3.org/2000/01/rdf-schema#label> ?label}\n" +
//    "        }\n" +
//    		namedGraphsFilter + 
//    "    }\n", 
    
    "    select distinct ?uri ?value {\n" + 
    "            graph " + namedGraphToQuery + " {\n" +
    "	             ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "   	         ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
    "                ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    		typesFilter +
    "                ?uri <http://purl.org/dc/elements/1.1/title> ?value.\n" + 
    "            }\n" +
    "    }\n", 
    
    "    select distinct ?uri ?value {\n" + 
    "            graph " + namedGraphToQuery + " {\n" +
    "   	         ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "	             ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
    "                ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    			typesFilter +
    "                ?uri <http://onto.dm2e.eu/schemas/dm2e/subtitle> ?value.\n" + 
    "            }\n" +
    "    }\n", 
    
    "    select distinct ?uri ?value {\n" + 
    "            graph " + namedGraphToQuery + " {\n" +
    "    	         ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "	             ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
    "                ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    			typesFilter +
    "                ?uri <http://purl.org/dc/elements/1.1/description> ?value.\n" + 
    "            }\n" +
    "    }\n", 
    
    "    select distinct ?uri ?value {\n" + 
    "            graph " + namedGraphToQuery + " {\n" +
    "   	         ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "	             ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
    "                ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    		typesFilter +
    "                ?uri <http://onto.dm2e.eu/schemas/dm2e/cover> ?value.\n" + 
    "            }\n" +
    "    }"
    };
	
	/*
	private static final String[] DM2E_INDEXING_QUERIES = {
		DM2E_PREFIXES +"select distinct ?g ?uri ?field ?value where {" +
				"graph ?g {" +
					"?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. " +
					"?uri <http://purl.org/dc/elements/1.1/type> <http://onto.dm2e.eu/schemas/dm2e/1.1/Page>" +
					"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://purl.org/spar/fabio/#Page>}" +
					"?uri ?field ?v. " +
					"?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value." +
				"}" +
			"}",
		DM2E_PREFIXES + "select distinct ?g ?uri ?field ?value where {" +
					"graph ?g {" +
						"?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. " +
						"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://onto.dm2e.eu/schemas/dm2e/1.1/Page>}" +
						"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://purl.org/spar/fabio/#Page>}" +
						"?uri ?field ?value. " +
						"FILTER (isLiteral(?value))" +
					"}" +
				"}",
		DM2E_PREFIXES + "select distinct ?g ?uri ?field ?value where {" +
						"graph ?g {" +
							"?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. " +
							"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://onto.dm2e.eu/schemas/dm2e/1.1/Page>}" +
							"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://purl.org/spar/fabio/#Page>}" +
							"?uri ?field ?value. " +
							"FILTER (!isLiteral(?value)) " +
							"FILTER NOT EXISTS {?value <http://www.w3.org/2004/02/skos/core#prefLabel> ?label}" +
						"}" +
					"}",
		DM2E_PREFIXES + "select distinct ?g ?uri ?field ?value where {" +
					"graph ?g {" +
						"?agg edm:aggregatedCHO ?uri." +
						"?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. " +
						"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://onto.dm2e.eu/schemas/dm2e/1.1/Page>}" +
						"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://purl.org/spar/fabio/#Page>}" +
						"?agg ?field ?v. " +
						"?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value." +
					"}" +
				"}",
		DM2E_PREFIXES + "select distinct ?g ?uri ?field ?value where {" +
						"graph ?g {" +
							"?agg edm:aggregatedCHO ?uri." +
							"?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. " +
							"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://onto.dm2e.eu/schemas/dm2e/1.1/Page>}" +
							"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://purl.org/spar/fabio/#Page>}" +
							"?agg ?field ?value. " +
							"FILTER (isLiteral(?value))" +
						"}" +
					"}",			
		DM2E_PREFIXES + "select distinct ?g ?uri ?field ?value where {" +
						"graph ?g {" +
							"?agg edm:aggregatedCHO ?uri." +
							"?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. " +
							"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://onto.dm2e.eu/schemas/dm2e/1.1/Page>}" +
							"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://purl.org/spar/fabio/#Page>}" +
							"?agg ?field ?value. " +
							"FILTER (!isLiteral(?value)) " +
							"FILTER NOT EXISTS {?value <http://www.w3.org/2004/02/skos/core#prefLabel> ?label}" +
						"}" +
					"}",	
		DM2E_PREFIXES + "select ?g ?uri ?value {" +
				"graph ?g {" +
					"?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>." +
					"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://onto.dm2e.eu/schemas/dm2e/1.1/Page>}" +
					"FILTER NOT EXISTS {?uri <http://purl.org/dc/elements/1.1/type> <http://purl.org/spar/fabio/#Page>}" +
					"?uri <http://purl.org/dc/elements/1.1/title> ?value." +
				"}" +
		"}",
	};
	*/
	public DM2EIndexingConfiguration() {
		super(DM2E_SOLR_SERVER, DM2E_SESAME_URL, DM2E_SESAME_REPOSITORY_NAME, DM2E_PREFIXES, DM2E_INDEXING_QUERIES, DM2E_FACET_QUERIES, PUNDIT_QUERIES, PUNDIT_FACETS_QUERIES, ANNOTATED_SUBITEMS_QUERIES, null, null, true, false);
	}
	 
	
	
}
