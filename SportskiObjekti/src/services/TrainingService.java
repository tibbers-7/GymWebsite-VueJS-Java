package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.ScheduledTraining;
import beans.SportsObject;
import beans.Training;
import beans.User;
import data.TrainingDAO;

@Path("/trainings")
public class TrainingService {

	@Context
	ServletContext ctx;
	
	public TrainingService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("trainingsDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainingsDAO", new TrainingDAO(contextPath));
		}
	}
	
	@GET
	@Path("/getAllTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getAllTypes() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getTrainingCollection();
	}
	
	@GET
	@Path("/getAllScheduled")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getAllScheduled() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getScheduledTrainingCollection();
	}
	
	@POST
	@Path("/getByObject")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getByObject(User manager) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getTrainingsByObject(manager.getSportsObjectID());
	}
	
	
	
	@POST
	@Path("/getByCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getByCustomer(User customer) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getScheduledTrainingsByCustomer(customer.getUsername());
	}
	
	@POST
	@Path("/getByTrainerPersonal")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getByTrainerPersonal(User trainer) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getPersonalTrainingsByTrainer(trainer.getUsername());
	}
	
	@POST
	@Path("/getByTrainerGroup")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getByTrainerGroup(User trainer) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getGroupTrainingsByTrainer(trainer.getUsername());
	}
}
