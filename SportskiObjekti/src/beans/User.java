package beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.utils.CustomerTypeEnum;
import data.utils.Gender;
import data.utils.UserType;

public class User implements Serializable{
	
	
	public User() {
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 6008893841218394652L;
	
	public User(String username, String password, String name, String last_name, Gender gender, String birthDate,
			UserType userType, String sportsObjectID, String visitedObjects,
			int points) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.userType = userType;
		this.sportsObjectID = sportsObjectID;
		this.visitedObjectsID = visitedObjects;
		this.points = points;
		this.fullName=name+" "+last_name;
		if(!visitedObjectsID.equals(" ")|visitedObjectsID!=null)
			{
			String[] v=visitedObjectsID.split(";");
			this.visitedObjects=new ArrayList<>();
			for(String visited:v) {
				this.visitedObjects.add(visited);
			}
		}
		
	}
	public User(UserBuilder builder) {
		super();
		this.username = builder.username;
		this.password = builder.password;
		this.name = builder.name;
		this.last_name = builder.last_name;
		this.gender = builder.gender;
		this.birthDate = builder.birthDate;
		this.userType = builder.userType;
		this.sportsObjectID = builder.sportsObjectID;
		this.visitedObjectsID = builder.visitedObjectsID;
		this.points = builder.points;
		this.fullName=name+" "+last_name;
		this.visitedObjects=builder.visitedObjects;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public data.utils.Gender getGender() {
		return gender;
	}
	public void setGender(data.utils.Gender gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getSportsObjectID() {
		return sportsObjectID;
	}
	public void setSportsObjectID(String sportsObjectID) {
		this.sportsObjectID = sportsObjectID;
	}
	public List<String> getVisitedObjects() {
		return visitedObjects;
	}
	public void setVisitedObjects(List<String> visitedObjects) {
		this.visitedObjects = visitedObjects;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public String getFullName() {
		return fullName;
	}
	public String getVisitedObjectsID() {
		return visitedObjectsID;
	}
	public void setVisitedObjectsID(String visitedObjectsID) {
		this.visitedObjectsID = visitedObjectsID;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		String visited="/";
		if (visitedObjects!=null | !visitedObjects.isEmpty()) visited=visitedObjectsID;
		
		return username + ", " + password + ", " + name + ", " + last_name + ", " + gender + ", " + birthDate + ", "
				+ active + ", " + userType  + ", " + sportsObjectID + ", " + visited
				+ ", " + points + ", " + customerTypeEnum;
	}
	private String username;
	private String password;
	private String name;
	private String last_name;
	private data.utils.Gender gender;
	private String birthDate;
	private Boolean active;
	private UserType userType;
	private String sportsObjectID;
	private String visitedObjectsID;
	private int points;
	private CustomerTypeEnum customerTypeEnum;
	private String fullName;
	private List<String> visitedObjects;
	
	public static class UserBuilder{
		
		private String username;
		private String password;
		private String name;
		private String last_name;
		private data.utils.Gender gender;
		private String birthDate;
		private UserType userType;
		private Boolean active;
		private String sportsObjectID;
		private String visitedObjectsID;
		private int points;
		private CustomerTypeEnum customerTypeEnum;
		private String fullName;
		private List<String> visitedObjects;
		
		
		public UserBuilder(String username, String password, String name, String last_name, Gender gender, String birthDate,
				UserType userType, Boolean active, String visitedObjectsID) {
			this.username = username;
			this.password = password;
			this.name = name;
			this.last_name = last_name;
			this.gender = gender;
			this.birthDate = birthDate;
			this.userType = userType;
			this.active=active;
			this.fullName=name+" "+last_name;
			this.visitedObjectsID=visitedObjectsID;
			if(visitedObjectsID!=null)
			{
				String[] v=visitedObjectsID.split(";");
				this.visitedObjects=new ArrayList<>();
				for(String visited:v) {
					this.visitedObjects.add(visited);
				}
			}
		}
		public Boolean getActive() {
			return active;
		}
		public void setActive(Boolean active) {
			this.active = active;
		}
		public UserBuilder sportsObject(String sportsObjectID)
		{
			this.sportsObjectID=sportsObjectID;
			return this;
		}	
		public UserBuilder visitedObjectsID(String visitedObjects)
		{
			this.visitedObjectsID=visitedObjects;
			return this;
		}	
		public UserBuilder points(int points)
		{
			this.points=points;
			return this;
		}
		public UserBuilder customerTypeEnum(CustomerTypeEnum customerTypeEnum)
		{
			this.customerTypeEnum=customerTypeEnum;
			return this;
		}
		public User build(){
			User user=new User(this);
			return user;
		}
	}

	public void setCustomerType(CustomerTypeEnum customerType) {
		this.customerTypeEnum=customerType;
		
	}

	
}