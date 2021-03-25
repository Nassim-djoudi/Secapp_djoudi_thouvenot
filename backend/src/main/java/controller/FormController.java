package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.FormDao;
import dao.UserDao;
import domain.Form;
import domain.User;


@Path("/")
@Produces("application/json")
public class FormController {

	FormDao formDao = new FormDao();
	UserDao userDao = new UserDao();
	
	/** Methode GET pour obtenir un lien de connexion pour s'inscrire à un sondage
	 * 
	 * @param titre
	 * @param email
	 * @return
	 */
	
	@GET
	@Path("/add-form/get-url") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getString(@QueryParam("titre") String titre, @QueryParam("email") String email)  {

		long id = -1;
		if (!titre.equals("undefined") && !email.equals("undefined")) {

			id = formDao.getIdRequest(titre, email);
		}
		String message = "{" + "\"" + "id" + "\":\"" + "Formulaire invalide" + "\"}";
		if (id > 0) {
			message = "{" + "\"" + "id" + "\":\"" + "http://localhost:4200/choice/" + id + "\"}";
		}
		
		return Response.ok().entity(message).build();
	}
	
	/** Methode GET permettant d'obtenir les parametres d'un sondage à partir d'un mot de passe
	 * et de l'ID d'un sondage. Si secure == false, la méthode utilise un requete SQL pouvant être
	 * attaquée par injection SQL. Si secure == true, la requete est parametree et l'injection SQL
	 * n'est plus possible.
	 * 
	 * @param id
	 * @param password
	 * @param secure
	 * @return
	 */
	
	@GET
	@Path("/logging") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCheckPassword(@QueryParam("id") String id, @QueryParam("password") String password, @QueryParam("secure") Boolean secure)  {

		Form form = null;
		if (!id.equals("undefined") && !password.equals("undefined") && !secure) {
			form = formDao.FormfindInjection(Long.valueOf(id), password);
		} else if (!id.equals("undefined") && !password.equals("undefined") && secure) {
			form = formDao.FormfindSecure(Long.valueOf(id), password);
		}
		
		return Response.ok().entity(form).build();
	}
	
	@POST
	@Path("/add-form") 
	@Consumes("application/json")
	public Response addForm(Form form) {
		
		if (form.getOrganisateur().equals(null)) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{" + "\"" + "id" + "\":\"" + "Invalide User" + "\"}").build();
		}
		
		// Verification de l'adresse email
		if (form.getOrganisateur().getEmail() == "") {
			return Response.status(Response.Status.BAD_REQUEST).entity("{" + "\"" + "id" + "\":\"" + "Invalid Email" + "\"}").build();
		}
		System.out.print(form.getTitre());
		// Verification du titre
		if (form.getTitre() == "") {
			return Response.status(Response.Status.BAD_REQUEST).entity("{" + "\"" + "id" + "\":\"" + "Invalid Title" + "\"}").build();
		}
		
		// Presence de l'organisateur dans le DB
		User testUser = userDao.findOne(form.getOrganisateur().getEmail());
		
		// Verification de la presence de l'organisateur dans le DB
		if (testUser != null && (!testUser.getNom().equals(form.getOrganisateur().getNom()) || !testUser.getPrenom().equals(form.getOrganisateur().getPrenom()))) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{" + "\"" + "id" + "\":\"" + "Email already used" + "\"}").build();
		} else if (testUser == null) {
			userDao.save(form.getOrganisateur());
		}
		
		// Verification d'un sondage ayant le meme titre deja crée par le même utilisateur
		if (formDao.getIdRequest(form.getTitre(), form.getOrganisateur().getEmail()) > 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{" + "\"" + "id" + "\":\"" + "Form already exist" + "\"}").build();
		}
		
		// Sauvegarde du sondage dans la BD
		formDao.save(form);
		
		return Response.ok().entity("{" + "\"" + "id" + "\":\"" + "Form added" + "\"}").build();
	}

}

