package services;

import java.time.LocalDate;
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
import beans.SportsObject;
import beans.User;
import data.MembershipDAO;
import data.SportsObjectDAO;
import data.UserDAO;
import data.utils.MembershipType;


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
	@Path("/checkMembership")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkMembership(SportsObject object) {
		MembershipDAO membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
		User user=(User)context.getAttribute("currentUser");
		int errorNum=membershipDAO.checkMembership(object.getId(),user);
		String res="";
		switch(errorNum) {
			case 0:
				res="Validna članarina!";
				return Response.status(200).entity(res).build();
			case 1:res="Nemate nijednu aktivnu članarinu!";break;
			case 2:res="Nemate važeću članarinu u odabranom objektu!";break;
		}
		return Response.status(400).entity(res).build();
	}
	
	@POST
	@Path("/setSelected")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setSelected(String s) {
		MembershipDAO membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
		s=s.substring(s.indexOf(':')+1,s.lastIndexOf('}'));
		System.out.println(s);
		Membership mem=membershipDAO.getByID(s);
		User user=(User)context.getAttribute("currentUser");
		mem.setCustomerID(user.getName());
		context.setAttribute("selectedMem", mem);
		System.out.println(mem.toString());
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
	public Response addMembership() {
		MembershipDAO membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
		User customer=(User)context.getAttribute("currentUser");
		Membership membership=(Membership)context.getAttribute("selectedMem");
		membership.setPayDate(LocalDate.now());
		if(membership.getMembershipType()==MembershipType.YEARLY)
			membership.setValidUntil(LocalDate.now().plusYears(1));
		else membership.setValidUntil(LocalDate.now().plusMonths(1));
		membershipDAO.addMembership(customer,membership);
		return Response.status(200).build();

	}
	
	
}
	