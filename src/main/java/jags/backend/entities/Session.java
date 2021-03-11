package jags.backend.entities;

import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component
@Table(name="Session")
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Numero", length = 50)
	private String numero;
	
	@Column(name="Type")
	private Integer type;
	
	@Column(name="PrixHT")
	private Double prixHT;
	
	@Column(name="Personnalisee")
	private Integer personalisee;
	
	@Column(name="Duree")
	private Integer duree;
	
	@Column(name="Validation")
	private Integer validation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DateDebut")
	private Date dateDebut;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DateFin")
	private Date dateFin;

	@ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("id")
	@JsonIgnore
	@JoinColumn(name="Formation_id")
    private Formation formation;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("id")
	@JoinColumn(name="Lieu_id")
    private Lieu lieu;

	@JsonIgnore
	@OneToMany(mappedBy = "session")
	private List<BilanParticipantSession> bilanParticipantSessions;

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

	public Double getPrixHT() {
		return prixHT;
	}

	public void setPrixHT(Double prixHT) {
		this.prixHT = prixHT;
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

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public List<BilanParticipantSession> getBilanParticipantSessions() {
		return bilanParticipantSessions;
	}

	public void setBilanParticipantSessions(List<BilanParticipantSession> bilanParticipantSessions) {
		this.bilanParticipantSessions = bilanParticipantSessions;
	}

}
