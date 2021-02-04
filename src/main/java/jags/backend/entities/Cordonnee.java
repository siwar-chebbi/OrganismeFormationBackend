package jags.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Coordonnee")
public class Cordonnee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NumeroVoie")
	private String numeroVoie;
	
	@Column(name = "TypeVoie")
	private String typeVoie;
	
	@Column(name = "Ville")
	private String ville;
	
	@Column(name = "CodePostal") 
	private String codePostal;
	
	@Column(name = "Pays")
	private String pays;
	
	@Column(name = "Mail")
	private String mail;
	
	@Column(name = "Telephone")
	private String telephone;

	@OneToOne
	@JoinColumn(name = "Entreprise_id")
	private Entreprise entreprise;
	
	@OneToOne(mappedBy = "cordonnee")
	private Participant participant;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroVoie() {
		return numeroVoie;
	}

	public void setNumeroVoie(String numeroVoie) {
		this.numeroVoie = numeroVoie;
	}

	public String getTypeVoie() {
		return typeVoie;
	}

	public void setTypeVoie(String typeVoie) {
		this.typeVoie = typeVoie;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
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
	
}
