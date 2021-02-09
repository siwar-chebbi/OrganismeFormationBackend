package jags.backend.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Evaluation")
public class Evaluation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NumeroSession", length = 50)
	private String numeroSession;

	@Column(name="Accueil")
	private Integer accueil;

	@Column(name="Environnement")
	private Integer environnement;

	@Column(name="Contenu")
	private Integer contenu;

	@Column(name="Pedagogie")
	private Integer pedagogie;
	
	@Column(name="Maitrise")
	private Integer maitrise;
	
	@Column(name="Disponibilite")
	private Integer disponibilite;
	
	@Column(name="Reponse")
	private Integer reponse;
	
	@Column(name="Animation")
	private Integer animation;

	@Column(name="SouhaitFormation")
	private Boolean souhaitFormation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DateEvaluation")
	private Date dateEvaluation;
	
	@Column(name="Recommandation")
	private Integer recommandation;
	
	@Column(name="Satisfaction")
	private Integer satisfaction;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("id")
	@JoinColumn(name="Participant_id")
	private Participant participant;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("id")
	@JoinColumn(name="Session_id")
	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroSession() {
		return numeroSession;
	}

	public void setNumeroSession(String numeroSession) {
		this.numeroSession = numeroSession;
	}

	public Integer getAccueil() {
		return accueil;
	}

	public void setAccueil(Integer accueil) {
		this.accueil = accueil;
	}

	public Integer getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(Integer environnement) {
		this.environnement = environnement;
	}

	public Integer getContenu() {
		return contenu;
	}

	public void setContenu(Integer contenu) {
		this.contenu = contenu;
	}

	public Integer getPedagogie() {
		return pedagogie;
	}

	public void setPedagogie(Integer pedagogie) {
		this.pedagogie = pedagogie;
	}

	public Integer getMaitrise() {
		return maitrise;
	}

	public void setMaitrise(Integer maitrise) {
		this.maitrise = maitrise;
	}

	public Integer getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Integer disponibilite) {
		this.disponibilite = disponibilite;
	}

	public Integer getReponse() {
		return reponse;
	}

	public void setReponse(Integer reponse) {
		this.reponse = reponse;
	}

	public Integer getAnimation() {
		return animation;
	}

	public void setAnimation(Integer animation) {
		this.animation = animation;
	}

	public Boolean getSouhaitFormation() {
		return souhaitFormation;
	}

	public void setSouhaitFormation(Boolean souhaitFormation) {
		this.souhaitFormation = souhaitFormation;
	}

	public Date getDateEvaluation() {
		return dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public Integer getRecommandation() {
		return recommandation;
	}

	public void setRecommandation(Integer recommandation) {
		this.recommandation = recommandation;
	}

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipantToEvalutaion(Participant participant) {
		this.participant = participant;
	}

	// Getter identique a celui d'au dessus
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
}
