package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	ServletContext ctx;
	
	public SportsObjectService() {
	}
	
	@PostConstruct
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("sportsObjectDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("sportsObjectDAO", new SportsObjectDAO(contextPath));
		}
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportsObject> getAll() {
		SportsObjectDAO dao = (SportsObjectDAO) ctx.getAttribute("sportsObjectDAO");
		return dao.getSportsObjectsCollection();
	}
	
	@POST
	@Path("/getByManager")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getByManager(User manager) {
		SportsObjectDAO dao = (SportsObjectDAO)ctx.getAttribute("productDAO");
		SportsObject s= dao.getByManager(manager.getUsername());
		return Response.status(200).entity(s).build();
	}
	@POST
	@Path("/setSelectedObject")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSelectedObject(SportsObject s) {
		SportsObjectDAO dao = (SportsObjectDAO)ctx.getAttribute("productDAO");
		SportsObject sp=dao.getSportsObject(s.getId());
		ctx.setAttribute("selectedObject", sp);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getSelectedObject")
	@Produces(MediaType.APPLICATION_JSON)
	public SportsObject getSelectedObject() {
		return (SportsObject) ctx.getAttribute("selectedObject");
	}
	
	@POST
	@Path("/addNew")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SportsObject addNew(SportsObject object) {
		SportsObjectDAO dao = (SportsObjectDAO) ctx.getAttribute("productDAO");
		return object;
	}
	
	
	
}
