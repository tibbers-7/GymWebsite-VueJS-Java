package data;
import beans.User;
import utils.Gender;
import utils.DateTools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class UserDAO {

	private HashMap<String, User> users;
	private String userFilepath="";

	public UserDAO(String filePath) {

		this.setUsers(new HashMap<String, User>());
		this.setFilepath(filePath);
		
		User u1=new User("tibbers","123","Anja","Dmitrovic",Gender.FEMALE,DateTools.parseDate("14.04.2001"));
		User u2=new User("tibbers","123","Anja","Dmitrovic",Gender.FEMALE,DateTools.parseDate("14.04.2001"));
		User u3=new User("tibbers","123","Anja","Dmitrovic",Gender.FEMALE,DateTools.parseDate("14.04.2001"));
		users.put("1", u1);
		users.put("2", u2);
		users.put("3", u3);
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
