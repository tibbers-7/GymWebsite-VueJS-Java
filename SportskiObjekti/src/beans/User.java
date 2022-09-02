package beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import utils.CustomerType;
import utils.Gender;
import utils.UserType;

public class User {
	
	
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
	public utils.Gender getGender() {
		return gender;
	}
	public void setGender(utils.Gender gender) {
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
	private utils.Gender gender;
	private String birthDate;
	
	UserType userType;
	String membershipID;
	String sportsObjectID;
	String visitedObjectsID;
	int points;
	CustomerType customerType;
	
	
}