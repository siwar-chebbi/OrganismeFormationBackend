package jags.backend.DTO;

import java.text.MessageFormat;
import java.util.List;

public class FormationDTO {
	
	private Long id;
	private String contenu;
	private String logiciel;
	private Integer numero;
	private String support;
	private String titre;
	private Long idResponsable;
	private List<Long> idTheme;
	
	// Getters & Setters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public List<Long> getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(List<Long> idTheme) {
		this.idTheme = idTheme;
	}

	@Override
	public String toString() {
		return MessageFormat.format("FormationDTO'{'id={0}, contenu=''{1}'', logiciel=''{2}'', numero={3}, support=''{4}'', titre=''{5}'', idResponsable={6}, idTheme={7}'}'", id, contenu, logiciel, numero, support, titre, idResponsable, idTheme);
	}
}
