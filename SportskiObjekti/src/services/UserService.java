package services;

import java.io.Console;
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

import beans.Membership;
import beans.SportsObject;
import beans.User;
import data.MembershipDAO;
import data.UserDAO;
import data.utils.CustomerType;
import data.utils.UserType;


@Path("/user")
public class UserService {

	@Context
	ServletContext context;

	@Context
	HttpServletRequest request;

	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getUsers() {
		UserDAO userDAO = (UserDAO) context.getAttribute("userDAO");
		return userDAO.getUserCollection();
	}
	
	
		
	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout() {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user != null) {
			session.invalidate();
			return Response.status(200).build();
		}
		else {
			return Response.status(400).entity("User je vec izlogovan!").build();
		}
	}

	@GET
	@Path("/loggedinstatus")
	@Produces(MediaType.APPLICATION_JSON)
	public User loginStat() {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			return user;
		} else {
			return null;
		}
	}
	
	@GET
	@Path("/getFreeManagers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getFreeManagers() {
		UserDAO userDAO = (UserDAO) context.getAttribute("userDAO");
		return userDAO.getFreeManagers();
		
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User userToLogIn) {
		
		try {
		HttpSession session = request.getSession();

		if (userToLogIn.getUsername() == null || userToLogIn.getPassword() == null
				|| userToLogIn.getUsername().equals("") || userToLogIn.getPassword().equals("")) {
			return Response.status(400).entity("Prilikom logovanja unesite korisnicko ime i sifru!").build();

		}
		
		
		UserDAO userDAO = (UserDAO) context.getAttribute("userDAO");

		if (userDAO.searchUser(userToLogIn.getUsername()) != null) {

			User user = userDAO.searchUser(userToLogIn.getUsername());

			if (user.getPassword().equals(userToLogIn.getPassword()) == true) {
				session.setAttribute("activeUser", user);
				
				return Response.status(200).entity(user.getUserType().toString()).build();
			} else {
				return Response.status(400).entity("Pogresan password!").build();
			}
		}

		if (session.getAttribute("activeUser") != null) {
			return Response.status(400).entity("Vec ste ulogovani!").build();
		} else {
			return Response.status(400).entity("Logovanje nije uspesno!").build();
		}} catch(Exception e) {
			return Response.status(400).entity("error!").build();
			
		}
		
		
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(User userToRegister) {

		if (userToRegister.getUsername() == null || userToRegister.getPassword() == null
				 || userToRegister.getUsername().equals("")
				|| userToRegister.getPassword().equals("")) {
			return Response.status(400).entity("Username, password i email su obavezna polja.").build();
		}
		
		UserDAO userDAO = (UserDAO) context.getAttribute("userDAO");

		if (userDAO.searchUser(userToRegister.getUsername()) != null) {
			return Response.status(400).entity("Username koji ste uneli vec je zauzet.").build();
		} else {
			userDAO.registerCustomer(userToRegister);
			return Response.status(200).entity("Uspe�no kreiran nalog!").build();
		}
	}
	
	@POST
	@Path("/registerManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerManager(User userToRegister) {

		if (userToRegister.getUsername() == null || userToRegister.getPassword() == null
				 || userToRegister.getUsername().equals("")
				|| userToRegister.getPassword().equals("")) {
			return Response.status(400).entity("Username, password i email su obavezna polja.").build();
		}
		
		UserDAO userDAO = (UserDAO) context.getAttribute("userDAO");

		if (userDAO.searchUser(userToRegister.getUsername()) != null) {
			return Response.status(400).entity("Username koji ste uneli vec je zauzet.").build();
		} else {
			userDAO.registerManager(userToRegister);
			context.setAttribute("manager", userToRegister);
			return Response.status(200).entity(userToRegister).build();
		}
	}
	
	@POST
	@Path("/registerTrainer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerTrainer(User userToRegister) {

		if (userToRegister.getUsername() == null || userToRegister.getPassword() == null
				 || userToRegister.getUsername().equals("")
				|| userToRegister.getPassword().equals("")) {
			return Response.status(400).entity("Username, password i email su obavezna polja.").build();
		}
		
		UserDAO userDAO = (UserDAO) context.getAttribute("userDAO");

		if (userDAO.searchUser(userToRegister.getUsername()) != null) {
			return Response.status(400).entity("Username koji ste uneli vec je zauzet.").build();
		} else {
			userDAO.registerTrainer(userToRegister);
			return Response.status(200).entity("Uspe�no kreiran nalog!").build();
		}
	}
	
	@POST
	@Path("/editInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editInfo(User userToEdit) {

		UserDAO userDAO = (UserDAO) context.getAttribute("userDAO");
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("activeUser");
		user.setName(userToEdit.getName());
		user.setLast_name(userToEdit.getLast_name());
		user.setBirthDate(userToEdit.getBirthDate());
		userDAO.editUser(user);
		context.setAttribute("activeUser", user);
			return Response.status(200).entity("Uspe�no promenjen nalog!").build();
	}
	
	@POST
	@Path("/rememberMembership")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response rememberMembership(Membership mem) {
		
		context.setAttribute("currentMembership", mem);
		return Response.status(200).build();
	}
	
	@POST
	@Path("/checkMembership")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkMembership(Membership ogMem) {

		UserDAO userDAO = (UserDAO) context.getAttribute("userDAO");
		Membership mem=(Membership)context.getAttribute("currentMembership");
		String isValid=userDAO.checkMembership((User)context.getAttribute("activeCustomer"),mem,ogMem);
		return Response.status(200).entity(isValid).build();
	}
	

	
	@GET
	@Path("/activeUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getActiveUser() {
		
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("activeUser");
		return user;
		
	}
	
	@GET
	@Path("/getTrainers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getTrainers() {
		UserDAO dao = (UserDAO) context.getAttribute("userDAO");
		return dao.getTrainers();
		
	}
	
	@PostConstruct
	public void init() {
		
		if (context.getAttribute("userDAO") == null) {
			String contextPath = context.getRealPath("");
			UserDAO UserDAO = new UserDAO(contextPath);
			context.setAttribute("userDAO", UserDAO);
		}
	
	}
	
	@POST
	@Path("/assignManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignManager(SportsObject s) {
		UserDAO dao = (UserDAO) context.getAttribute("userDAO");
		User manager=(User)context.getAttribute("manager");
		dao.assignManager(manager,s);
		return Response.status(200).build();
	}
	
	
	
	
	

}
