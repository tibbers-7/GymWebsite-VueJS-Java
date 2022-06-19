package data;
import beans.SportsObject;
import beans.User;
import utils.Gender;
import utils.DateTools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class UserDAO {

	private HashMap<String, User> users=new HashMap<>();
	private String userFilepath="";

	public UserDAO(String filePath) {

		this.setUsers(new HashMap<String, User>());
		this.setFilepath(filePath);
		loadUsers();
		
	}
	
	public Collection<User> getUserCollection() {
		return users.values();
	}
	
	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}
	
	public void setFilepath(String userFilePath) {
		this.userFilepath = userFilePath;
	}
	
	public void addUser(User u) {
		users.put(u.getUsername(), u);
		saveUser(u);
	}
	
	private void saveUser(User u) {
		File f = new File(userFilepath + "/users.txt");
		FileWriter writer=null;
		try {
			writer = new FileWriter(f,true);
			writer.write(u.getUserString()); 
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User searchUser(String username) {
		if (getUserCollection() != null) {
			for (User u : getUserCollection()) {
				if (u.getUsername().equals(username)) {
					return u;
				}
			}
		}
		return null;
	}
	
	private void loadUsers() {
		BufferedReader in = null;
		try {
			File file = new File(userFilepath + "/users.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, username = "", password = "", name = "", last_name="", gender="", birth_date="";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					username = st.nextToken().trim();
					password = st.nextToken().trim();
					name = st.nextToken().trim();
					last_name = st.nextToken().trim();
					gender = st.nextToken().trim();
					birth_date = st.nextToken().trim();
				}
				Gender genderEnum;
				if(gender.equals("M")) genderEnum=Gender.MALE; else genderEnum=Gender.FEMALE;
				SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy.");
		        Date date = parser.parse(birth_date);
				User user=new User(username,password,name,last_name,genderEnum,date);
				users.put(user.getUsername(), user);
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
