package jags.backend.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import jags.backend.entities.composite.key.EnseignerCle;

@Entity
public class Enseigner {
	
	@EmbeddedId
	private EnseignerCle id;
	
	@ManyToOne
	@MapsId("formateurId")
	@JoinColumn(name="Formateur_id")
	private Formateur formateur;
	
	@ManyToOne
	@MapsId("formationId")
	@JoinColumn(name="Formation_id")
	private Formation formation;
	
	private Integer experience;

	// Getters et Setters
	public EnseignerCle getId() {
		return id;
	}
	public void setId(EnseignerCle id) {
		this.id = id;
	}
	public Formateur getFormateur() {
		return formateur;
	}
	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	
	

}
