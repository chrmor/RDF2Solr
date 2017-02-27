package semedia.rdf2solr.tests;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import ner.Document;

import org.apache.commons.io.IOUtils;

public class NYTNews {
	
	public static Set<Document> getNYTNews(int offset, int end, String section, String beginDate) throws IOException {
		
		Set<Document> result = new HashSet<Document>();
		
		String sectionFilter = "&fq=document_type:(\"article\")";
		if (section!=null) {
			//sectionFilter = "&fq=section_name:(\"" + section + "\")%20AND%20document_type:(\"article\")";
			if (section.length() > 0) {
				sectionFilter += "%20AND%20section_name:(\"" + section + "\")";
			} 
			
		}
		
		end = end/10;
		offset = offset/10;
		
		for (int page = offset; page < end; page++) {
			String query = "http://api.nytimes.com/svc/search/v2/articlesearch.json?sort=oldest&begin_date=" + beginDate +"&fl=web_url,pub_date&page=" + page + sectionFilter + "&api-key=344f492f3d1395937e8bfba97baa1484:10:72379757";
			System.out.println(query);
			URL call = new URL(query);
			String news = IOUtils.toString(call.openStream(), "UTF-8");
			String[] list = news.split("\"web_url\":");
			for (int i = 1; i < list.length; i++) {
				String link = list[i];
				
				String[] pieces = link.split(",\"pub_date\":");
				link = pieces[0];
				link = link.replace("\"", "").replace("\\/", "/");
				String date = pieces[1].replace("},{", "").replace("\"", "").split("T")[0];
				if (i==list.length - 1) {
					date = date.split("}]}")[0];
					
				}
				HashMap<String,ArrayList<String>> fields = new HashMap<String,ArrayList<String>>();
				ArrayList<String> values = new ArrayList<String>();
				values.add(date);
				fields.put("date_s", values);
				
				Document doc = new Document();
				doc.setAdditionalFieldsToIndex(fields);
				doc.setUrl(link);
				doc.setPublicationDate(date);
				//doc.setText(text);
				
				result.add(doc);
			}	
		}
		return result;
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		try {
			getNYTNews(0, 1000, "Science", "20150101");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
