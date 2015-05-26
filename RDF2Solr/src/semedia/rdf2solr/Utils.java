package semedia.rdf2solr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringEscapeUtils;

public class Utils {
	
	private static String LOG_OUTPUT = "files/log.txt"; 
	
	public static void clearLog() throws IOException {
		Writer out = new BufferedWriter(new FileWriter(new File(LOG_OUTPUT),false));
		//out.write("storyjs_jsonp_data = " + jobj.toString(3));
		out.write("");
		out.flush();
		out.close();
	}
	
	public static void writeToFile(String string, String filePath) throws IOException {

		Writer out = new BufferedWriter(new FileWriter(new File(filePath),false));
		out.write(string);
		out.flush();
		out.close();
		
	}
	
	public static void writeToLog(String string) throws IOException {

		Writer out = new BufferedWriter(new FileWriter(new File(LOG_OUTPUT),true));
		//out.write("storyjs_jsonp_data = " + jobj.toString(3));
		out.write(string + "\n");
		out.flush();
		out.close();
		
	}
	
	public static String stringToUTF8(String string) {
		return new String(string.getBytes(),Charset.forName("UTF-8"));
	}
	
	public static String escapeHTML(String text) {
		String result = StringEscapeUtils.escapeHtml4(text).replaceAll("ü","&#252;").replaceAll("í","&#237;").replaceAll("ç", "&#231;").replaceAll("č", "&#269;").replaceAll("ž", "&#382;").replaceAll("š", "&#353;").replace("\n", "<br/>");
		return result;
	}
	
	public static String unescapeHTML(String html) {
		String text = StringEscapeUtils.unescapeHtml4(html);
		return text;
	}
	
	public static String encodeURL(String text) throws UnsupportedEncodingException {
		return URLEncoder.encode(text,"UTF-8").replaceAll("\\+","%20");
	}
	
	public static String decodeURL(String text) throws UnsupportedEncodingException {
		return URLDecoder.decode(text,"UTF-8");
	}
	
	public static String readFile( String file ) throws IOException {

		
		StringBuilder  stringBuilder = new StringBuilder();
		String         ls = System.getProperty("line.separator");
		BufferedReader in = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(new File(file)), Charset.forName("UTF-8")));
 
		String str;
 
		while ((str = in.readLine()) != null) {
			stringBuilder.append(str);
			stringBuilder.append( ls );
		}
 
        in.close();
        
        return stringBuilder.toString();
		
		/*
		BufferedReader reader = new BufferedReader( new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }

	    return stringBuilder.toString();
	    */
	}

	public static String stripHTMLTags(String html) {
		return html.replaceAll("\\<.*?>","");
	}

}
