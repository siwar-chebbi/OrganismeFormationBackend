package jags.backend.DTO;

import java.util.Date;

public class AjoutParticipant {

	private long id;
	private boolean civilite;
	private Date dateNaissance;
	private String nom;
	private String prenom;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean getCivilite() {
		return civilite;
	}
	public void setCivilite(boolean civilite) {
		this.civilite = civilite;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
}
