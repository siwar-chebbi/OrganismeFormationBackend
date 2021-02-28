package jags.backend.DTO;

import jags.backend.entities.Entreprise;

public class InscriptionParticipantEmploye {
	
	private Long idParticipant;
	private Long idSession;
	private CoordonneeDTO coordonneeParticipant;
	private Entreprise entreprise;
	private CoordonneeDTO coordonneeEntreprise;
	
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
	public CoordonneeDTO getCoordonneeEntreprise() {
		return coordonneeEntreprise;
	}
	public void setCoordonneeEntreprise(CoordonneeDTO coordonneeEntreprise) {
		this.coordonneeEntreprise = coordonneeEntreprise;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
}
