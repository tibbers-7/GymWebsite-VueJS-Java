package data;

import java.util.Collection;
import java.util.HashMap;

import beans.Membership;
import utils.ObjectType;

public class MembershipDAO {
	private HashMap<Integer, Membership> memberships;
	public HashMap<Integer, Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(HashMap<Integer, Membership> memberships) {
		this.memberships = memberships;
	}


	public String getMembershipsPath() {
		return membershipsPath;
	}

	public void setMembershipsPath(String membershipsPath) {
		this.membershipsPath = membershipsPath;
	}


	private String membershipsPath= "";
	
	public Collection<Membership> getMembershipsCollection() {
		return memberships.values();
	}

	public MembershipDAO() {
		// TODO Auto-generated constructor stub
	}

	/*public SportsObjectsDAO(String contextPath) {
		this.setSportsObjects(new HashMap<Integer, Membership>());
		this.setSportsObjectsPath(contextPath);

	}*/

	public void addMemberships(Membership s) {
		int maxId = 0;
		maxId=getMembershipsCollection().size();
		maxId++;
		memberships.put(maxId, s);
	}

	

private void test() {

		Membership s1 = new Membership("011AADDCCD", null, null, 1000, "00013123", "20310233");
		
	}
}
