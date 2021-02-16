package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.Entreprise;
import jags.backend.entities.Participant;
import jags.backend.repositories.ParticipantRepository;

@Service
public class ParticipantService {
	
	@Autowired
	private ParticipantRepository repository;
	
	@Autowired
	private EntrepriseService entrepriseService;
	
	@Autowired
	private Entreprise entreprise;

	public List<Participant> findAll() {
		return this.repository.findAll();
	}

	public Participant save(Participant entity) {
		return this.repository.save(entity);
	}

	public Participant findById(Long participantId) {
		return this.repository.findById(participantId)
							.orElseThrow(() ->  new ResponseStatusException (HttpStatus.NOT_FOUND));
	}

	public List<Participant> findByEntreprise(Long entrepriseId) {
		entreprise = this.entrepriseService.findById(entrepriseId);
//		entreprise.setId(entrepriseId);
		return this.repository.findByEntreprise(entreprise);
	}
}
