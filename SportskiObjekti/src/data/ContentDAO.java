package data;

import java.util.HashMap;

import beans.Content;

public class ContentDAO {

	private HashMap<Integer, Content> content=new HashMap<>();
	private String sportsObjectsPath = "";
	public ContentDAO(HashMap<Integer, Content> content, String sportsObjectsPath) {
		super();
		this.content = content;
		this.sportsObjectsPath = sportsObjectsPath;
	}
	public HashMap<Integer, Content> getContent() {
		return content;
	}
	public void setContent(HashMap<Integer, Content> content) {
		this.content = content;
	}
	public String getSportsObjectsPath() {
		return sportsObjectsPath;
	}
	public void setSportsObjectsPath(String sportsObjectsPath) {
		this.sportsObjectsPath = sportsObjectsPath;
	}
	
	
}
