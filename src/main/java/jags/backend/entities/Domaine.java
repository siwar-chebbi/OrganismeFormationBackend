package jags.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.MessageFormat;

@Entity
@Table(name="Domaine")
public class Domaine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Nom", length=45)
	private String Nom;
	
	@Column(name = "Description")
	private String Description;

	// Getters et Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return MessageFormat.format("Domaine{id={0}, Nom={1}, Description={2}}", id, Nom, Description);
	}
}
