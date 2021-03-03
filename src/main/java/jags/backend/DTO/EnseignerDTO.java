package jags.backend.DTO;

public class EnseignerDTO {
	
	private Long idFormation;
	private Long idFormateur;
	private Integer experience;
	
	public Long getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(Long idFormation) {
		this.idFormation = idFormation;
	}
	public Long getIdFormateur() {
		return idFormateur;
	}
	public void setIdFormateur(Long idFormateur) {
		this.idFormateur = idFormateur;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	@Override
	public String toString() {
		return "EnseignerDTO [idFormation=" + idFormation + ", idFormateur=" + idFormateur + ", experience="
				+ experience + "]";
	}

	
}
