package domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "Forms")
public class Form {
	// Classe de representation d'un sondage dans notre modele
	
	@Id
	@GeneratedValue
	long id;
	
	String titre;
	String lieu;
	String password;

	@ManyToMany
	List<User> participants;
	
	@ManyToOne
	User organisateur;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "form")	
	List<Choice> choixPossible;
	
	@OneToOne
	Choice choixSelectionne;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public User getOrganisateur() {
		return organisateur;
	}

	public void setOrganisateur(User organisateur) {
		this.organisateur = organisateur;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Choice> getChoixPossible() {
		return choixPossible;
	}

	public void setChoixPossible(List<Choice> choixPossible) {
		this.choixPossible = choixPossible;
	}

	public Choice getChoixSelectionne() {
		return choixSelectionne;
	}

	public void setChoixSelectionne(Choice choixSelectionne) {
		this.choixSelectionne = choixSelectionne;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	

}