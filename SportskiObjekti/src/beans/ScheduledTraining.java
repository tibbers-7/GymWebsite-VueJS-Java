package beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ScheduledTraining  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -8778761921916943988L;
LocalDateTime dateTime;
public ScheduledTraining(LocalDateTime dateTime, Training training, User user, User trainer) {
	super();
	this.dateTime = dateTime;
	this.training = training;
	this.user = user;
	this.trainer = trainer;
}
public LocalDateTime getDateTime() {
	return dateTime;
}
public void setDateTime(LocalDateTime dateTime) {
	this.dateTime = dateTime;
}
public Training getTraining() {
	return training;
}
public void setTraining(Training training) {
	this.training = training;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public User getTrainer() {
	return trainer;
}
public void setTrainer(User trainer) {
	this.trainer = trainer;
}
Training training;
User user;
User trainer;
}
