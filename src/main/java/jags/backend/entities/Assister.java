package jags.backend.entities;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import jags.backend.entities.composite.key.AssisterCle;

@Entity
public class Assister {
	
	@EmbeddedId
	private AssisterCle id;
	
//	@ManyToOne
//	@MapsId("participantId")
//	@JoinColumn(name="Participant_id")
//	private Participant participant;
	
	@ManyToOne
	@MapsId("sessionId")
	@JoinColumn(name="Session_id")
	private Session session;
	
	
	private Boolean prerequis;
	
}
