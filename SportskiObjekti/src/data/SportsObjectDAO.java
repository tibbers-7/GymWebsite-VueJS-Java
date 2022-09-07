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
import java.util.StringTokenizer;

import javax.ws.rs.core.Response;

import beans.SportsObject;
import beans.User;
import data.utils.ObjectType;

public class SportsObjectDAO {
		private HashMap<Integer, SportsObject> sportsObjects= new HashMap<>();
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

		public void setSportsObjects(HashMap<Integer, SportsObject> sportsObjects) {
			this.sportsObjects = sportsObjects;
		}

		public SportsObjectDAO(String sportsObjectsPath) {
			super();
			this.sportsObjectsPath = sportsObjectsPath;
			loadSportsObjects();
			//test();
		}
		public SportsObject getSportsObject(String sportsObjectID) {
			if (getSportsObjectsCollection() != null) {
				for (SportsObject s : getSportsObjectsCollection()) {
					if (s.getId().equals(sportsObjectID)) {
						return s;
					}
				}
			}
			return null;
		}
		

		private String sportsObjectsPath = "";

		public void addSportsObject(SportsObject s) {
			int maxId = 0;
			maxId=getSportsObjectsCollection().size();
			maxId++;
			sportsObjects.put(maxId, s);
		}

		private void loadSportsObjects() {
			BufferedReader in = null;
			try {
				File file = new File(sportsObjectsPath + "/sportsObjects.csv");
				System.out.println(file.getCanonicalPath());
				in = new BufferedReader(new FileReader(file));
				String line, name = "", type = "", services = "", isOpen="", location="", avgScore="", openHours="",imgName="",id="",manager="";
				StringTokenizer st;
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals(""))
						continue;
					st = new StringTokenizer(line, ",");
					while (st.hasMoreTokens()) {
						id = st.nextToken().trim();
						name = st.nextToken().trim();
						type = st.nextToken().trim();
						services = st.nextToken().trim();
						isOpen = st.nextToken().trim();
						location = st.nextToken().trim();
						avgScore = st.nextToken().trim();
						openHours = st.nextToken().trim();
						imgName = st.nextToken().trim();
						manager = st.nextToken().trim();
					}
					Boolean isOpen_=false;
					if(isOpen.equals("true")) isOpen_=true;
					
					String[] servicesStrings=services.split("-");
					List<String> servicesList=new ArrayList<String>();
					for(String s : servicesStrings) {
						servicesList.add(s);
					}
					String imgFilepath="images/"+imgName;
					SportsObject sportsObject=new SportsObject(id,name,ObjectType.valueOf(type),servicesList,isOpen_,location,Float.parseFloat(avgScore),imgFilepath,openHours,manager);
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
	public SportsObject getByManager(String username) {
		for(SportsObject s: getSportsObjectsCollection()) {
			if(s.getManager().equals(username)) return s;
		}
		return null;
}
}
