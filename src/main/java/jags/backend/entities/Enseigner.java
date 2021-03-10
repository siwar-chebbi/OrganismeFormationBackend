package jags.backend.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import jags.backend.entities.composite.key.EnseignerCle;

@Entity
@Component
@Table(name="Enseigner")
public class Enseigner {
	
	@EmbeddedId
	private EnseignerCle id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	//@MapsId("idFormateur")
	@JoinColumn(name="Formateur_id", nullable=false, insertable = false, updatable = false)
	private Formateur formateur;
	
	@ManyToOne(fetch = FetchType.EAGER)
	//@MapsId("idFormation")
	@JoinColumn(name="Formation_id", nullable=false, insertable = false, updatable = false)
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
	@Override
	public String toString() {
		return "Enseigner [id=" + id + ", formateurID=" + formateur.getId() + ", formationID=" + formation.getId() + ", experience="
				+ experience + "]";
	}
	
	
	

}
