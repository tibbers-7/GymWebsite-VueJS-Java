package services;

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


import beans.User;
import data.UserDAO;


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
		UserDAO UserDAO = (UserDAO) context.getAttribute("users");
		return UserDAO.getUserCollection();
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
	@Path("/loginstat")
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

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User userToLogIn) {

		HttpSession session = request.getSession();

		if (userToLogIn.getUsername() == null || userToLogIn.getPassword() == null
				|| userToLogIn.getUsername().equals("") || userToLogIn.getPassword().equals("")) {
			return Response.status(400).entity("Prilikom logovanja unesite korisnicko ime i sifru!").build();

		}
		
		UserDAO UserDAO = (UserDAO) context.getAttribute("users");

		if (UserDAO.searchUser(userToLogIn.getUsername()) != null) {

			User user = UserDAO.searchUser(userToLogIn.getUsername());

			if (user.getPassword().equals(userToLogIn.getPassword()) == true) {
				session.setAttribute("user", user);
				return Response.status(200).build();
			} else {
				return Response.status(400).entity("Pogresan password!").build();
			}
		}

		if (session.getAttribute("user") != null) {
			return Response.status(400).entity("Vec ste ulogovani!").build();
		} else {
			return Response.status(400).entity("Logovanje nije uspesno!").build();
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
		
		UserDAO UserDAO = (UserDAO) context.getAttribute("users");

//		if (UserDAO.searchUser(userToRegister.getUsername()) != null) {
//			return Response.status(400).entity("Username koji ste uneli vec je zauzet.").build();
//		} else {
			User user=new User(userToRegister.getUsername(),userToRegister.getPassword(),userToRegister.getName(),userToRegister.getLast_name(),userToRegister.getGender(),userToRegister.getBirthDate());
			UserDAO.addUser(user);
			return Response.status(200).build();
		//}
	}
	

	@PostConstruct
	public void init() {
		
		if (context.getAttribute("users") == null) {
			String contextPath = context.getRealPath("");
			UserDAO UserDAO = new UserDAO(contextPath);
			context.setAttribute("users", UserDAO);
		}
	
	}

}
