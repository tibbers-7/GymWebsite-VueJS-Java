package beans;

import java.util.Date;
import java.util.List;
import utils.Gender;
import utils.MembershipType;
import beans.CustomerType;

public class Customer extends User{
	private Membership membership;
	private List<Integer> visitedLocations;
	public Customer(String username,String password,String name,String last_name, Gender gender,Date birthDate,Membership membership, List<Integer> visitedLocations, int points, CustomerType type) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setLast_name(last_name);
		this.setGender(gender);
		this.setBirthDate(birthDate);
		this.membership = membership;
		this.visitedLocations = visitedLocations;
		this.points = points;
		this.type = type;
	}
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