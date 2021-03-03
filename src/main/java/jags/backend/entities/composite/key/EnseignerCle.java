package jags.backend.entities.composite.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnseignerCle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="Formation_id")
	private Long formationId;
	
	@Column(name="Formateur_id")
	private Long formateurId;

	public Long getFormationId() {
		return formationId;
	}
	public void setFormationId(Long formationId) {
		this.formationId = formationId;
	}
	public Long getFormateurId() {
		return formateurId;
	}
	public void setFormateurId(Long formateurId) {
		this.formateurId = formateurId;
	}
	

}
