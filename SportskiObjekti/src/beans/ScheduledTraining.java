package beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScheduledTraining  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
	 * 
	 */

	public ScheduledTraining(LocalDateTime dateTime, String training, String user, String trainer) {
		super();
		this.dateTime = dateTime;
		this.training = training;
		this.user = user;
		this.trainer = trainer;
	}
	
	public ScheduledTraining(String dateTime, String training, String user, String trainer, String sObject) {
		super();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm;dd.MM.yyyy.");
		this.dateTime=LocalDateTime.parse(dateTime,formatter);
		this.dateTimeString = this.dateTime.format(formatter);
		this.training = training;
		this.user = user;
		this.trainer = trainer;
		this.sObject=sObject;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getTraining() {
		return training;
	}
	public void setTraining(String training) {
		this.training = training;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	String training;
	String user;
	String trainer;
	LocalDateTime dateTime;
	String sObject;
	String dateTimeString;
	public String getDateTimeString() {
		return dateTimeString;
	}

	public void setDateTimeString(String dateTimeString) {
		this.dateTimeString = dateTimeString;
	}

	public String getsObject() {
		return sObject;
	}

	public void setsObject(String sObject) {
		this.sObject = sObject;
	}
}
