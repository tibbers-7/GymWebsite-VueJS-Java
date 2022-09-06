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
			ContentDAO contentDAO=new ContentDAO(sportsObjectsPath);
			loadSportsObjects();
		}
		public SportsObject getSportsObject(String sportsObjectID) {
			if (getSportsObjectsCollection() != null) {
				for (SportsObject s : getSportsObjectsCollection()) {
					if (s.getName().equals(sportsObjectID)) {
						return s;
					}
				}
			}
			return null;
		}
		

		private String sportsObjectsPath = "";

		public SportsObjectDAO() {
			// TODO Auto-generated constructor stub
			test();
		}

		/*public SportsObjectsDAO(String contextPath) {
			this.setSportsObjects(new HashMap<Integer, SportsObject>());
			this.setSportsObjectsPath(contextPath);

		}*/
	
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
				String line, name = "", type = "", services = "", isOpen="", location="", avgScore="", openHours="",imgName="";
				StringTokenizer st;
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals(""))
						continue;
					st = new StringTokenizer(line, ";");
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
					if(isOpen.equals("true")) isOpen_=true;
					
					String[] servicesStrings=services.split(",");
					List<String> servicesList=new ArrayList<String>();
					for(String s : servicesStrings) {
						servicesList.add(s);
					}
					String imgFilepath=sportsObjectsPath+"/images/"+imgName;
					SportsObject sportsObject=new SportsObject(name,ObjectType.valueOf(type),servicesList,isOpen_,location,Float.parseFloat(avgScore),imgFilepath,openHours, imgFilepath);
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

private void test() {

			SportsObject s1 = new SportsObject("aa1100ddcc", ObjectType.GYM, null, true, "Adresa 1", (float) 4.8, "", "07:00 - 19:00");
			addSportsObject(s1);
			addSportsObject(s1);
			addSportsObject(s1);
		}
}
