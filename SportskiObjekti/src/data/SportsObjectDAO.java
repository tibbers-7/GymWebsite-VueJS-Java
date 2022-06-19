package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.SportsObject;
import beans.User;
import utils.ObjectType;

public class SportsObjectDAO {
		private HashMap<Integer, SportsObject> sportsObjects= new HashMap<>();
		private String sportsObjectsPath = "";

		public SportsObjectDAO(String contextPath) {
			this.setSportsObjects(new HashMap<Integer, SportsObject>());
			SportsObject s1 = new SportsObject("aa1100ddcc", ObjectType.GYM, null, true, "15.66 59.55 ", (float) 4.8, "07:00 - 19:00");
			addSportsObject(s1);
			this.setSportsObjectsPath(contextPath);
			loadSportsObjects();

		}
		
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
		
	
		public void addSportsObject(SportsObject s) {
			int maxId = 0;
			maxId=getSportsObjectsCollection().size();
			maxId++;
			sportsObjects.put(maxId, s);
			saveSportsObject(s);
		}

		private void saveSportsObject(SportsObject object) {
			File f = new File(sportsObjectsPath + "/data/users.txt");
			FileWriter writer=null;
			try {
				writer = new FileWriter(f);
				writer.write(object.getSportsObjectString()); 
			    writer.flush();
			    writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	private void loadSportsObjects() {
		BufferedReader in = null;
		try {
			File file = new File(sportsObjectsPath + "/sportsObjects.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, name = "", type = "", services = "", isOpen="", location="", avgScore="", openHours="";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
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
				}
				SportsObject sportsObject=new SportsObject(name,type,services,isOpen,location,avgScore,openHours);
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
			}
		}
	}
}
