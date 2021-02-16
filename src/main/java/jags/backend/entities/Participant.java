package jags.backend.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Component
@Table(name = "Participant")
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "Civilite")
	private Boolean civilite;
	
	@Column(name = "Nom", length = 100)
	private String nom;

	@Column(name = "Prenom", length = 100)
	private String prenom;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DateNaissance")
	private Date dateNaissance;

	@ManyToOne
	@JoinColumn(name = "Entreprise_id")
	private Entreprise entreprise;
	
	@OneToOne
	@JoinColumn(name = "Coordonnee_id")
	private Coordonnee coordonnee;

	@OneToMany(mappedBy = "participant")
	private List<BilanParticipantSession> bilanParticipantSessions;
	
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}
}
