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
import data.UserDAO;

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
	@GET
	@Path("/getByManager")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByManager() {
		SportsObjectDAO dao = (SportsObjectDAO)context.getAttribute("sportsObjectDAO");
		User manager=(User) context.getAttribute("activeUser");
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
	@Path("/addService")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addService(String service) {
		SportsObjectDAO dao = (SportsObjectDAO) context.getAttribute("sportsObjectDAO");
		User manager=(User) context.getAttribute("activeUser");
		SportsObject s= dao.getSportsObject(manager.getSportsObjectID());
		service=service.substring(11,service.lastIndexOf('"'));
		dao.checkService(s.getId(),service);
		dao.addService(s.getId(), service);
		return Response.status(200).entity(s).build();
	}
	
	@POST
	@Path("/removeService")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeService(String service) {
		SportsObjectDAO dao = (SportsObjectDAO) context.getAttribute("sportsObjectDAO");
		User manager=(User) context.getAttribute("activeUser");
		SportsObject s= dao.getSportsObject(manager.getSportsObjectID());
		service=service.substring(11,service.lastIndexOf('"'));
		dao.removeService(s.getId(), service);
		return Response.status(200).entity(s).build();
	}
	
	@POST
	@Path("/addNew")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNew(SportsObject object) {
		SportsObjectDAO dao = (SportsObjectDAO) context.getAttribute("sportsObjectDAO");
		SportsObject fullObj=dao.addSportsObject(object);
		context.setAttribute("newObject", fullObj);
		return Response.status(200).entity(object).build();
	}
	
	@POST
	@Path("/assignManager")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignManager(String username) {
		SportsObject object=(SportsObject)context.getAttribute("newObject");
		UserDAO dao = (UserDAO) context.getAttribute("userDAO");
		dao.assignManager(username, object);
		return Response.status(200).entity(object).build();
	}
	@POST
	@Path("/setActiveManager")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setActiveManager(User manager) {
		context.setAttribute("activeUser", manager);
		return Response.status(200).build();
	}
	
}
