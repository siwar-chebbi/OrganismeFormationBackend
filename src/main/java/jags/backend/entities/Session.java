package jags.backend.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="Session")
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Numero")
	private String numero;
	
	@Column(name="Type")
	private Integer type;
	
	@Column(name="Prix")
	private Integer prix;
	
	@Column(name="Personnalisee")
	private Integer personalisee;
	
	@Column(name="Duree")
	private Integer duree;
	
	@Column(name="Validation")
	private Integer validation;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DateDebut")
	@DateTimeFormat(pattern = "dd-MM-yyyy:hh:mm:ss")
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DateFin")
	@DateTimeFormat(pattern = "dd-MM-yyyy:hh:mm:ss")
	private Date dateFin;
	
	@OneToMany(mappedBy = "session")
	private List<Assister> prerequis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPersonalisee() {
		return personalisee;
	}

	public void setPersonalisee(Integer personalisee) {
		this.personalisee = personalisee;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public Integer getValidation() {
		return validation;
	}

	public void setValidation(Integer validation) {
		this.validation = validation;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public List<Assister> getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(List<Assister> prerequis) {
		this.prerequis = prerequis;
	}

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}
	
}
