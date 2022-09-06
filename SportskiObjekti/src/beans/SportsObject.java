package beans;

import java.io.Serializable;
import java.util.List;

import data.utils.ObjectType;

public class SportsObject  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4048385338677087921L;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ObjectType getType() {
		return type;
	}
	public void setType(ObjectType type) {
		this.type = type;
	}
	public List<Content> getServices() {
		return contents;
	}
	public void setServices(List<Content> services) {
		this.contents = services;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getOpenHours() {
		return openHours;
	}
	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}
	private ObjectType type;
	public SportsObject(String name, ObjectType type, List<Content> contents, Boolean isOpen, String location,
			float avgScore, String logoPath, String openHours) {
		super();
		this.name = name;
		this.type = type;
		this.contents = contents;
		this.isOpen = isOpen;
		this.location = location;
		this.avgScore = avgScore;
		this.logoPath = logoPath;
		this.openHours = openHours;
	}

	@Override
	public String toString() {
		String logoStr=" ",openHrsStr=" ";
		if (isOpen==null) isOpen=false;
		if (logoPath!=null) logoStr=logoPath.substring(7);
		if (openHours!=null) openHrsStr=openHours;
		return id + ", "+ name + ", " + type + ", " + getServicesString() + ", " + isOpen + ", " + location + ", " + avgScore + ","
				+ openHrsStr+","+logoStr;
	}
	
	private String id;
	private String name;
	private List<String> services;
	private Boolean isOpen;
	private String location;
	private float avgScore;
	private String logoPath;
	private String openHours;
	public String getServicesString() {
		
		if(services==null) servicesString=" ";
		else {
			servicesString="";
			for(String s:services) {
				servicesString=servicesString+s+"-";
			}
		}
		return servicesString;
	}
	public void setContentString(String contentString) {
		
		
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String servicesString;
}
