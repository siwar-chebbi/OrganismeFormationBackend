package jags.backend.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssisterCle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="Participant_id")
	private Long participantId;
	
	@Column(name="Session_id")
	private Long sessionId;
}
