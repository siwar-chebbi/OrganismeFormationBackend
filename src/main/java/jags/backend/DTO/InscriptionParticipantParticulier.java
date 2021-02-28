package jags.backend.DTO;

public class InscriptionParticipantParticulier {
	
	private Long idParticipant;
	private Long idSession;
	private CoordonneeDTO coordonneeParticipant;
	
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
	public CoordonneeDTO getCoordonneeParticipant() {
		return coordonneeParticipant;
	}
	public void setCoordonneeParticipant(CoordonneeDTO coordonneeParticipant) {
		this.coordonneeParticipant = coordonneeParticipant;
	}
}
