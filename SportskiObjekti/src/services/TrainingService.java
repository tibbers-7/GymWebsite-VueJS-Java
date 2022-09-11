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
		User manager=(User)ctx.getAttribute("activeUser");
		return dao.getTrainingsByObject(manager.getSportsObjectID());
	}
	
	
	
	@GET
	@Path("/getByCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getByCustomer() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		User customer=(User)ctx.getAttribute("activeUser");
		return dao.getScheduledTrainingsByCustomer(customer.getUsername());
	}
	
	@GET
	@Path("/getTrainers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> getTrainers() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		User manager=(User)ctx.getAttribute("activeUser");
		return dao.getTrainers(manager.getSportsObjectID());
	}
	
	@GET
	@Path("/getByTrainerPersonal")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getByTrainerPersonal() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		User trainer=(User)ctx.getAttribute("activeUser");
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
	@Path("/addNewTraining")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewTraining(Training training) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		dao.addTraining(training);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getByTrainerGroup")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ScheduledTraining> getByTrainerGroup() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		User trainer=(User)ctx.getAttribute("activeUser");
		return dao.getGroupTrainingsByTrainer(trainer.getUsername());
	}
	
	@POST
	@Path("/getByObject")
	@Consumes(MediaType.APPLICATION_JSON)
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
	@Path("/setActiveUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setActiveUser(User user) {
		ctx.setAttribute("activeUser", user);
		return Response.status(200).build();
	}
}
