package jags.backend.DTO;

public class ResumeInscription {

	private long id;
	private String numeroSessionEval;
	private String nomParticipant;
	private String prenomParticipant;
	private boolean existeDeja;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumeroSessionEval() {
		return numeroSessionEval;
	}
	public void setNumeroSessionEval(String numeroSessionEval) {
		this.numeroSessionEval = numeroSessionEval;
	}
	public String getNomParticipant() {
		return nomParticipant;
	}
	public void setNomParticipant(String nomParticipant) {
		this.nomParticipant = nomParticipant;
	}
	public String getPrenomParticipant() {
		return prenomParticipant;
	}
	public void setPrenomParticipant(String prenomParticpant) {
		this.prenomParticipant = prenomParticpant;
	}

	public boolean isExisteDeja() {
		return existeDeja;
	}

	public void setExisteDeja(boolean existeDeja) {
		this.existeDeja = existeDeja;
	}
}
