package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Content;

public class ContentDAO {

	private HashMap<Integer, Content> contents=new HashMap<>();
	private String contentPath = "";
	public ContentDAO(String sportsObjectsPath) {
		super();	
		this.contentPath = sportsObjectsPath;
		loadContent();
	}
	public HashMap<Integer, Content> getContent() {
		return contents;
	}
	public void setContent(HashMap<Integer, Content> content) {
		this.contents = content;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void getContentPath(String sportsObjectsPath) {
		this.contentPath = sportsObjectsPath;
	}
	
	public void addContent(Content c) {
		int maxId = 0;
		maxId=getContent().size();
		maxId++;
		getContent().put(maxId, c);
	}
	
//	private String getContentString() {
//		String s="";
//		for(Content c: contents.values()){
//			s=s+c.getName()+',';
//		}
//	}
	public Content getByID(int id) {
		return contents.get(id);
	}
	private void loadContent() {
		BufferedReader in = null;
		try {
			File file = new File(contentPath + "/contents.csv");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line,isOpen = "";
			int id=0;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				String name="";
				line = line.trim();
				if (line.equals(""))
					continue;
				st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					name = st.nextToken().trim();
				}
				Boolean isOpen_=false;
				if(isOpen.equals("true")) isOpen_=true;
				
				Content newContent=new Content(id,name);
				addContent(newContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}}
		}
	
	
}
