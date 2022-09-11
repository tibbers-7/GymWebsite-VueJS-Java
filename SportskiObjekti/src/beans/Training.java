package beans;

import java.io.Serializable;

public class Training  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String name;
public Training(String id,String name, String type, String sObject, String duration, String trainerID, String description,
		String image) {
	super();
	this.id=Integer.parseInt(id);
	this.name = name;
	this.type = type;
	this.sObject = sObject;
	this.duration = duration;
	this.trainerID = trainerID;
	this.description = description;
	this.image = image;
}

public String getString() {
	//1,trening1,GROUP,0,15,tibbers, ,x
	if(description==null) description=" ";
	if(image==null) image="x";
	String ret=Integer.toString(id)+","+name+","+type+","+sObject+","+duration+","+trainerID+","+description+","+image;
	return ret;
	
}


public Training() {
	super();
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
int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

}
