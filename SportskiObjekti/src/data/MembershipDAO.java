package data;
import beans.SportsObject;
import beans.Membership;
import utils.Gender;
import utils.DateTools;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
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
	
	public void addMembership(Membership u) {
		members.put(u.getMemberString(), u);
		saveMembership(u);
	}
	
	private void saveMembership(Membership u) {
		FileOutputStream outputStream;
		try {
			String str = u.getMemberString();
		    BufferedWriter writer = new BufferedWriter(new FileWriter(memberFilepath + "/members.csv", true));
		    writer.append("\n");
		    writer.append(str);
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
	}
	
	
	public Membership searchMembership(String membername) {
		if (getMembershipCollection() != null) {
			for (Membership u : getMembershipCollection()) {
				if (u.getMemberString().equals(membername)) {
					return u;
				}
			}
		}
		return null;
	}
	
	private void loadMemberships() {
		BufferedReader in = null;
		try {
			File file = new File(memberFilepath + "/members.csv");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String ID="", payDate = "", validUntil = "", cena = "", customerID="", trainerID="", line="",status="",allowedUntil="";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					ID = st.nextToken().trim();
					payDate = st.nextToken().trim();
					validUntil = st.nextToken().trim();
					cena = st.nextToken().trim();
					customerID = st.nextToken().trim();
					status = st.nextToken().trim();
					allowedUntil=st.nextToken().trim();
				}
				SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy.");
				Membership member=new Membership(ID,parser.parse(payDate),LocalDateTime.parse(validUntil),Integer.parseInt(cena),customerID,status,allowedUntil);
				members.put(member.getMemberString(), member);
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
