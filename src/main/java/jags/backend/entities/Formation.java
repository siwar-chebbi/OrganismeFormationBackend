package jags.backend.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;


@Entity 
@Component
@Table(name="Formation")
public class Formation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="Numero")
	private Integer numero;
	
	@Column(name ="Titre", length=100)
	private String titre;
	
	@Column(name ="Contenu")
	private String contenu;
	
	@Column(name ="Logiciel", length=45)
	private String logiciel;
	
	@Column(name ="Support", length=45)
	private String support;
	
	@OneToOne(mappedBy = "formation")// donc la table Formation qui gere
    private TestPrerequis testPrerequis;
	
	@ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("id")
	@JsonIgnore
	@JoinColumn(name = "Responsable_id")
	private Responsable responsable;
	
	@OneToMany(mappedBy = "formation")
	private List<Enseigner> experience;
	
	@ManyToMany
	@JoinTable( name = "Contenir", 
	joinColumns = @JoinColumn(name = "Formation_id"), 
	inverseJoinColumns = @JoinColumn (name ="Theme_id"))
	private List<Theme> themes;

	@JsonIgnore
	@OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
	private List<Session> sessions;

	// Getters et Setters
	public Long getId() {
		return id;
	}
	public Integer getNumero() {
		return numero;
	}
	public String getTitre() {
		return titre;
	}
	public String getContenu() {
		return contenu;
	}
	public String getLogiciel() {
		return logiciel;
	}
	public String getSupport() {
		return support;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public void setLogiciel(String logiciel) {
		this.logiciel = logiciel;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	public Responsable getResponsable() {
		return responsable;
	}
	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	public List<Theme> getThemes() {
		return themes;
	}
	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}
	public TestPrerequis getTestPrerequis() {
		return testPrerequis;
	}
	public void setTestPrerequis(TestPrerequis testPrerequis) {
		this.testPrerequis = testPrerequis;
	}
	public List<Enseigner> getExperience() {
		return experience;
	}
	public void setExperience(List<Enseigner> experience) {
		this.experience = experience;
	}
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
}
