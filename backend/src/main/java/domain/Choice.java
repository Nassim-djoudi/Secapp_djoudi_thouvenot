package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Choice {
	// Classe de representation d'un choix associee à un sondage dans notre modele
	
	@Id
	@GeneratedValue
	long id;
	
	String debut;
	String fin;
	
	@ManyToOne
	@JsonBackReference
	Form form;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDebut() {
		return debut;
	}
	public void setDebut(String debut) {
		this.debut = debut;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form s) {
		this.form = s;
	}
	
}