package dao;

import domain.Form;

//Interface associee a la dao de l'objet Form
public interface IFormDao extends IGenericDao<Long, Form>{
	public long getIdRequest(String titre, String email);
	public Form FormfindInjection(Long id, String password);
	public Form FormfindSecure(Long id, String password);
}
