package jags.backend.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Lieu")
public class Lieu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Nom", length=45)
	private String nom;
	
	@Column(name="Salle", length=45)
	private String salle;

	@OneToMany(mappedBy = "lieu")
	private List<Session> session;

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

	public String getSalle() {
		return salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public List<Session> getSession() {
		return session;
	}

	public void setSession(List<Session> session) {
		this.session = session;
	}
	
}
