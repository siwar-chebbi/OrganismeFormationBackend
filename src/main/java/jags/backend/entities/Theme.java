package jags.backend.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Theme")
public class Theme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Numero")
	private Integer Numero;
	
	@Column(name = "Nom", length=50)
	private String Nom;
	
	@ManyToOne
	@JoinColumn(name="Domaine_id")
	private Domaine domaine;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ThemeParent_id")
	private Theme themeParent;

	@OneToMany(mappedBy="themeParent")
	private List<Theme> themes = new ArrayList<Theme>();

	// Getters et Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumero() {
		return Numero;
	}
	public void setNumero(Integer numero) {
		Numero = numero;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public Domaine getDomaine() {
		return domaine;
	}
	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}
	public Theme getThemeParent() {
		return themeParent;
	}
	public void setThemeParent(Theme themeParent) {
		this.themeParent = themeParent;
	}
	public List<Theme> getThemes() {
		return themes;
	}
	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}
	@Override
	public String toString() {
		return "Theme [id=" + id + ", Numero=" + Numero + ", Nom=" + Nom + ", domaine=" + domaine + ", themeParent="
				+ themeParent + ", themes=" + themes + "]";
	}
	
	
	
	
	

}
