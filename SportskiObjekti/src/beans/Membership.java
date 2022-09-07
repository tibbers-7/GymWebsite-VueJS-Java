package beans;

import java.io.Serializable;
import java.time.LocalDate;
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
	
	MembershipType membershipType;
	LocalDate payDate;
	LocalDate validUntil;
	int price;
	String customerID;
	Status status;
	int allowedNumber;
	String ID;
	
	//yearly & monthly membership -> validUntil=null
	
	public Membership(String id,String type, String createdDate,String validUntil, String price, String customerID, String status,
			String allowedNumber) {
		this.status=Status.INACTIVE;
		if(status=="A") this.status=Status.ACTIVE;
		
		
		this.payDate=LocalDate.parse(createdDate);
				
		this.membershipType=MembershipType.OTHER;
		if (type=="Y") {
			this.validUntil=this.payDate.plusYears(1);
			this.membershipType=MembershipType.YEARLY;
		}
		if (type=="M") {
			this.validUntil=this.payDate.plusMonths(1);
			this.membershipType=MembershipType.MONTHLY;
		}
		
		this.customerID=customerID;
		this.price=Integer.parseInt(price);
		this.allowedNumber=Integer.parseInt(allowedNumber);
				
		
		
	}
	public String getMembershipString() {
		String s= ID+","+payDate+","+validUntil+","+price+","+customerID+","+status.toString()+","+allowedNumber;
		return s;
	}
	
}
