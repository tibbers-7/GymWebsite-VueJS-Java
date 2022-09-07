package data;
import beans.Membership;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;


public class MembershipDAO {

	private HashMap<String, Membership> members=new HashMap<>();
	private String memberFilepath="";

	public MembershipDAO(String filePath) {

		this.setMemberships(new HashMap<String, Membership>());
		this.setFilepath(filePath);
		loadMemberships();
		
	}
	
	public Collection<Membership> getMembershipCollection() {
		return members.values();
	}
	
	public void setMemberships(HashMap<String, Membership> members) {
		this.members = members;
	}
	
	public void setFilepath(String memberFilePath) {
		this.memberFilepath = memberFilePath;
	}
	
	public void addMembership(Membership m) {
		members.put(m.getID(), m);
		saveMembership(m);
	}
	
	private void saveMembership(Membership u) {
		
		try {
			String str = u.getMembershipString();
		    BufferedWriter writer = new BufferedWriter(new FileWriter(memberFilepath + "/members.csv", true));
		    writer.append("\n");
		    writer.append(str);
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Membership getByUser(String username) {
		for(Membership m : getMembershipCollection()) {
			if(m.getCustomerID().equals(username)) return m;
		}
		return null;
	}
	
	
	public Membership searchMembership(String membername) {
		if (getMembershipCollection() != null) {
			for (Membership m : getMembershipCollection()) {
				if (m.getID().equals(membername)) {
					return m;
				}
			}
		}
		return null;
	}
	
	private void loadMemberships() {
		BufferedReader in = null;
		try {
			File file = new File(memberFilepath + "/memberships.csv");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String ID="", payDate = "", validUntil = "", cena = "", customerID="", line="",status="",allowedUntil="",type="";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					ID = st.nextToken().trim();
					type=st.nextToken().trim();
					payDate = st.nextToken().trim();
					validUntil = st.nextToken().trim();
					cena = st.nextToken().trim();
					customerID = st.nextToken().trim();
					status = st.nextToken().trim();
					allowedUntil=st.nextToken().trim();
				}
				
				
				Membership member=new Membership(ID,type,payDate,validUntil,cena,customerID,status,allowedUntil);
				members.put(member.getID(), member);
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
