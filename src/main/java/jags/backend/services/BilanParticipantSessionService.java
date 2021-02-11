package jags.backend.services;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	//Service
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
	
	//Entities
	@Autowired
	private Entreprise entreprise;
	@Autowired
	private Coordonnee coordonnee;
	@Autowired
	private BilanParticipantSession bilan;
	@Autowired
	private Participant participant;
	@Autowired
	private Session session;
	
	List<String> bodyRequestSplit = new ArrayList<String>();

	/**
	 * Méthode permettant de récuperer tout les objets BilanParticipantSession
	 * @return Une liste contenant tout les objets BilanParticipantSession
	 */
	public List<BilanParticipantSession> findAll(){
		return this.repository.findAll();
	}
	
	/**
	 * Méthode permettant l'inscription d'un utilisateur dit particulier (sans entreprise) à une session
	 * @param participantId id du participant à enregistrer
	 * @param sessionId 	id de la session à enregistrer
	 * @param coordonnee	objet Coordonnee contenant les valeur d'evaluation de la session du participant
	 */
	public void inscriptionSessionParticulier(Long participantId, Long sessionId, Coordonnee coordonneeTemp) {
		//Recuperation session et participant
		getSessionAndParticipant(participantId, sessionId);
		
		// create dans bilanParticipantSession
		setInscriptionBilan(participant, session);
		
		// Appel de méthode
		sauvegardeCoordonneeParticipant(coordonneeTemp);
		
		// update dans lieu
		this.lieuService.save(session.getLieu());
		this.participantService.save(participant);
	}
	
	/**
	 * Méthode permettant l'inscription d'un utilisateur dit ayant une entreprise à une session
	 * @param participantId id du participant à enregistrer
	 * @param sessionId 	id de la session à enregistrer
	 * @param coordonnee	objet Coordonnee contenant les valeur d'evaluation de la session du participant
	 */
	public void inscriptionSessionEntreprise(Long participantId, Long sessionId, String bodyRequest) {
		//Recuperation session et participant
		getSessionAndParticipant(participantId, sessionId);
		
		// create dans bilanParticipantSession
		setInscriptionBilan(participant, session);
		
		// Découpage des infos body
		splitBody(bodyRequest);
		
		// Add / update coordoonnee participant
		getCoordonneeParticipant(bodyRequestSplit);
		sauvegardeCoordonneeParticipant(coordonnee);
				
		// Add coordonnee entreprise + entreprise
		sauvegardeCoordonneeEntreprise();
		
		 //update dans lieu
		this.lieuService.save(session.getLieu());
		
		 //update participant avec l'id entreprise
		participant.setEntreprise(entreprise);
		this.participantService.save(participant);
		
		// A voir front 
		// sauvegarder les coordonnées participant 
		// Si le partcipant à déjà une entreprise
	}
	
	/**
	 * Methode permettant de récupérer les informations sur le participant et la session qu'il choisit
	 * @param participantId l'id du participant recu via url
	 * @param sessionId l'id de la session recu via url
	 */
	public void getSessionAndParticipant(Long participantId, Long sessionId) {
		session = this.sessionService.findById(sessionId);
		participant = this.participantService.findById(participantId);
		
	}
	
	/**
	 * Methode permettant de create / update les coordonnees du participant
	 * @param coordonneeTemp coordonee du participant
	 */
	public void sauvegardeCoordonneeParticipant(Coordonnee coordonneeTemp) {
		
		if(this.coordonneeService.existsCoordonneeByMail(coordonneeTemp.getMail())) {
			// Récupération de l'objet contenu en table par le mail de la requestBody
			coordonnee = this.coordonneeService.findByMail(coordonneeTemp.getMail());
			// Récupération de l'id
			coordonneeTemp.setId(coordonnee.getId());
			this.coordonneeService.save(coordonneeTemp);
			// update participant avec l'id coordonnee
			participant.setCoordonnee(coordonneeTemp);
		} else {
			// create dans coordonnées
			this.coordonneeService.save(coordonneeTemp);
			// update participant avec l'id coordonnee
			participant.setCoordonnee(coordonneeTemp);
		}
	}
	

	/**
	 * Methode permettant de create les coordonnees d'une entreprise
	 */
	public void sauvegardeCoordonneeEntreprise() {
		getDetailsEntreprise(bodyRequestSplit);
		if(!this.entrepriseService.existsEntrepriseBySiret(entreprise.getSiret())) {
			// Create dans entreprise
			this.entrepriseService.save(entreprise);
			// create dans coordonnées
			getCoordonneeEntreprise(bodyRequestSplit);
			coordonnee.setEntreprise(entreprise);
			this.coordonneeService.save(coordonnee);
		} else {
			Entreprise entrepriseTemp = this.entrepriseService.findBySiret(entreprise.getSiret());
			entreprise.setId(entrepriseTemp.getId());
		}
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
	 * Methode permettant de découper le bodyRequest
	 * @param bodyRequest Les données issues du formulaire d'inscription pour un utilisateur ayant une entreprise
	 */
	public void splitBody(String bodyRequest) {
		String parts[] = bodyRequest.split(";");
		for (int i = 0; i < parts.length; i++) {
			bodyRequestSplit.add(i, parts[i]);
		}
	}

	/**
	 * Methode permettant la récupération des coordonnees d'une entreprise à partir des informations provenant du bodyRequest
	 * @param bodyRequestSplit Les informations issues du bodyRequest
	 */
	public void getCoordonneeEntreprise(List<String> bodyRequestSplit) {
		coordonnee = new Coordonnee();
		coordonnee.setCodePostal(bodyRequestSplit.get(0));
		coordonnee.setMail(bodyRequestSplit.get(1));
		coordonnee.setPays(bodyRequestSplit.get(2));
		coordonnee.setNumeroVoie(bodyRequestSplit.get(3));
		coordonnee.setTelephone(bodyRequestSplit.get(4));
		coordonnee.setTypeVoie(bodyRequestSplit.get(5));
		coordonnee.setVille(bodyRequestSplit.get(6));
	}
	
	/**
	 * Methode permettant la récupération des coordonnees d'un participant à partir des informations provenant du bodyRequest
	 * @param bodyRequestSplit Les informations issues du bodyRequest
	 */
	public void getCoordonneeParticipant(List<String> bodyRequestSplit) {
		coordonnee = new Coordonnee();
		coordonnee.setCodePostal(bodyRequestSplit.get(9));
		coordonnee.setMail(bodyRequestSplit.get(10));
		coordonnee.setPays(bodyRequestSplit.get(11));
		coordonnee.setNumeroVoie(bodyRequestSplit.get(12));
		coordonnee.setTelephone(bodyRequestSplit.get(13));
		coordonnee.setTypeVoie(bodyRequestSplit.get(14));
		coordonnee.setVille(bodyRequestSplit.get(15));
	}
	
	/**
	 * Methode permettant la récupération des données d'une entreprise à partir des informations provenant du bodyRequest
	 * @param bodyRequestSplit Les informations issues du bodyRequest
	 */
	public void getDetailsEntreprise(List<String> bodyRequestSplit) {
		entreprise.setSiret(bodyRequestSplit.get(7));
		entreprise.setNom(bodyRequestSplit.get(8));
		
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
		this.repository.save(bilan);
	}
}
