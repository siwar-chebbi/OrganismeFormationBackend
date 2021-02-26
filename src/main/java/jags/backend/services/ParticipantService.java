package jags.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.BilanParticipantSession;
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
	private BilanParticipantSessionService bilanParticipantSessionService;
	
	@Autowired
	private Entreprise entreprise;

	/**
	 * Récupère tout les participants contenu dans la bdd
	 * @return la liste de tout les participants
	 */
	public List<Participant> findAll() {
		return this.repository.findAll();
	}

	/**
	 * Ajoute un participant en base de donnée 
	 * @param participant participant que l'on veut ajouter
	 * @return le participant ajoutée
	 */
	public Participant save(Participant participant) {
		return this.repository.save(participant);
	}

	/**
	 * Récupération d'un participant par son id
	 * @param participantId Ide du participant que l'on recherche
	 * @return le participant qui correspond à l'id recherché
	 */
	public Participant findById(Long participantId) {
		return this.repository.findById(participantId)
							.orElseThrow(() ->  new ResponseStatusException (HttpStatus.NOT_FOUND));
	}
	
	/**
	 * Permet de récupérer les participants d'une entreprise par l'ID de l'entreprise
	 * @param entrepriseId un entier (Long) corespondant à l'ID de l'entreprise 
	 * @return Une liste de Participant qui appartiennent à l'entreprise dont l'id = entreprise id
	 */
	public List<Participant> findByEntreprise(Long entrepriseId) {
		entreprise = this.entrepriseService.findById(entrepriseId);
		return this.repository.findByEntreprise(entreprise);
	}

	public List<Participant> findParticipantBySessionId(Long sessionId) {
		List<Long> idParticipant = new ArrayList<Long>();
		for (BilanParticipantSession bilan : this.bilanParticipantSessionService.findParticipantBySessionId(sessionId)) {
			idParticipant.add(bilan.getParticipant().getId());
		}
		return this.repository.findAllById(idParticipant);
	}
}
