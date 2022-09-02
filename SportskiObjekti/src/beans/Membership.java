package beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import utils.Gender;
import utils.MembershipType;
import utils.ObjectType;

public class Membership {
	String ID;
	public Membership(String iD, MembershipType membershipType, LocalDate payDate, LocalDateTime validUntil, int cena,
			String customerID, String status, String allowedNumber) {
		super();
		ID = iD;
		this.membershipType = membershipType;
		this.payDate = payDate;
		this.validUntil = validUntil;
		this.cena = cena;
		this.customerID = customerID;
		this.status = status;
		this.allowedNumber = allowedNumber;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public MembershipType getMembershipType() {
		return membershipType;
	}
	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}
	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}
	public LocalDateTime getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(LocalDateTime validUntil) {
		this.validUntil = validUntil;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAllowedNumber() {
		return allowedNumber;
	}
	public void setAllowedNumber(String allowedNumber) {
		this.allowedNumber = allowedNumber;
	}
	MembershipType membershipType;
	LocalDate payDate;
	LocalDateTime validUntil;
	int cena;
	String customerID;
	String status;
	String allowedNumber;
	public Membership(String iD2, Date parse, LocalDateTime parse2, int parseInt, String customerID2, String status2,
			String allowedUntil) {
		// TODO Auto-generated constructor stub
	}
	public String getMemberString() {
		String s= ID+","+payDate+","+validUntil+","+cena+","+customerID+","+status+","+allowedNumber;
		return s;
	}
	
}
