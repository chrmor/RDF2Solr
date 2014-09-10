package semedia.rdf2solr.indexconfs;


public class DM2EIndexingConfiguration extends Configuration {

	/**
	 * DM2E configuration
	 */
	private static final String DM2E_SOLR_SERVER = "http://metasound.dibet.univpm.it:8080/solr-dm2e/";
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
	
	private static final String[] DM2E_INDEXING_QUERIES = {
    "    select distinct ?uri ?field ?value where {\n" + 
    "        graph ?g {\n" +
    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "            FILTER (\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "            )\n" + 
    "            ?uri ?field ?v. \n" + 
    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
    "        }\n" +
    "		 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
//    "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }\n",
    
    "            \n" + 
    "    select distinct ?uri ?field ?value where {\n" + 
    "        graph ?g {\n" +
    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "            FILTER (\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "            )\n" + 
    "            ?uri ?field ?value.\n" + 
    "            FILTER (isLiteral(?value))\n" + 
    "        }\n" +
    "		 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
//    "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }       \n", 
     
    "    select distinct ?uri ?field ?value where {\n" + 
    "        graph ?g {\n" +
    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>. \n" + 
    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "            FILTER (\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "            )\n" + 
    "            ?uri ?field ?value.\n" + 
    "            FILTER (!isLiteral(?value))\n" + 
    "            FILTER NOT EXISTS {?value <http://www.w3.org/2004/02/skos/core#prefLabel> ?label}\n" + 
    "        }\n" +
    "		 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
 //   "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }       \n", 
    
    "    select distinct ?uri ?field ?value where {\n" + 
    "        graph ?g {\n" +
    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "            FILTER (\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "            )\n" + 
    "            ?agg ?field ?v. \n" + 
    "            ?v <http://www.w3.org/2004/02/skos/core#prefLabel> ?value.\n" + 
    "        }\n" +
    "		 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
 //   "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }\n", 
    
    "    select distinct ?uri ?field ?value where {\n" + 
    "        graph ?g {\n" +
    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "            FILTER (\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "            )\n" + 
    "            ?agg ?field ?value.\n" + 
    "            FILTER (isLiteral(?value))\n" + 
    "        }\n" +
    "		 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
 //   "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }\n", 
    
    "    select distinct ?uri ?field ?value where {\n" + 
    "        graph ?g {\n" +
    "            ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "            ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
    "            ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "            ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "            FILTER (\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                ||\n" + 
    "                ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "            )\n" + 
    "            ?agg ?field ?value.\n" + 
    "            FILTER (!isLiteral(?value))\n" + 
    "            FILTER NOT EXISTS {?value <http://www.w3.org/2004/02/skos/core#prefLabel> ?label}\n" + 
    "        }\n" +
    "		 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
//    "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }\n", 
    
    "    select distinct ?uri ?value {\n" + 
    "            graph ?g {\n" +
    "	             ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "   	         ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" + 
    "                ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "                FILTER (\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "                )\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/title> ?value.\n" + 
    "            }\n" +
    "			 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
//    "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }\n", 
    
    "    select distinct ?uri ?value {\n" + 
    "            graph ?g {\n" +
    "   	         ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "	             ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
    "                ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "                FILTER (\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "                )\n" + 
    "                ?uri <http://onto.dm2e.eu/schemas/dm2e/subtitle> ?value.\n" + 
    "            }\n" +
    "			 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
//    "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }\n", 
    
    "    select distinct ?uri ?value {\n" + 
    "            graph ?g {\n" +
    "    	         ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "	             ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
    "                ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "                FILTER (\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "                )\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/description> ?value.\n" + 
    "            }\n" +
    "			 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
//    "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }\n", 
    
    "    select distinct ?uri ?value {\n" + 
    "            graph ?g {\n" +
    "   	         ?agg <http://www.europeana.eu/schemas/edm/aggregatedCHO> ?uri.\n" + 
    "	             ?agg <http://onto.dm2e.eu/schemas/dm2e/displayLevel> \"true\"^^<http://www.w3.org/2001/XMLSchema#boolean> .\n" +  
    "                ?uri <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.europeana.eu/schemas/edm/ProvidedCHO>.\n" + 
    "                ?uri <http://purl.org/dc/elements/1.1/type> ?dc_type .\n" + 
    "                FILTER (\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Issue>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Letter>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Book>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://purl.org/ontology/bibo/Journal>\n" + 
    "                    ||\n" + 
    "                    ?dc_type = <http://onto.dm2e.eu/schemas/dm2e/Manuscript>\n" + 
    "                )\n" + 
    "                ?uri <http://onto.dm2e.eu/schemas/dm2e/cover> ?value.\n" + 
    "            }\n" +
    "			 FILTER (?g = <http://data.dm2e.eu/data/dataset/gei/gei-digital/20140301021526607> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/harriot/20140306195409535> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rara/20140303175001587> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/mpiwg/rarafulltextsample/20140305163700648> || \n" + 
//    "            ?g = <http://data.dm2e.eu/data/dataset/onb/abo/20140306154239247> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/onb/codices/20140304184952678> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/ub-ffm/sammlungen/20140301004538912> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uber/dingler/20140306153350082> || \n" + 
    "            ?g = <http://data.dm2e.eu/data/dataset/uib/wab/20140307191021384>)" + 
    "    }"};
	
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
		super(DM2E_SOLR_SERVER, DM2E_SESAME_URL, DM2E_SESAME_REPOSITORY_NAME, DM2E_PREFIXES, DM2E_INDEXING_QUERIES, null, null, null);
	}
	 
	
	
}
