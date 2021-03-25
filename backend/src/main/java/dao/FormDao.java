package dao;

import java.util.List;

import domain.Form;

// Dao utilisée pour gerer les objets Form
public class FormDao extends AbstractJpaDao<Long, Form> implements IFormDao {
	
	// Méthode pour obtenir l'ID d'un sondage à partir de son titre et de l'email 
	// de l'organisateur. On a unicité si existence car l'oganisateur ne peut pas
	// créer plusieurs sondages avec le même titre
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
	
	// Méthode pour trouver un sondage à partir de son ID
	@Override
	public Form findOne(Long id) {
		return this.entityManager.find(Form.class, id);	
	}
	
	// Requete pour obtenir un sondage à partir d'un ID et de son mot passe
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
	
	// Même méthode que la précendente en utilisant des requetes parametrees pour eviter les injections
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
