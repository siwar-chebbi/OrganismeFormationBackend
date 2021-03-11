package jags.backend.DTO;

import java.util.List;

public class SessionDetails {

	private String titreFormation;
	private List<String> themes;
	private String contenuFormation;
	private double prix;
	private String lieu;
	public String getTitreFormation() {
		return titreFormation;
	}
	public void setTitreFormation(String titreFormation) {
		this.titreFormation = titreFormation;
	}
	public List<String> getThemes() {
		return themes;
	}
	public void setThemes(List<String> themes) {
		this.themes = themes;
	}
	public String getContenuFormation() {
		return contenuFormation;
	}
	public void setContenuFormation(String contenuFormation) {
		this.contenuFormation = contenuFormation;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	
}
