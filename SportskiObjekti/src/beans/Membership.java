package beans;

import java.time.LocalDateTime;
import java.util.Date;

import utils.ObjectType;

public class Membership {

	public String ID;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
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
	public String getTrainerID() {
		return trainerID;
	}
	public void setTrainerID(String trainerID) {
		this.trainerID = trainerID;
	}
	public Date payDate;
	public Membership(String iD, Date payDate, LocalDateTime validUntil, int cena, String customerID,
			String trainerID) {
		super();
		ID = iD;
		this.payDate = payDate;
		this.validUntil = validUntil;
		this.cena = cena;
		this.customerID = customerID;
		this.trainerID = trainerID;
	}
	public LocalDateTime validUntil;
	public int cena;
	public String customerID;
	public String trainerID;
}
