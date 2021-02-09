package jags.backend.services;

import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.BilanParticipantSession;
import jags.backend.entities.Coordonnee;
import jags.backend.entities.Entreprise;
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
	@Autowired
	private EntrepriseService entrepriseService;
	
	private Entreprise entreprise = new Entreprise();
	private Coordonnee coordonnee = new Coordonnee();
	private BilanParticipantSession bilan = new BilanParticipantSession();
	
	
	public List<BilanParticipantSession> findAll(){
		return this.repository.findAll();
	}
	
	/**
	 * Méthode permettant l'inscription d'un utilisateur dit particulier (sans entreprise) à une session
	 * @param participantId id du participant à enregistrer
	 * @param sessionId 	id de la session à enregistrer
	 * @param coordonnee	objet Coordonnee contenant les valeur d'evaluation de la session du participant
	 */
	public void inscriptionSessionParticulier(Long participantId, Long sessionId, Coordonnee coordonnee) {
		//lecture dans session
		Session session = this.sessionService.findById(sessionId);
		Participant participant = this.participantService.findById(participantId);
		// create dans bilanParticipantSession
		setInscriptionBilan(participant, session);
		this.repository.save(bilan);
		// create dans coordonnées
		this.coordonneeService.save(coordonnee);
		// update dans lieu
		this.lieuService.save(session.getLieu());
		// update participant avec l'id coordonnee
		participant.setCoordonnee(coordonnee);
		this.participantService.save(participant);
	}
	
	/**
	 * Méthode permettant l'inscription d'un utilisateur dit ayant une entreprise à une session
	 * @param participantId id du participant à enregistrer
	 * @param sessionId 	id de la session à enregistrer
	 * @param coordonnee	objet Coordonnee contenant les valeur d'evaluation de la session du participant
	 */
	public void inscriptionSessionEntreprise(Long participantId, Long sessionId, String bodyRequest) {
		//lecture
		Session session = this.sessionService.findById(sessionId);
		Participant participant = this.participantService.findById(participantId);
		// create dans bilanParticipantSession
		bilan.setParticipant(participant);
		bilan.setSession(session);
		bilan.setNumeroSessionEval(session.getNumero());
		this.repository.save(bilan);
		// Découpage des infos body
		splitBody(bodyRequest);
		// Create dans entreprise
		this.entrepriseService.save(entreprise);
		// create dans coordonnées
		coordonnee.setEntreprise(entreprise);
		this.coordonneeService.save(coordonnee);
		// update dans lieu
		this.lieuService.save(session.getLieu());
		// update participant avec l'id entreprise
		participant.setEntreprise(entreprise);
		this.participantService.save(participant);
	}
	
	/**
	 * Methode permettant d'enregistrer l'evaluation d'une session d'un participant
	 * @param bilanParticipantSession objet contenant les valeurs de l'evalaution du participant 
	 */
	public void evaluationSession(BilanParticipantSession bilanParticipantSession) {
		bilan = this.repository.findByParticipantIdAndSessionId(bilanParticipantSession.getParticipant().getId(), bilanParticipantSession.getSession().getId());
		bilanParticipantSession.setId(bilan.getId());
		this.repository.save(bilanParticipantSession);
	}
	
	/**
	 * Methode permettant de découper le body en deux objets coordonnee et entreprise, pour la methode inscriptionSessionEntreprise
	 * @param bodyRequest Les données issues du formulaire d'inscription pour un utilisateur ayant une entreprise
	 */
	public void splitBody(String bodyRequest) {
		String[] parts = bodyRequest.split(";");
		coordonnee.setCodePostal(parts[0]);;
		coordonnee.setMail(parts[1]);;
		coordonnee.setPays(parts[2]);;
		coordonnee.setNumeroVoie(parts[3]);;
		coordonnee.setTelephone(parts[4]);;
		coordonnee.setTypeVoie(parts[5]);;
		coordonnee.setVille(parts[6]);;
		entreprise.setSiret(parts[7]);
		entreprise.setNom(parts[8]);
	}
	
	/**
	 * Methode permettant de renseigner les valeur requise pour l'inscription d'un participant à une session
	 * @param participant 	l'objet participant pour retrouver son id
	 * @param session 		l'objet session pour retrouver son id
	 */
	public void setInscriptionBilan(Participant participant, Session session) {
		bilan.setParticipant(participant);
		bilan.setSession(session);
		bilan.setNumeroSessionEval(session.getNumero());
	}
}
