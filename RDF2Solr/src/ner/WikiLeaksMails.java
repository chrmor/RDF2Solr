package ner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class WikiLeaksMails {
	
	public static Set<String> getMailUrlsFromSender(String sender, int numPages, int pageOffset) throws IOException {
		
		HashSet<String> ids = new HashSet<String>();
		
		String query = "https://wikileaks.org/hackingteam/emails/?q=&relid=0&title=&notitle=&date=&mailboxid=0&mailbox=&domainid=0&domain=&minrecipient=0&maxrecipient=0&file=&mto=&mfrom=" + sender + "&nofrom=&noto=&offset=";
	
		for (int i = pageOffset; i < pageOffset + numPages; i++) {
			String url = query + i*50;
			URL call = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection)call.openConnection();
			String html = IOUtils.toString(con.getInputStream(), "UTF-8");
			String[] pieces = html.split("doc-");
			for (int j = 1; j < pieces.length; j++) {
				String id = pieces[j].split(" class")[0].replace("\"", "");
				ids.add("https://wikileaks.org/hackingteam/emails/emailid/" + id);
			}
			
		}
		
		return ids;
		
	}
	
	public static void populateItems(String id, Set<Document> result) throws IOException {
		
		HashMap<String,ArrayList<String>> additionalFields = new HashMap<String,ArrayList<String>>();
		String query = id;
		System.out.println("Fetching content from " + query + " ...");
		URL call = new URL(query);
		HttpsURLConnection con = (HttpsURLConnection)call.openConnection();
		String html = IOUtils.toString(con.getInputStream(), "UTF-8");
		
		
		//TODO extract this data using JSoup is safer...
		String[] pieces = html.split("<th>Date</th>")[1].split("<td>")[1].split("</td>");
		ArrayList<String> values = new ArrayList<String>();
		values.add(pieces[0]);
		additionalFields.put("date_s", values);
		values = new ArrayList<String>();
		values.add(pieces[0].split(" ")[0]);
		additionalFields.put("day_s", values);
		pieces = html.split("<th>From</th>")[1].split("<td>")[1].split("</td>");
		values = new ArrayList<String>();
		values.add(pieces[0]);
		additionalFields.put("from_s", values);
		pieces = html.split("<th>To</th>")[1].split("<td>")[1].split("</td>");
		String[] tos = pieces[0].split(",");
		values = new ArrayList<String>();
		for (String to : tos) {
			values.add(to);
		}
		additionalFields.put("to_ss", values);
		pieces = html.split("<h2>")[1].split("</h2>");
		values = new ArrayList<String>();
		values.add(pieces[0]);
		additionalFields.put("subject_s", values);
		
		String text = WikiLeaksContentExtractor.getContentFromHTML(html);
		
		Document doc = new Document();
		doc.setAdditionalFieldsToIndex(additionalFields);
		doc.setUrl(query);
		doc.setText(text);
		
		result.add(doc);
	}
	
	public static Set<Document> getItemsFromRange(int startID, int endID) throws IOException {
		
		Set<Document> result = new HashSet<Document>();
		
		for (int i = startID; i <= endID; i++) {
			populateItems("https://wikileaks.org/hackingteam/emails/emailid/" + i, result);
			
		}
		return result;
		
	}
	
	public static Set<Document> getItemsFromSender(String sender, int numPages, int pageOffset) throws IOException {
		
		Set<Document> result = new HashSet<Document>();
		
		Set<String> ids = getMailUrlsFromSender(sender, numPages, pageOffset);
		
		for (String id : ids) {
			populateItems(id, result);
		}
		
		return result;
		
	}
	
	public static void main(String[] args) throws IOException {
		WikiLeaksMails wm = new WikiLeaksMails();
		//wm.getItems(165299, 165299);
		wm.getMailUrlsFromSender("Vincenzetti", 2, 0);
	}

}
