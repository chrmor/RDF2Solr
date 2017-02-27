package semedia.rdf2solr.index;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * This class handles a Solr Index, wrapping CURL operations.
 * @author christianmorbidoni
 *
 */
public class SolrIndexHandler {

	private SolrServer solrServer;
	
	/**
	 * Initializes a Solr index
	 * @param host, the host where the Solr server is running
	 * @param port, the port to which the Solr server listens
	 * @param serverName, the name of the Solr instance
	 * @param indexName, the name of the Solr "core" containing the index to be handled
	 */
	public void initIndex(String host, int port, String serverName, String indexName) {
		String url = host + ":" + port + "/" + serverName + "/" + indexName;
		solrServer = new HttpSolrServer(url);
	}
	
}
