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
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public MembershipType getMembershipType() {
		return membershipType;
	}
	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}

	private int ID;
	private String name;
	public Membership() {
		super();
	}

	private MembershipType membershipType;
	private LocalDate payDate;
	private String payDateString;
	private LocalDate validUntil;
	private String validUntilString;
	private int price;
	private String customerID;
	private Status status;
	private int allowedNumber;
	private String sportsObject;
	
	
	//yearly & monthly membership -> validUntil=null
	
	public String getSportsObject() {
		return sportsObject;
	}
	public void setSportsObject(String sportsObject) {
		this.sportsObject = sportsObject;
	}
	public Membership(int iD, String name, MembershipType membershipType, LocalDate payDate, String payDateString,
			LocalDate validUntil, String validUntilString, int price, String customerID, Status status,
			int allowedNumber, String sportsObject) {
		super();
		ID = iD;
		this.name = name;
		this.membershipType = membershipType;
		this.payDate = payDate;
		this.payDateString = payDateString;
		this.validUntil = validUntil;
		this.validUntilString = validUntilString;
		this.price = price;
		this.customerID = customerID;
		this.status = status;
		this.allowedNumber = allowedNumber;
		this.sportsObject = sportsObject;
	}
	public Membership(String id,String name,String type, String sportsObject, String createdDate,String validUntil, String price, String customerID, String status,
			String allowedNumber) {
		this.status=Status.INACTIVE;
		if(status=="ACTIVE") this.status=Status.ACTIVE;
		this.name=name;
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
		this.sportsObject=sportsObject;
				
		
		
	}
	public Membership(String iD2,String name, String type,String sportsObject, String cena, String allowedUntil) {
		this.ID=Integer.parseInt(iD2);
		this.name=name;
		switch(type) {
			case "Y": this.membershipType=MembershipType.YEARLY;break;
			case "M": this.membershipType=MembershipType.MONTHLY;break;
			default: this.membershipType=MembershipType.OTHER; }
		this.price=Integer.parseInt(cena);
		this.status=Status.INACTIVE;
		this.allowedNumber=Integer.parseInt(allowedUntil);
		this.customerID="/";this.status=Status.INACTIVE;
		this.sportsObject=sportsObject;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		this.payDate=LocalDate.parse("01.01.0001.",formatter);
		this.validUntil=LocalDate.parse("01.01.0001.",formatter);
		this.payDateString=this.payDate.format(formatter);
		this.validUntilString=this.validUntil.format(formatter);
		
	}
	public String getMembershipString() {
		String s= ID+","+name+","+membershipType+","+sportsObject+","+payDateString+","+validUntilString+","+Integer.toString(price)+","+customerID+","+status.toString()+","+Integer.toString(allowedNumber);
		return s;
	}
	
}
