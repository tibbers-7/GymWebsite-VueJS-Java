package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.SportsObject;
import beans.User;
import data.SportsObjectDAO;

@Path("/sportsobjects")
public class SportsObjectService {
	
	@Context
	ServletContext context;
	
	public SportsObjectService() {
	}
	
	@PostConstruct
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (context.getAttribute("sportsObjectDAO") == null) {
	    	String contextPath = context.getRealPath("");
			context.setAttribute("sportsObjectDAO", new SportsObjectDAO(contextPath));
		}
	}
	@POST
	@Path("/getByManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByManager(User manager) {
		SportsObjectDAO dao = (SportsObjectDAO)ctx.getAttribute("sportsObjectDAO");
		SportsObject s= dao.getSportsObject(manager.getSportsObjectID());
		return Response.status(200).entity(s).build();
	}
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportsObject> getAll() {
		SportsObjectDAO dao = (SportsObjectDAO) context.getAttribute("sportsObjectDAO");
		return dao.getSportsObjectsCollection();
	}
	@POST
	@Path("/getContent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getContent(SportsObject s) {
		return Response.status(200).entity(s.getServices()).build();
	}
	@POST
	@Path("/setSelectedObject")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSelectedObject(SportsObject s) {
		context.setAttribute("selectedObject", s);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getSelectedObject")
	@Produces(MediaType.APPLICATION_JSON)
	public SportsObject getSelectedObject() {
		return (SportsObject) context.getAttribute("selectedObject");
	}
	
	@GET
	@Path("/getTypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> getTypes() {
		SportsObjectDAO dao = (SportsObjectDAO) context.getAttribute("sportsObjectDAO");
		return dao.getTypes();
	}
	
	@POST
	@Path("/addNew")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNew(SportsObject object) {
		SportsObjectDAO dao = (SportsObjectDAO) context.getAttribute("sportsObjectDAO");
		dao.addSportsObject(object);
		return Response.status(200).entity(object).build();
	}
	
	
	
}
