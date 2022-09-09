package beans;

import java.io.Serializable;
import java.util.List;

import data.utils.ObjectType;

public class SportsObject  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4048385338677087921L;
	
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
	public List<String> getServices() {
		return services;
	}
	public void setServices(List<String> services) {
		this.services = services;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public SportsObject(String id,String name, ObjectType type, List<String> services, Boolean isOpen, String location,
			float avgScore, String logoPath, String openHours) {
		super();
		this.id=id;
		this.name = name;
		this.type = type;
		this.services = services;
		this.isOpen = isOpen;
		this.location = location;
		this.avgScore = avgScore;
		this.logoPath = logoPath;
		this.openHours = openHours;
	}

	@Override
	public String toString() {
		return id + ", "+ name + ", " + type + ", " + services + ", " + isOpen + ", " + location + ", " + avgScore + ", "
				+ logoPath + ", " + openHours;
	}
	
	private String id;
	private String name;
	private List<String> services;
	private Boolean isOpen;
	private String location;
	private float avgScore;
	private String logoPath;
	private String openHours;
	public SportsObject() {
		super();
	}
	public String getServicesString() {
		return services.toString();
	}
	public void setServicesString(String servicesString) {
		String[] strings = servicesString.split(",");
		for(String s : strings)
			services.add(s);
		 	
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String servicesString;
}
