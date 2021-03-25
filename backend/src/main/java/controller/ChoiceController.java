package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import dao.ChoiceDao;
import dao.FormDao;
import dao.UserDao;
import domain.Choice;
import domain.Form;
import domain.User;


@Path("/choice")
@Produces("application/json")
public class ChoiceController {
	
	FormDao formDao = new FormDao();
	ChoiceDao choiceDao = new ChoiceDao();
	UserDao userDao = new UserDao();
	
	/** Methode POST pour ajouter un sondage
	 * 
	 * @param id
	 * @param choice
	 * @return
	 */
	
	@POST
	@Path("/{choiceId}") 
	@Consumes("application/json")
	public Response addChoice(@PathParam("choiceId") Long id, Choice choice) {
		
		Form form = formDao.findOne(id);
		
		if (form == null) {
			return Response.serverError().entity("Invalide ID").build();
		}
		
		Choice finalChoice = choice;

		finalChoice.setForm(form);
		
		choiceDao.save(finalChoice);
		
		return Response.ok().entity("SUCCESS").build();
	}
	/** Methode POST pour ajouter un choix associe à un sondage
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	
	@POST
	@Path("/add-user/{choiceId}") 
	@Consumes("application/json")
	public Response addUser(@PathParam("choiceId") Long id, User user) {
		
		Form form = formDao.findOne(id);
		
		if (form == null) {
			return Response.serverError().entity("Invalide ID").build();
		}
		
		// Presence de l'organisateur dans le DB
		User testUser = userDao.findOne(user.getEmail());
				
		
		// Verification de la presence de l'organisateur dans le DB
		if (testUser != null ) {
			System.out.print("Utilisateur deja inscrit");
			System.out.print('\n');
			return Response.serverError().entity("Email already used").build();
		} else if (testUser == null) {
			System.out.print("Utilisateur non inscrit");
			System.out.print('\n');
			userDao.save(user);
		}
		form.getParticipants().add(user);
		formDao.update(form);
		return Response.ok().entity("SUCCESS").build();
	}
	
}
