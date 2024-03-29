package data;
import beans.CustomerType;
import beans.Membership;
import beans.SportsObject;
import beans.User;
import data.utils.CustomerTypeEnum;
import data.utils.DateTools;
import data.utils.Gender;
import data.utils.UserType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class UserDAO {

	private HashMap<String, User> users=new HashMap<>();
	private String userFilepath="";
	private List<CustomerType> customerTypes;

	public UserDAO(String filePath) {

		this.setUsers(new HashMap<String, User>());
		this.setFilepath(filePath);
		initTypes();
		loadUsers();
		
		
	}
	private void initTypes() {
		customerTypes=new ArrayList<>();
		customerTypes.add(new CustomerType(CustomerTypeEnum.NONE,0,0));
		customerTypes.add(new CustomerType(CustomerTypeEnum.BRONZE,5000,5));
		customerTypes.add(new CustomerType(CustomerTypeEnum.SILVER,10000,10));
		customerTypes.add(new CustomerType(CustomerTypeEnum.GOLD,20000,20));
	}
	
	private CustomerTypeEnum getCustomerType(int points) {
		CustomerTypeEnum ret=CustomerTypeEnum.NONE;
		for(CustomerType c:customerTypes) {
			if(points>=c.getRequiredPoints()) ret=c.getName();
		} return ret;
	}
	
	public Collection<User> getUserCollection() {
		return users.values();
	}
	public void registerCustomer(User u){
		u.setActive(true);
		u.setUserType(UserType.CUSTOMER);
		users.put(u.getUsername(), u);
		saveUser(u);
	}
	public void registerTrainer(User u){
		u.setActive(true);
		u.setUserType(UserType.TRAINER);
		users.put(u.getUsername(), u);
		saveUser(u);
	}
	public void registerManager(User u){
		u.setActive(true);
		u.setUserType(UserType.MANAGER);
		users.put(u.getUsername(), u);
		saveUser(u);
	}
	public User getUser(String username,String pass){
		for (User user : users.values()) {
			if(user.getName()==username&&user.getPassword()==pass)
				return user;
		}
		return null;
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
	public void editUser(User u) {
		users.remove(u.getUsername());
		users.put(u.getUsername(), u);
		saveUsers();
	}
	
	private void saveUser(User u) {
		try {
			
			String str = u.toString();
		    BufferedWriter writer = new BufferedWriter(new FileWriter(userFilepath + "/users.csv", true));
		    writer.append("\n");
		    writer.append(str);
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		}
	}
	private void saveUsers() {
		try {
			String str="";
		    BufferedWriter writer = new BufferedWriter(new FileWriter(userFilepath+"/users.csv"));
		    writer.write("");
		    for (User u : getUserCollection()) {
				str=u.toString();
				writer.append(str);
				writer.append("\n");
		    }
		    writer.close();
		    
		} catch (IOException e) {
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
	public void deactivateUser(String username){
		User u =searchUser(username);
		u.setActive(false);
		saveUsers();
	}
	
	private void loadUsers() {
		BufferedReader in = null;
		try {
			File file = new File(userFilepath+"/users.csv");
			in = new BufferedReader(new FileReader(file));
			String line, username = "", password = "", name = "", last_name=""
					, gender="", birth_date="",userType="",membershipID="",sportsObjectID="",
					visitedObjects="",points="",customerType="",active="";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					
					username = "";password = "";name = "";last_name="";gender="";birth_date="";userType="";	
				    sportsObjectID="";visitedObjects="";points="";customerType="";active="";
					username = st.nextToken().trim();
					password = st.nextToken().trim();
					name = st.nextToken().trim();
					last_name = st.nextToken().trim();
					gender = st.nextToken().trim();
					birth_date = st.nextToken().trim();
					userType=st.nextToken().trim();	
					sportsObjectID=st.nextToken().trim();
					visitedObjects=st.nextToken().trim();
					points=st.nextToken().trim();
					customerType=st.nextToken().trim();
					active=st.nextToken().trim();
				}
				Gender genderEnum;
				if(gender.equals("M")) genderEnum=Gender.MALE; else genderEnum=Gender.FEMALE;
				SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy.");
		        Date date = parser.parse(birth_date);
				User user= new User.UserBuilder(username,password,name,last_name,genderEnum,
						birth_date,UserType.valueOf(userType),Boolean.parseBoolean(active),visitedObjects)
						.points(Integer.parseInt(points)).customerTypeEnum(getCustomerType(Integer.parseInt(points)))
						.sportsObject(sportsObjectID).build();
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

	public Collection<User> getTrainers() {
		List<User> trainerList =new ArrayList<>();
		for(User u: getUserCollection()) {
			if (u.getUserType()==UserType.TRAINER) trainerList.add(u);
		}
		return trainerList;
	}

	public Collection<User> getFreeManagers() {
		List<User> ret=new ArrayList<>();
		for(User u:getUserCollection()) {
			if(u.getUserType()==UserType.MANAGER) {
				if(u.getSportsObjectID()==null | u.getSportsObjectID().equals("0"))
					ret.add(u);
			}
		}
		return ret;
	}

	public boolean checkMembership(User customer, Membership mem,Membership ogMem) {
		LocalDate now=LocalDate.now();
		
		if(now.isAfter(mem.getValidUntil())) {
			int totalPoints=(mem.getPrice()/1000)*mem.getAllowedNumber();
			int numUsed=ogMem.getAllowedNumber()-mem.getAllowedNumber();
			int oneThird=ogMem.getAllowedNumber()/3;
			
			int newPoints=0;
			//broj_izgubljenih_bodova = ukupna_cena_Ä�lanarine/1000 * 133 * 4
			if(numUsed<oneThird) {
				int pointsLost=(mem.getPrice()/1000)*133*4;
				newPoints=customer.getPoints()-pointsLost;
				if(newPoints<0) newPoints=0;
			} else newPoints=customer.getPoints()+totalPoints;
			customer.setPoints(newPoints);
			customer.setCustomerType(getCustomerType(newPoints));
			
			editUser(customer);
			return false;
		}
		return true;
	}

	public void assignManager(String username, SportsObject s) {
		User manager=searchUser(username);
		manager.setSportsObjectID(s.getId());
		editUser(manager);
	}

	public String getUserType(User user) {
		return user.getUserType().toString();
	}

	public Collection<User> getVisitors(String sportsObjectID) {
		List<User> ret=new ArrayList<>();
		for(User u:getUserCollection()) {
			if(u.getUserType()!=UserType.CUSTOMER) continue;
			if(u.getVisitedObjects()==null) continue;
			for(String obj:u.getVisitedObjects()) {
				if(obj.equals(sportsObjectID)) ret.add(u);
			}
		}
		return ret;
	}
	public int getPoints(String username) {
		User u=searchUser(username);
		return u.getPoints();
	}

}
