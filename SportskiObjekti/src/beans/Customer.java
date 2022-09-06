package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.utils.CustomerType;
import data.utils.Gender;
import data.utils.MembershipType;
import data.utils.UserType;

public class Customer extends User{


	public Customer(String username, String password, String name, String last_name, Gender gender, String birthDate,
			UserType userType, String membershipID, String sportsObjectID, String visitedObjects, int points,
			CustomerType customerType, Membership membership, List<Integer> visitedLocations, int points2,
			CustomerType type) {
		super(username, password, name, last_name, gender, birthDate, userType, membershipID, sportsObjectID,
				visitedObjects, points, customerType);
		this.membership = membership;
		this.visitedLocations = visitedLocations;
		points = points2;
		this.type = type;
	}
	private Membership membership;
	private List<Integer> visitedLocations;

	private int points;
	private CustomerType type;
	public Membership getMembership() {
		return membership;
	}
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	public List<Integer> getVisitedLocations() {
		return visitedLocations;
	}
	public void setVisitedLocations(List<Integer> visitedLocations) {
		this.visitedLocations = visitedLocations;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public CustomerType getType() {
		return type;
	}
	public void setType(CustomerType type) {
		this.type = type;
	}
	
}