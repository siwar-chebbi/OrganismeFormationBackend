package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.Coordonnee;
import jags.backend.repositories.CoordonneeRepository;

@Service
public class CoordonneeService {
	
	@Autowired
	private CoordonneeRepository repository;
	
	@Autowired
	private Coordonnee coordonnee;

	public List<Coordonnee> findAll() {
		return repository.findAll();
	}
	
	/**
	 * Si la coordonnee n'existe pas, ajout en base de donnees. Si elle existe, update de celle-ci.
	 * @param coordonnee les coordonne a create / update
	 * @return les coordonnées à jour en table
	 */
	public Coordonnee save(Coordonnee coordonnee) {
		coordoonneeIdSiExiste(coordonnee);
		return this.repository.save(coordonnee);
	}
	
	/**
	 * Recupere l'id d'une coordonnee si celle-ci existe en base de donnees
	 * @param coordonnee la coordonne dont on cherche l'id
	 */
	public void coordoonneeIdSiExiste(Coordonnee coordonnee) {
		if(this.repository.existsCoordonneeByMail(coordonnee.getMail())) {
			System.out.println(" dans if exist, avant find id");
			Long id = findIdByMail(coordonnee.getMail());
			System.out.println(" avant set id");
			coordonnee.setId(id);
		}
	}
	
	/**
	 * Recuperation d'une coordonnee a partir d'une adresse mail
	 * @param mail le mail de la coordonnee a rechercher
	 * @return
	 */
	public Coordonnee findByMail(String mail) {
		return repository.findByMail(mail);
	}

	/**
	 * Verification de l'existance d'une coordonne a partir d'une adresse mail
	 * @param mail le mail de la coordonnee recherchee
	 * @return booleen, true --> coordonnee existe, false --> coordonnee n'existe pas
	 */
	public Boolean existsCoordonneeByMail(String mail) {
		return repository.existsCoordonneeByMail(mail);
	}

	/**
	 * Recuperation de l'id d'une entreprise a partir d'une adresse mail
	 * @param mail le mail de la coordonnee dont on cherche l'id
	 * @return
	 */
	public Long findIdByMail(String mail) {
		coordonnee = findByMail(mail);
		return coordonnee.getId();
		
	}
}
