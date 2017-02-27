package ner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WikiLeaksContentExtractor {
	
	public static ArrayList<String> blacklist = new ArrayList<String>();
	static {
		blacklist.add("Milan Singapore Washington DC");
		blacklist.add("Milan Singapore WashingtonDC");
		blacklist.add("<br />Hacking Team");
		blacklist.add("www.hackingteam.com");
		blacklist.add("<br />CEO");
		blacklist.add("<br />email:&nbsp;d.vincenzetti@hackingteam.com");
		blacklist.add("<br />mobile: +39 3494403823");
		blacklist.add("<br />phone: +39 0229060603");
		blacklist.add("follow me on Twitter: @emanuele_paris");
		
		
		
	}
	
	public static String getContentFromHTML(String html) {
		
		Document doc = Jsoup.parse(html);
		String mailBody = doc.getElementById("email_body").html();
		
		mailBody = mailBody.replace("&nbsp;", " ");
		
		for (String black : blacklist) {
			mailBody = mailBody.replace(black, "");
		}
		
		return mailBody;
		
		
	}	
	public static String getContentFromUrl(String url) throws IOException {
		
		URL call = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection)call.openConnection();
		String html = IOUtils.toString(con.getInputStream(), "UTF-8");
		return getContentFromHTML(html);
		
		
	}  

	
	public static void main(String[] args) {
		try {
			String text = getContentFromUrl("https://wikileaks.org/hackingteam/emails/emailid/169441");
			System.out.println("TESTO:\n" + text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
