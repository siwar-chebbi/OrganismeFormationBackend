package jags.backend.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

@Entity 
@Component
@Table(name="Responsable")
public class Responsable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Civilite")
	private Boolean civilite;
	
	@Column(name ="Nom", length=45)
	private String nom;
	
	@Column(name ="Prenom", length=45)
	private String prenom;
	
	@Column(name ="Role", length=80)
	private String role;
	
	@Column(name ="Mail", length=80)
	private String mail;
	
	@Column(name ="Telephone", length=25)
	private String telephone;
	
	@Column(name = "Identifiant", length = 100)
	private String identifiant;
	
	@Column(name = "Mdp", length = 100)
	private String mdp;

	@JsonIgnore
	@OneToMany(mappedBy = "responsable")
	private List<Formation> formations;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public Boolean getCivilite() {
		return civilite;
	}
	public void setCivilite(Boolean civilite) {
		this.civilite = civilite;
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

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}	
}
