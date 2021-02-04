package jags.backend.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name="TestPrerequis")
public class TestPrerequis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="Numero")
	private Integer numero;
	
	@Column(name ="Lien")
	private String lien;
	
	
	@OneToOne(cascade = CascadeType.ALL)//valable sur tt les actions sur le parent
    @JoinColumn(name = "formation_id")
    private Formation formation;
	

	public Long getId() {
		return id;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getLien() {
		return lien;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	

}
