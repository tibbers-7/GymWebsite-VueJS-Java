package services;

import java.util.ArrayList;
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
import javax.ws.rs.core.Response;

import beans.Membership;
import beans.User;
import data.MembershipDAO;
import data.SportsObjectDAO;
import data.UserDAO;


@Path("/memberships")
public class MembershipService {

MembershipDAO membershipDAO;
	
	
	@Context
	ServletContext context;
	
	@SuppressWarnings("unused")
	
	@PostConstruct
	public void init() {
		
		if (context.getAttribute("membershipDAO") == null) {
			String contextPath = context.getRealPath("");
			MembershipDAO membershipDAO = new MembershipDAO(contextPath);
			context.setAttribute("membershipDAO", membershipDAO);
		}
	}
	
	@GET
	@Path("/getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Membership> getAll() {
	    membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
	    return membershipDAO.getMembershipCollection();
	}
	
	@POST
	@Path("/getMembership")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMembership(User customer) {
		MembershipDAO membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
		Membership mem=membershipDAO.getByUser(customer.getUsername());
		return Response.status(200).entity(mem).build();

	}
	
}
	