package jags.backend.entities;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import jags.backend.entities.composite.key.AssisterCle;

@Entity
@Table(name="Assister")
public class Assister {
	
	@EmbeddedId
	private AssisterCle id;
	
	@ManyToOne
	@MapsId("participantId")
	@JoinColumn(name="Participant_id")
	private Participant participant;
	
	@ManyToOne
	@MapsId("sessionId")
	@JoinColumn(name="Session_id")
	private Session session;
	
	
	private Boolean prerequis;


	public AssisterCle getId() {
		return id;
	}


	public void setId(AssisterCle id) {
		this.id = id;
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


	public Boolean getPrerequis() {
		return prerequis;
	}


	public void setPrerequis(Boolean prerequis) {
		this.prerequis = prerequis;
	}
	
}
