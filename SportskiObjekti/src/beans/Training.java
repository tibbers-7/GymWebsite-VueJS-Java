package beans;

import java.io.Serializable;

public class Training  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String name;
public Training(String name, String type, String sObject, String duration, String trainerID, String description,
		String image) {
	super();
	this.name = name;
	this.type = type;
	this.sObject = sObject;
	this.duration = duration;
	this.trainerID = trainerID;
	this.description = description;
	this.image = image;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getsObject() {
	return sObject;
}
public void setsObject(String sObject) {
	this.sObject = sObject;
}
public String getDuration() {
	return duration;
}
public void setDuration(String duration) {
	this.duration = duration;
}
public String getTrainerID() {
	return trainerID;
}
public void setTrainerID(String trainerID) {
	this.trainerID = trainerID;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
String type;
String sObject;
String duration;
String trainerID;
String description;
String image;

}
