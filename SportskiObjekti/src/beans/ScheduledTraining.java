package beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import data.utils.TrainingType;

public class ScheduledTraining  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
	 * 
	 */

	
	public ScheduledTraining(String id,String dateTime, String training, String user, String trainer, String sObject, String type) {
		super();
		this.id=Integer.parseInt(id);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm;dd.MM.yyyy.");
		this.dateTime=LocalDateTime.parse(dateTime,formatter);
		this.dateTimeString = this.dateTime.format(formatter);
		this.training = training;
		this.user = user;
		this.trainer = trainer;
		this.sObject=sObject;
		switch(type) {
		case "P":
			this.type=TrainingType.PERSONAL;
			break;
		case "G":
			this.type=TrainingType.GROUP;
			break;
		default:
			this.type=TrainingType.OTHER;
		}
	}
	
	public ScheduledTraining(int id,LocalDateTime dateTime, String training, String user, String trainer, String sObject, TrainingType type) {
		super();
		this.id=id;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm;dd.MM.yyyy.");
		this.dateTime=dateTime;
		this.dateTimeString = this.dateTime.format(formatter);
		this.training = training;
		this.user = user;
		this.trainer = trainer;
		this.sObject=sObject;
		this.type=type;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public TrainingType getType() {
		return type;
	}

	public void setType(TrainingType type) {
		this.type = type;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	int id;
	String training;
	String user;
	String trainer;
	LocalDateTime dateTime;
	String sObject;
	String dateTimeString;
	TrainingType type;
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
	
	public String trainingString() {
		return Integer.toString(id)+","+dateTimeString+","+training+","+user+","+trainer+","+sObject+","+type;
	}
}
