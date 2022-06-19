package beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.Gender;

public class User {
	
	public User(String username, String password, String name, String last_name, Gender gender, String birthDate) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.gender = gender;
		this.birthDate = birthDate;
	}
	public User(String username2, String password2, String name2, String last_name2, Gender genderEnum, Date date) {
		this.username = username2;
		this.password = password2;
		this.name = name2;
		this.last_name = last_name2;
		this.gender = genderEnum;
		this.birthDate = date.toGMTString();
	}
	private String username;
	private String password;
	private String name;
	private String last_name;
	private utils.Gender gender;
	private String birthDate;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public utils.Gender getGender() {
		return gender;
	}
	public void setGender(utils.Gender gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getUserString() {
		char genderChar;
		if(this.gender==Gender.MALE) genderChar='M'; else genderChar='F';
//		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");  
//		String strDate = dateFormat.format(this.birthDate); 
		String s=this.username+";"+this.password+";"+this.name+";"+this.last_name+";"+genderChar+";"+birthDate;
		return s;
	}
	
}