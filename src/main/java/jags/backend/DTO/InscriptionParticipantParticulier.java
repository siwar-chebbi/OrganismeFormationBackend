package jags.backend.DTO;

public class InscriptionParticipantParticulier {
	
	private Long idParticipant;
	private Long idSession;
	private CoordonneeDTO coordonnee;
	
	public Long getIdParticipant() {
		return idParticipant;
	}
	public void setIdParticipant(Long idParticipant) {
		this.idParticipant = idParticipant;
	}
	public Long getIdSession() {
		return idSession;
	}
	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}
	public CoordonneeDTO getCoordonnee() {
		return coordonnee;
	}
	public void setCoordonnee(CoordonneeDTO coordonnee) {
		this.coordonnee = coordonnee;
	}
	@Override
	public String toString() {
		return "InscriptionParticipantParticulier [idParticipant=" + idParticipant + ", idSession=" + idSession
				+ ", coordonnee=" + coordonnee.toString() + "]";
	}
	
	
	
}
