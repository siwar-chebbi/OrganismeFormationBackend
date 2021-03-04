package jags.backend.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ResponsableDTO {

	private Long id;
	private String nom;
	private String prenom;
	private String role;
	private String mail;
	private String telephone;
	
	public Long getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getRole() {
		return role;
	}
	public String getMail() {
		return mail;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
