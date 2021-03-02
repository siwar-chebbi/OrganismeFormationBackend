package jags.backend.DTO;

import java.util.List;

public class SessionsParticipant {

	private List<Long> idSessions;
	private long idParticipant;
	private List<String> numerosEvaluationSession;
	
	
	
	public List<Long> getIdSessions() {
		return idSessions;
	}
	public void setIdSessions(List<Long> idSessions) {
		this.idSessions = idSessions;
	}
	public long getIdParticipant() {
		return idParticipant;
	}
	public void setIdParticipant(long idParticipant) {
		this.idParticipant = idParticipant;
	}
	public List<String> getNumerosEvaluationSession() {
		return numerosEvaluationSession;
	}
	public void setNumerosEvaluationSession(List<String> numerosEvaluationSession) {
		this.numerosEvaluationSession = numerosEvaluationSession;
	}
	
	
}
