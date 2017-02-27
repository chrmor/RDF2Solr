package semedia.rdf2solr.sedano;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class IndexSearch {

	private static final String indexUrl = "http://ec2-52-19-92-96.eu-west-1.compute.amazonaws.com:9200/news/news/_search";
	
	public void search() {
		HttpClient client = new HttpClient();
		
		GetMethod get = new GetMethod(indexUrl);
		
	}
	
}
