package jags.backend.entities;


import java.util.HashSet;
import java.util.Set;

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
	
	@Column(name = "Nom")
	private String Nom;
	
	@ManyToOne
	@JoinColumn(name="Domaine_id")
	private Domaine domaine;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ThemeParent_id")
	private Theme ThemeParent;

	@OneToMany(mappedBy="ThemeParent")
	private Set<Theme> Theme = new HashSet<Theme>();

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
		return ThemeParent;
	}
	public void setThemeParent(Theme themeParent) {
		ThemeParent = themeParent;
	}
	public Set<Theme> getTheme() {
		return Theme;
	}
	public void setTheme(Set<Theme> theme) {
		Theme = theme;
	}
	
	
	
	

}
