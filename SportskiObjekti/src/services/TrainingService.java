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

import beans.SportsObject;
import beans.Training;
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
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getAll() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getTrainingCollection().values();
	}
	
	@POST
	@Path("/getByObject")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getByObjectId(SportsObject s) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingsDAO");
		return dao.getTrainingsByObject(s.getId());
	}
}
