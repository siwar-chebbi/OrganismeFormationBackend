package jags.backend.DTO;


public class FormationDTO {
	
	private String contenu;
	private String logiciel;
	private Integer numero;
	private String support;
	private String titre;
	private Long idResponsable;
	private Long idTheme;
	
	// Getters & Setters
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getLogiciel() {
		return logiciel;
	}
	public void setLogiciel(String logiciel) {
		this.logiciel = logiciel;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Long getIdResponsable() {
		return idResponsable;
	}
	public void setIdResponsable(Long idResponsable) {
		this.idResponsable = idResponsable;
	}
	public Long getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(Long idTheme) {
		this.idTheme = idTheme;
	}
	@Override
	public String toString() {
		return "FormationDTO [contenu=" + contenu + ", logiciel=" + logiciel + ", numero=" + numero + ", support="
				+ support + ", titre=" + titre + ", IdResponsable=" + idResponsable + ", IdTheme=" + idTheme + "]";
	}
}
