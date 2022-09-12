package data;
import beans.Membership;
import beans.User;
import data.utils.MembershipType;
import data.utils.Status;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class MembershipDAO {

	private HashMap<Integer, Membership> members=new HashMap<>();
	private String memberFilepath="";

	public MembershipDAO(String filePath) {

		this.setMemberships(new HashMap<Integer, Membership>());
		this.setFilepath(filePath);
		loadMemberships();
		
	}
	
	public Collection<Membership> getMembershipCollection() {
		return members.values();
	}
	
	public void setMemberships(HashMap<Integer, Membership> members) {
		this.members = members;
	}
	
	public void setFilepath(String memberFilePath) {
		this.memberFilepath = memberFilePath;
	}
	
	public Membership addMembership(User customer, Membership newMem) {
		Membership memToDelete=null;
		for(Membership m: getMembershipCollection()) {
			if(m.getCustomerID().equals(customer.getUsername())){
				memToDelete=m;
			}
		}
		if(memToDelete!=null) members.remove(memToDelete.getID());
		newMem.setPayDate(LocalDate.now());
		if(newMem.getMembershipType()==MembershipType.YEARLY)
			newMem.setValidUntil(LocalDate.now().plusYears(1));
		else newMem.setValidUntil(LocalDate.now().plusMonths(1));
		newMem.setCustomerID(customer.getUsername());
		newMem.setStatus(Status.ACTIVE);
		newMem.setID(getNextKey());
		members.put(newMem.getID(), newMem);
		saveMemberships();
		return newMem;
		
	}
	
	private int getNextKey() {
		int largest=0;
		for(int i:members.keySet()) {
			if(i>largest) largest=i;
		} return ++largest;
	}
	
	private void saveMembership(Membership m) {
		
		try {
			String str = m.getMembershipString();
		    BufferedWriter writer = new BufferedWriter(new FileWriter(memberFilepath + "/memberships.csv", true));
		    writer.append("\n");
		    writer.append(str);
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void saveMemberships() {
		try {
			String str="";
		    BufferedWriter writer = new BufferedWriter(new FileWriter(memberFilepath + "/memberships.csv"));
		    writer.write("");
		    for (Membership m : getMembershipCollection()) {
				str=m.getMembershipString();
				writer.append(str);
				writer.append("\n");
		    }
		    writer.close();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Membership getByUser(String username) {
		for(Membership m : getMembershipCollection()) {
			if(m.getStatus()==Status.INACTIVE) continue;
			if(m.getCustomerID().equals(username)) return m;
		}
		return null;
	}
	
	
	public Membership searchMembership(String membername) {
		return members.get(Integer.parseInt(membername));
	}
	
	private void loadMemberships() {
		BufferedReader in = null;
		try {
			File file = new File(memberFilepath + "/memberships.csv");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String ID="",name="", payDate = "",object="", validUntil = "", cena = "", customerID="", line="",status="",allowedUntil="",type="";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					ID = st.nextToken().trim();
					name=st.nextToken().trim();
					type=st.nextToken().trim();
					object=st.nextToken().trim();
					payDate = st.nextToken().trim();
					validUntil = st.nextToken().trim();
					cena = st.nextToken().trim();
					customerID = st.nextToken().trim();
					status = st.nextToken().trim();
					allowedUntil=st.nextToken().trim();
				}
				Membership member=null;
				if(customerID.equals("/")) {
					member=new Membership(ID,name,type,object,cena,allowedUntil);
				}
				else member=new Membership(ID,name,type,object,payDate,validUntil,cena,customerID,status,allowedUntil);
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


	public Collection<Membership> getAvailable() {
		List<Membership> ret=new ArrayList<>();
		for(Membership m:getMembershipCollection()) {
			if(m.getCustomerID().equals("/")) {
				ret.add(m);
			}
		} return ret;
	}

	public void cancelMembership(User customer) {
		Membership mem=getByUser(customer.getUsername());
		members.remove(mem.getID());
		saveMemberships();
	}

	public Membership getOriginal(Membership mem) {
		for(Membership m:getMembershipCollection()) {
			if(m.getCustomerID().equals("/")) {
				if(m.getName().equals(mem.getName())) return m;
			}
		}
		return null;
	}
	public Membership getByID(String s) {
		for(Membership m:getMembershipCollection()) {
				if(m.getID()==Integer.parseInt(s)) return m;
			
		}
		return null;
	}

	public int checkMembership(String objId, User user) {
		Membership mem=null;
		for(Membership m:getMembershipCollection()) {
			if(m.getCustomerID().equals(user.getUsername())) mem=m;
		}
		if (mem==null) return 1;
		if(mem.getSportsObject().equals(objId)) {
			if(mem.getAllowedNumber())
			mem.removePoint();
			return 0;
		} 
		return 2; //nema u tom obj
	}
	
}
