package jags.backend.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Formateur")
public class Formateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Nom",  length=45)
	private String nom;
	
	@Column(name = "Prenom", length=45)
	private String prenom;
	
	@Column(name = "Mail", length=60)
	private String mail;
	
	@Column(name = "Note")
	private Double note;
	
	@Column(name = "Blame")
	private Integer blame;

	@OneToMany(mappedBy = "formateur")
	private List<Enseigner> experience;
		
	// Getters et Setters
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Double getNote() {
		return note;
	}
	public void setNote(Double note) {
		this.note = note;
	}
	public Integer getBlame() {
		return blame;
	}
	public void setBlame(Integer blame) {
		this.blame = blame;
	}
	public List<Enseigner> getExperience() {
		return experience;
	}
	public void setExperience(List<Enseigner> experience) {
		this.experience = experience;
	}
	
}
