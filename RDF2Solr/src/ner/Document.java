package ner;

import java.util.ArrayList;
import java.util.Map;

public class Document implements Comparable<Document>{

	private String url;
	private Map<String,ArrayList<String>> additionalFieldsToIndex;
	private String text;
	private String publicationDate;
	
	public Document() {
		// TODO Auto-generated constructor stub
	}
	
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public String getPublicationDate() {
		return publicationDate;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setAdditionalFieldsToIndex(
			Map<String, ArrayList<String>> additionalFieldsToIndex) {
		this.additionalFieldsToIndex = additionalFieldsToIndex;
	}
	
	public Map<String, ArrayList<String>> getAdditionalFieldsToIndex() {
		return additionalFieldsToIndex;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return url.hashCode();
	}
	
	@Override
	public int compareTo(Document o) {
		return o.getPublicationDate().compareTo(getPublicationDate());
	}
	
}
