package beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.utils.CustomerType;
import data.utils.Gender;
import data.utils.UserType;

public class User implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6008893841218394652L;
	
	public User(String username, String password, String name, String last_name, Gender gender, String birthDate,
			UserType userType, String membershipID, String sportsObjectID, String visitedObjects,
			int points, CustomerType customerType) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.userType = userType;
		this.membershipID = membershipID;
		this.sportsObjectID = sportsObjectID;
		this.visitedObjectsID = visitedObjects;
		this.points = points;
		this.customerType = customerType;
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
		this.membershipID = builder.membershipID;
		this.sportsObjectID = builder.sportsObjectID;
		this.visitedObjectsID = builder.visitedObjectsID;
		this.points = builder.points;
		this.customerType = builder.customerType;
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
	public String getMembershipID() {
		return membershipID;
	}
	public void setMembershipID(String membershipID) {
		this.membershipID = membershipID;
	}
	public String getSportsObjectID() {
		return sportsObjectID;
	}
	public void setSportsObjectID(String sportsObjectID) {
		this.sportsObjectID = sportsObjectID;
	}
	public String getVisitedObjects() {
		return visitedObjectsID;
	}
	public void setVisitedObjects(String visitedObjects) {
		this.visitedObjectsID = visitedObjects;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public CustomerType getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	@Override
	public String toString() {
		return username + "," + password + "," + name + "," + last_name
				+"," + gender + "," + birthDate + "," + userType +","
				+ membershipID + "," + sportsObjectID + "," + visitedObjectsID
				+ "," + points + "," + customerType;
	}
	private String username;
	private String password;
	private String name;
	private String last_name;
	private data.utils.Gender gender;
	private String birthDate;
	private Boolean active;
	private UserType userType;
	private String membershipID;
	private String sportsObjectID;
	private String visitedObjectsID;
	private int points;
	private CustomerType customerType;
	
	public static class UserBuilder{
		
		private String username;
		private String password;
		private String name;
		private String last_name;
		private data.utils.Gender gender;
		private String birthDate;
		private UserType userType;
		private Boolean active;
		private String membershipID;
		private String sportsObjectID;
		private String visitedObjectsID;
		private int points;
		private CustomerType customerType;
		
		public UserBuilder(String username, String password, String name, String last_name, Gender gender, String birthDate,
				UserType userType, Boolean active) {
			this.username = username;
			this.password = password;
			this.name = name;
			this.last_name = last_name;
			this.gender = gender;
			this.birthDate = birthDate;
			this.userType = userType;
			this.active=active;
		}
		public UserBuilder membership(String membershipID)
		{
			this.membershipID=membershipID;
			return this;
		}
		public UserBuilder sportsObject(String sportsObjectID)
		{
			this.sportsObjectID=sportsObjectID;
			return this;
		}	
		public UserBuilder visitedObjects(String visitedObjects)
		{
			this.visitedObjectsID=visitedObjects;
			return this;
		}	
		public UserBuilder sportsObject(int points)
		{
			this.points=points;
			return this;
		}
		public UserBuilder customerType(CustomerType customerType)
		{
			this.customerType=customerType;
			return this;
		}
		public User build(){
			User user=new User(this);
			return user;
		}
	}
	
}