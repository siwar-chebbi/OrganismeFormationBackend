package jags.backend.DTO;

import java.text.MessageFormat;

public class ThemeDTO {

	private Long id;
	private Integer Numero;
	private String Nom;

	public Long getId() {
		return id;
	}

	public Integer getNumero() {
		return Numero;
	}

	public String getNom() {
		return Nom;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(Integer numero) {
		Numero = numero;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	@Override
	public String toString() {
		return MessageFormat.format("ThemeDTO'{'{0}id=, Numero={1}, Nom={2}'}'", id, Numero, Nom);
	}
}
