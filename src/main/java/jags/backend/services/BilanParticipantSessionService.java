package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.BilanParticipantSession;
import jags.backend.entities.Coordonnee;
import jags.backend.entities.Participant;
import jags.backend.entities.Session;
import jags.backend.repositories.BilanParticipantSessionRepository;

@Service
public class BilanParticipantSessionService {

	@Autowired
	private BilanParticipantSessionRepository repository;
	@Autowired
	private CoordonneeService coordonneeService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private ParticipantService participantService;
	@Autowired
	private LieuService lieuService;
	
	public List<BilanParticipantSession> findAll(){
		return this.repository.findAll();
	}
	
	public void inscriptionSession(Long participantId, Long sessionId, Coordonnee coordonnee) {
		//lecture dans session
		Session session = this.sessionService.findById(sessionId);
		Participant participant = this.participantService.findById(participantId);
		// create dans bilanParticipantSession
		BilanParticipantSession bilan = new BilanParticipantSession();
		bilan.setParticipant(participant);
		bilan.setSession(session);
		bilan.setNumeroSessionEval(session.getNumero());
		this.repository.save(bilan);
		// create dans coordonnées
		this.coordonneeService.save(coordonnee);
		// update dans lieu
		this.lieuService.save(session.getLieu());
	}
}
