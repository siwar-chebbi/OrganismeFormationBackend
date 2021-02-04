package jags.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Entreprise")
public class Entreprise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Nom", length = 60)
	private String nom;

	@Column(name = "Siret",  length = 20)
	private String siret;

	@OneToOne(mappedBy = "entreprise")
	private Cordonnee coordonnee;
	
	@OneToOne(mappedBy = "entreprise")
	private Participant participant;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public Cordonnee getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(Cordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	
	
}
