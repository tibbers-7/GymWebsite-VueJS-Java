package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import utils.ObjectType;

public class SportsObject {
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
	public SportsObject(String name, ObjectType type, List<String> services, Boolean isOpen, String location,
			float avgScore,  String openHours) {
		super();
		this.name = name;
		this.type = type;
		this.services = services;
		this.isOpen = isOpen;
		this.location = location;
		this.avgScore = avgScore;
		//this.logoPath = logoPath;
		this.openHours = openHours;
	}
	
	public SportsObject(String name, String type, String servicesString, String isOpen, String location,
			String avgScore,  String openHours) {
		super();
		this.name = name;
		this.type = ObjectType.valueOf(type);
		
		this.services=new ArrayList<>();
		String[] servicesList=servicesString.split(",");
		for(String s: servicesList){
			this.services.add(s);
		}
		if(isOpen.equals("Y")) this.isOpen = true; else this.isOpen=false;
		this.location = location;
		this.avgScore = Float.parseFloat(openHours);
		//this.logoPath = logoPath;
		this.openHours = openHours;
	}
	private List<String> services;
	private Boolean isOpen;
	private String location;
	private float avgScore;
	private String logoPath;
	private String openHours;
	public String getSportsObjectString() {
		String servicesString="";
		for(String s:services) {
			servicesString=servicesString+s+";";
		}
		char isOpen_;
		if(isOpen) isOpen_='Y'; else isOpen_='N';
		String s=name+";"+type.toString()+";"+servicesString+isOpen_+";"+location+";"+Float.toString(avgScore)+";"+openHours;
		return s;
	}
}
