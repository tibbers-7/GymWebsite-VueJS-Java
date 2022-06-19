package data;
import beans.SportsObject;
import beans.User;
import utils.Gender;
import utils.DateTools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class UserDAO {

	private HashMap<String, User> users;
	private String userFilepath="";

	public UserDAO(String filePath) {

		users=new HashMap<>();
		this.setUsers(new HashMap<String, User>());
		this.setFilepath(filePath);
		
		User u1=new User("tibbers","123","Anja","Dmitrovic",Gender.FEMALE,DateTools.parseDate("14.04.2001"));
		users.put(u1.getUsername(), u1);
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
	public void addKorisnik(User u) {
		users.put(u.getUsername(), u);
	}
	
	public void addUser(User u) {
		users.put(u.getUsername(), u);
		//saveKorisnici();
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
	
//	private void loadKorisnici(String contextPath) {
//		FileWriter fileWriter = null;
//		BufferedReader in = null;
//		File file = null;
//		try {
//			file = new File(contextPath + "/data/korisnici.txt");
//			in = new BufferedReader(new FileReader(file));
//
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.setVisibilityChecker(
//					VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
//			TypeFactory factory = TypeFactory.defaultInstance();
//			MapType type = factory.constructMapType(HashMap.class, String.class, Korisnik.class);
//			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
//			korisnici = ((HashMap<String, Korisnik>) objectMapper.readValue(file, type));
//		} catch (FileNotFoundException fnfe) {
//			try {
//				file.createNewFile();
//				fileWriter = new FileWriter(file);
//				ObjectMapper objectMapper = new ObjectMapper();
//				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
//				String stringUsers = objectMapper.writeValueAsString(korisnici);
//				fileWriter.write(stringUsers);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				if (fileWriter != null) {
//					try {
//						fileWriter.close();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			if (in != null) {
//				try {
//					in.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
}
