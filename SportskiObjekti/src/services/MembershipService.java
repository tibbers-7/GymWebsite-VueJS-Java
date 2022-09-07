package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Membership;
import data.MembershipDAO;
import data.SportsObjectDAO;
import data.UserDAO;


@Path("/membership")
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
	@Path("/")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Membership> getAll() {
	    membershipDAO = (MembershipDAO) context.getAttribute("membershipDAO");
	    Collection<Membership> memberships = membershipDAO.getMembershipCollection();
	    Collection<Membership> retVal = new ArrayList<Membership>();
		for (Membership m : memberships) {			
			retVal.add(new Membership(m.getID(), m.getMembershipType(), m.getPayDate(), m.getValidUntil(), m.getPrice(), m.getCustomerID(), m.getStatus(),m.getAllowedNumber()));
		}
		return retVal;
	}
}
	