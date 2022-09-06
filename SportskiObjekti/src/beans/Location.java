package beans;

import java.io.Serializable;

public class Location implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 2645676152211063851L;
double GeoWidth;
public double getGeoWidth() {
	return GeoWidth;
}
public void setGeoWidth(double geoWidth) {
	GeoWidth = geoWidth;
}
public double getGeoLength() {
	return GeoLength;
}
public void setGeoLength(double geoLength) {
	GeoLength = geoLength;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public Location(double geoWidth, double geoLength, String address) {
	super();
	GeoWidth = geoWidth;
	GeoLength = geoLength;
	Address = address;
}
double GeoLength;
String Address;
}
