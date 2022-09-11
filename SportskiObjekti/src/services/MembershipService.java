package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.swing.text.DateFormatter;
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
	@GET
	@Path("/getAvailable")	
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Membership> getAvailable() {
	    membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
	    return membershipDAO.getAvailable();
	}
	
	@POST
	@Path("/getMembership")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMembership(User customer) {
		MembershipDAO membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
		Membership mem=membershipDAO.getByUser(customer.getUsername());
		Membership memOriginal=membershipDAO.getOriginal(mem);
		context.setAttribute("ogMem", memOriginal);
		return Response.status(200).entity(mem).build();

	}
	
	@POST
	@Path("/cancelMembership")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelMembership(User customer) {
		MembershipDAO membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
		membershipDAO.cancelMembership(customer);
		return Response.status(200).build();

	}
	
	@GET
	@Path("/getOriginal")	
	@Produces(MediaType.APPLICATION_JSON)
	public Membership getOriginal() {
	    return (Membership)context.getAttribute("ogMem");
	}
	
	@POST
	@Path("/postUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postUser(User customer) {
		context.setAttribute("currentUser", customer);
		return Response.status(200).build();
	}
	
	@POST
	@Path("/setSelected")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setSelected(Membership mem) {
		context.setAttribute("selectedMem", mem);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getSelected")
	@Produces(MediaType.APPLICATION_JSON)
	public Membership getSelected() {
		return (Membership)context.getAttribute("selectedMem");
	}
	
	@POST
	@Path("/addMembership")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMembership(Membership mem) {
		MembershipDAO membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
		User customer=(User)context.getAttribute("currentUser");
		membershipDAO.addMembership(customer,mem);
		return Response.status(200).build();

	}
	
	
}
	