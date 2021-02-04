package jags.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="Responsable")
public class Responsable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="Nom")
	private String nom;
	
	@Column(name ="Prenom")
	private String prenom;
	
	@Column(name ="Role")
	private String role;
	
	@Column(name ="Mail")
	private String mail;
	
	@Column(name ="Telephone")
	private String telephone;
	
	@OneToMany
	private List<Formation> formations = new ArrayList<>();

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
