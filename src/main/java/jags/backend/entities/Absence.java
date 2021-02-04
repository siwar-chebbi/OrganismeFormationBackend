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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Absence")
public class Absence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy:hh:mm:ss")
	private Date date;
	
	@Column(name="Motif")
	private String Motif;
	
	@Column(name="NumeroSession")
	private String numeroSession;

	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    @JoinColumn(name="Participant_id")
    private Participant participant;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMotif() {
		return Motif;
	}

	public void setMotif(String motif) {
		Motif = motif;
	}

	public String getNumeroSession() {
		return numeroSession;
	}

	public void setNumeroSession(String numeroSession) {
		this.numeroSession = numeroSession;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	
	
}
