package data;

import java.util.Collection;
import java.util.HashMap;

import beans.SportsObject;
import utils.ObjectType;

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

		

private void test() {

			SportsObject s1 = new SportsObject("aa1100ddcc", ObjectType.GYM, null, true, "Adresa 1", (float) 4.8, "", "07:00 - 19:00");
			addSportsObject(s1);
			addSportsObject(s1);
			addSportsObject(s1);
		}
}
