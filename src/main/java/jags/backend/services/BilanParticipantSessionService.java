package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.BilanParticipantSession;
import jags.backend.entities.Session;
import jags.backend.repositories.BilanParticipantSessionRepository;

@Service
public class BilanParticipantSessionService {

	@Autowired
	private BilanParticipantSessionRepository repository;
	private CoordonneeService coordonneeService;
	private SessionService sessionService;
	private LieuService lieuService;
	
	public List<BilanParticipantSession> findAll(){
		return this.repository.findAll();
	}
	
	public void inscriptionSession(Long participantId, Long sessionId, Coordonnee coordonnee) {
		//lecture dans session
		Session session = this.sessionService.findById(sessionId);
		// create dans bilanParticipantSession
		this.repository.saveBilanParticipantSession(session.getNumero(), participantId, sessionId);
		// create dans coordonnées
		this.coordonneeService.save(coordonnee);
		// update dans lieu
		this.lieuService.save(session.getLieu());
	}
}
