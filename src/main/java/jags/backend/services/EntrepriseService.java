package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.Coordonnee;
import jags.backend.entities.Entreprise;
import jags.backend.entities.Participant;
import jags.backend.repositories.EntrepriseRepository;

@Service
public class EntrepriseService {

	@Autowired
	private EntrepriseRepository repository;
	
	@Autowired
	private Entreprise entreprise;
	
	/**
	 * Recuperation de la liste de toutes les entreprise
	 * @return liste de toute les entreprises
	 */
	public List<Entreprise> findAll(){
		return this.repository.findAll();
	}
	
	/**
	 * Si l'entreprise existe pas, ajout en base de donnees. Sinon mise a jour de celle-ci
	 * @param entreprise entreprise a ajouter / modifier
	 * @return l'entreprise ajoutee / modifiee
	 */
	public Entreprise save(Entreprise entreprise) {
		entrepriseIdSiExiste(entreprise);
		return this.repository.save(entreprise);
	}
	
	/**
	 * Recuperation de l'id d'une entreprise si celle-ci existe
	 * @param entreprise entreprise dont on veut recueprer l'id
	 */
	public void entrepriseIdSiExiste(Entreprise entreprise) {
		if(this.repository.existsEntrepriseBySiret(entreprise.getSiret())) {
			Long id = findIdBySiret(entreprise.getSiret());
			entreprise.setId(id);
		}
	}
	
	/**
	 * Recuperation d'une entreprise a partir d'un numero de siret
	 * @param siret le numero de siret correspond a l'entreprise que l'on cherche
	 * @return l'entreprise correspondant au numero de siret
	 */
	public Entreprise findBySiret(String siret) {
		return this.repository.findBySiret(siret);
	}
	
	/**
	 * Verification de l'existance d'une entreprise a partir d'un numero de siret
	 * @param siret le siret de l'entreprise recherchee
	 * @return booleen, true --> entreprise existe, false --> entreprise existe pas
	 */
	public Boolean existsEntrepriseBySiret(String siret) {
		return this.repository.existsEntrepriseBySiret(siret);
	}

	/**
	 * Recuperation de l'id d'une entreprise a partir du numero de siret
	 * @param siret le siret de l'entreprise dont on recherche l'id
	 * @return l'id de l'entreprise correspondant au numero de siret recherchee
	 */
	public Long findIdBySiret(String siret) {
		entreprise = findBySiret(siret);
		return entreprise.getId();
	}

	/**
	 * Récupération d'une entreprise à partir de son id
	 * @param entrepriseId Id de l'entreprise que l'on recherche
	 * @return l'entreprise qui correspond à l'id par lequel on recherche
	 */
	public Entreprise findById(Long entrepriseId) {
		return this.repository.findById(entrepriseId)
							.orElseThrow(() ->  new ResponseStatusException (HttpStatus.NOT_FOUND));
	}
}
