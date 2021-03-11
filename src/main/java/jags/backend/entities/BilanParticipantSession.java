package jags.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="BilanParticipantSession")
public class BilanParticipantSession {

	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="Absence")
	private Double absence;

	@Column(name="NumeroSessionEval", length = 50)
	private String numeroSessionEval ;

	@Column(name="Prerequis")
	private Boolean prerequis;

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
	
	@Column(name="Recommandation")
	private Integer recommandation;
	
	@Column(name="Satisfaction")
	private Integer satisfaction;

	@ManyToOne
	@JoinColumn(name = "Participant_id")
	private Participant participant;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Session_id")
	private Session session;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAbsence() {
		return absence;
	}

	public void setAbsence(Double absence) {
		this.absence = absence;
	}

	public Boolean getPrerequis() {
		return prerequis;
	}

	public void setPrerequis(Boolean prerequis) {
		this.prerequis = prerequis;
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

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getNumeroSessionEval() {
		return numeroSessionEval;
	}

	public void setNumeroSessionEval(String numeroSessionEval) {
		this.numeroSessionEval = numeroSessionEval;
	}
	
}
