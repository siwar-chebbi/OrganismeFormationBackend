package jags.backend.DTO;

import java.util.Date;

public class SessionDTO {

	private String numero;
	private Integer type;
	private Double prixHT;
	private Integer duree;
	private Date dateDebut;
	private Long idFormation;
	private Long idLieu;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getPrixHT() {
		return prixHT;
	}
	public void setPrixHT(Double prixHT) {
		this.prixHT = prixHT;
	}
	public Integer getDuree() {
		return duree;
	}
	public void setDuree(Integer duree) {
		this.duree = duree;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Long getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(Long idFormation) {
		this.idFormation = idFormation;
	}
	public Long getIdLieu() {
		return idLieu;
	}
	public void setIdLieu(Long idLieu) {
		this.idLieu = idLieu;
	}
	
}
