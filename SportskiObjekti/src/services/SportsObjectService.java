package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
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

import beans.SportsObject;
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
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportsObject> getProducts() {
		SportsObjectDAO dao = (SportsObjectDAO) ctx.getAttribute("sportsObjectDAO");
		return dao.getSportsObjectsCollection();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SportsObject newProduct(SportsObject object) {
		SportsObjectDAO dao = (SportsObjectDAO) ctx.getAttribute("productDAO");
		return object;
	}
	
}
