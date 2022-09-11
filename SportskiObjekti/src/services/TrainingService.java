package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	public Collection<String> getAllTypes() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getTypes();
	}
	
	@GET
	@Path("/getAllScheduled")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getAllScheduled() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getScheduledTrainingCollection();
	}
	
	@GET
	@Path("/getByManager")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getByManager() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		User manager=(User)ctx.getAttribute("manager");
		return dao.getTrainingsByObject(manager.getSportsObjectID());
	}
	
	
	
	@POST
	@Path("/getByCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getByCustomer(User customer) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getScheduledTrainingsByCustomer(customer.getUsername());
	}
	
	@POST
	@Path("/getByTrainerPersonal")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getByTrainerPersonal(User trainer) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getPersonalTrainingsByTrainer(trainer.getUsername());
	}
	
	@POST
	@Path("/addTraining")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addTraining(ScheduledTraining training) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.addScheduledTraining(training);
	}
	
	@POST
	@Path("/getByTrainerGroup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getByTrainerGroup(User trainer) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getGroupTrainingsByTrainer(trainer.getUsername());
	}
	
	@POST
	@Path("/getByObject")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getByObject(SportsObject s) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getTrainingsByObject(s.getId());
	}
	
	
	@POST
	@Path("/cancelTraining")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelTraining(ScheduledTraining t) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		Collection<ScheduledTraining> trainings;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm;dd.MM.yyyy.");
		t.setDateTime(LocalDateTime.parse(t.getDateTimeString(), formatter));
		trainings= dao.cancelTraining(t);
		return Response.status(200).entity(trainings).build();
	}
	
	@POST
	@Path("/setActiveManager")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setActiveManager(User manager) {
		ctx.setAttribute("manager", manager);
		return Response.status(200).build();
	}
}
