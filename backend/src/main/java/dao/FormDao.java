package dao;

import java.util.List;

import domain.Form;

// Dao utilis�e pour gerer les objets Form
public class FormDao extends AbstractJpaDao<Long, Form> implements IFormDao {
	
	// M�thode pour obtenir l'ID d'un sondage � partir de son titre et de l'email 
	// de l'organisateur. On a unicit� si existence car l'oganisateur ne peut pas
	// cr�er plusieurs sondages avec le m�me titre
	@Override
	public long getIdRequest(String titre, String email) {
		String sqlRequest = "Select f " + "from Form f " + "where f.titre=:titre";
		List<Form> forms = this.entityManager.createQuery(sqlRequest, Form.class)
				.setParameter("titre", titre).getResultList();
		
		long id = -1;
		
		for(Form form : forms) {
			if (form.getOrganisateur().getEmail().equals(email)) {
				id = form.getId();
			}
		}
		
		return id;
	}
	
	// M�thode pour trouver un sondage � partir de son ID
	@Override
	public Form findOne(Long id) {
		return this.entityManager.find(Form.class, id);	
	}
	
	// Requete pour obtenir un sondage � partir d'un ID et de son mot passe
	// La requete est elementaire et peut etre victime d'injection SQL
	@Override
	public Form FormfindInjection(Long id, String password) {
		String sqlRequest = "Select f " + "from Form f " + "where f.id=" + id + " and (f.password=" + "\'" + password  + "\')";
		List<Form> forms = this.entityManager.createQuery(sqlRequest, Form.class).getResultList();
		if (forms.size() > 0) {
			return forms.get(0);
		}
		return null;
	}
	
	// M�me m�thode que la pr�cendente en utilisant des requetes parametrees pour eviter les injections
	// SQL
	@Override
	public Form FormfindSecure(Long id, String password) {
		String sqlRequest = "Select f " + "from Form f " + "where f.id=:id" + " and f.password=:password";
		List<Form> forms = this.entityManager.createQuery(sqlRequest, Form.class)
				.setParameter("id", id)
				.setParameter("password", password)
				.getResultList();
		if (forms.size() > 0) {
			return forms.get(0);
		}
		return null;
	}


}
