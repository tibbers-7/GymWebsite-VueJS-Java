package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

import beans.Content;
import beans.SportsObject;
import beans.User;
import data.utils.ObjectType;

public class SportsObjectDAO {
		private HashMap<Integer, SportsObject> sportsObjects= new HashMap<>();
		private String sportsObjectsPath = "";
		public HashMap<Integer, SportsObject> getSportsObjects() {
			return sportsObjects;
		}
		public Collection<SportsObject> getSportsObjectsCollection() {
			return sportsObjects.values();
		}

		public String getSportsObjectsPath() {
			return sportsObjectsPath;
		}

		public void setSportsObjectsPath(String sportsObjectsPath) {
			this.sportsObjectsPath = sportsObjectsPath;
		}

		public void setSportsObjects(HashMap<Integer, SportsObject> sportsObjects) throws IOException {
			this.sportsObjects = sportsObjects;
			
		}

		public SportsObjectDAO(String sportsObjectsPath) {
			super();
			this.sportsObjectsPath = sportsObjectsPath;
			contentDAO=new ContentDAO(sportsObjectsPath);
			loadSportsObjects();
		}
		public SportsObject getSportsObject(String sportsObjectID) {
			return sportsObjects.get(Integer.parseInt(sportsObjectID));
		}
		
		

		public SportsObjectDAO() {
			// TODO Auto-generated constructor stub
		}

		/*public SportsObjectsDAO(String contextPath) {
			this.setSportsObjects(new HashMap<Integer, SportsObject>());
			this.setSportsObjectsPath(contextPath);

		}*/
	

		public void addSportsObject(SportsObject s) {
			if(s.getId()==null) setNextKey(s);
			sportsObjects.put(Integer.parseInt(s.getId()),s);
			saveObjects();
		}
		
		private void setNextKey(SportsObject s) {
			int largest=0;
			for(int i:sportsObjects.keySet()) {
				if(i>largest) largest=i;
			} 
			s.setId(Integer.toString(++largest));
		}

		private void loadSportsObjects() {
			BufferedReader in = null;
			try {
				File file = new File(sportsObjectsPath + "/sportsObjects.csv");
				System.out.println(file.getCanonicalPath());
				in = new BufferedReader(new FileReader(file));
				String line, name = "", type = "", services = "", isOpen="", location="", avgScore="", openHours="",imgName="";
				StringTokenizer st;
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals(""))
						continue;
					st = new StringTokenizer(line, ",");
					while (st.hasMoreTokens()) {
						name = st.nextToken().trim();
						type = st.nextToken().trim();
						services = st.nextToken().trim();
						isOpen = st.nextToken().trim();
						location = st.nextToken().trim();
						avgScore = st.nextToken().trim();
						openHours = st.nextToken().trim();
						imgName = st.nextToken().trim();
					}
					Boolean isOpen_=false;
					String [] hours=openHours.split("-");
					if(LocalDateTime.now().getHour()>=Integer.parseInt(hours[0])&&LocalDateTime.now().getHour()<=Integer.parseInt(hours[1])) isOpen_=true;
					
					String[] contentIds=services.split("-");
					List<Content> contentList=new ArrayList<Content>();
					for(String id : contentIds) {
						contentList.add(contentDAO.getByID(Integer.parseInt(id)));
					}
					String imgFilepath=sportsObjectsPath+"/images/"+imgName;
					SportsObject sportsObject=new SportsObject(name,ObjectType.valueOf(type),contentList,isOpen_,location,Float.parseFloat(avgScore),imgFilepath,openHours);
					addSportsObject(sportsObject);
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
		
		private void saveObjects() {
			try {
				String str="";
			    BufferedWriter writer = new BufferedWriter(new FileWriter(sportsObjectsPath+"/sportsObjects.csv"));
			    writer.write("");
			    for (SportsObject s : getSportsObjectsCollection()) {
					str=s.toString();
					writer.append(str);
					writer.append("\n");
			    }
			    writer.close();
			    
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	public Collection<String> getTypes() {
		List<String> ret=new ArrayList<>();
		for(ObjectType ot:ObjectType.values()) {
			ret.add(ot.toString());
		} return ret;
	}
}
