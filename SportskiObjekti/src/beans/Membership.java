package beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import data.utils.Status;
import data.utils.MembershipType;

public class Membership implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9199905222200022220L;
	
	public Membership(String iD, MembershipType membershipType, LocalDate payDate, LocalDate validUntil, int price,
			String customerID, Status status, int allowedNumber) {
		super();
		ID = iD;
		this.membershipType = membershipType;
		this.payDate = payDate;
		this.validUntil = validUntil;
		this.price = price;
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
	public LocalDate getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(LocalDate validUntil) {
		this.validUntil = validUntil;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getAllowedNumber() {
		return allowedNumber;
	}
	public void setAllowedNumber(int allowedNumber) {
		this.allowedNumber = allowedNumber;
	}
	
	public String getPayDateString() {
		return payDateString;
	}
	public void setPayDateString(String payDateString) {
		this.payDateString = payDateString;
	}
	public String getValidUntilString() {
		return validUntilString;
	}
	public void setValidUntilString(String validUntilString) {
		this.validUntilString = validUntilString;
	}

	String ID;
	MembershipType membershipType;
	LocalDate payDate;
	String payDateString;
	LocalDate validUntil;
	String validUntilString;
	int price;
	String customerID;
	Status status;
	int allowedNumber;
	
	
	//yearly & monthly membership -> validUntil=null
	
	public Membership(String id,String type, String createdDate,String validUntil, String price, String customerID, String status,
			String allowedNumber) {
		this.status=Status.INACTIVE;
		if(status=="A") this.status=Status.ACTIVE;
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		this.payDate=LocalDate.parse(createdDate,formatter);
		this.payDateString = payDate.format(formatter);
		
		switch(type) {
		case "Y":
			this.validUntil=this.payDate.plusYears(1);
			this.membershipType=MembershipType.YEARLY;
			break;
		case "M":
			this.validUntil=this.payDate.plusMonths(1);
			this.membershipType=MembershipType.MONTHLY;
			break;
		default:
			this.membershipType=MembershipType.OTHER;
			this.validUntil=LocalDate.parse(validUntil,formatter);
		}
		
		this.validUntilString = this.validUntil.format(formatter);
		this.customerID=customerID;
		this.price=Integer.parseInt(price);
		this.allowedNumber=Integer.parseInt(allowedNumber);
				
		
		
	}
	public Membership(String iD2, String type, String cena, String allowedUntil) {
		this.ID=iD2;
		switch(type) {
			case "Y": this.membershipType=MembershipType.YEARLY;break;
			case "M": this.membershipType=MembershipType.MONTHLY;break;
			default: this.membershipType=MembershipType.OTHER; }
		this.price=Integer.parseInt(cena);
		this.status=Status.INACTIVE;
		this.allowedNumber=Integer.parseInt(allowedUntil);
		
	}
	public String getMembershipString() {
		String s= ID+","+membershipType+","+payDate+","+validUntil+","+Integer.toString(price)+","+customerID+","+status.toString()+","+Integer.toString(allowedNumber);
		return s;
	}
	
}
