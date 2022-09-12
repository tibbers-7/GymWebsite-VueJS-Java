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
		this.fullLocation=new Location(location);
		this.location=this.fullLocation.Address;
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
		return id + ", "+ name + ", " + type + ", " + getServicesString() + ", " + isOpen + ", " + fullLocation.toString() + ", " + avgScore + ","
				+ openHrsStr+","+logoStr;
	}
	
	private String id;
	private String name;
	private List<String> services;
	private Boolean isOpen;
	private Location fullLocation;
	private float avgScore;
	private String logoPath;
	private String openHours;
	private String location;
	
	public SportsObject() {
		super();
	}
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
