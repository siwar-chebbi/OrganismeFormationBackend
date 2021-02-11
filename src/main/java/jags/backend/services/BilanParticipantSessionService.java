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
	private Coordonnee coordonneeParticipant;
	@Autowired
	private BilanParticipantSession bilan;
	@Autowired
	private Participant participant;
	@Autowired
	private Session session;

	private Coordonnee coordonneeEntreprise = coordonneeParticipant;

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
		//lecture dans session
		session = this.sessionService.findById(sessionId);
		participant = this.participantService.findById(participantId);
		// create dans bilanParticipantSession
		setInscriptionBilan(participant, session);
		this.repository.save(bilan);
		// Appel de méthode
		sauvegardeCoordonneeParticipant(coordonneeTemp);
		// update dans lieu
		this.lieuService.save(session.getLieu());
		this.participantService.save(participant);
	}
	
	
	public void sauvegardeCoordonneeParticipant(Coordonnee coordonneeTemp) {
		
		if(this.coordonneeService.existsCoordonneeByMail(coordonneeTemp.getMail())) {
			// Récupération de l'objet contenu en table par le mail de la requestBody
			coordonneeParticipant = this.coordonneeService.findByMail(coordonneeTemp.getMail());
			// Récupération de l'id
			coordonneeTemp.setId(coordonneeParticipant.getId());
			//coordonnee.setId(this.coordonneeService.findIdByMail(coordonneeTemp.getMail()));
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
	 * Méthode permettant l'inscription d'un utilisateur dit ayant une entreprise à une session
	 * @param participantId id du participant à enregistrer
	 * @param sessionId 	id de la session à enregistrer
	 * @param coordonnee	objet Coordonnee contenant les valeur d'evaluation de la session du participant
	 */
	public void inscriptionSessionEntreprise(Long participantId, Long sessionId, String bodyRequest) {
		//lecture
		session = this.sessionService.findById(sessionId);
		participant = this.participantService.findById(participantId);
		// create dans bilanParticipantSession
		System.out.println("setInscriptionBilan");
		setInscriptionBilan(participant, session);
		this.repository.save(bilan);
		// Découpage des infos body
		System.out.println("splitBody");
		splitBody(bodyRequest);
		
		System.out.println("Sauvegarde coordonnee participant :" + coordonneeParticipant.getMail());
		System.out.println("Sauvegarde coordonnee participant :" + coordonneeParticipant.getVille());
		System.out.println("Avant Sauvegarde coordonnee entreprise :" + coordonneeEntreprise.getMail());
		System.out.println("Avant Sauvegarde coordonnee entreprise :" + coordonneeEntreprise.getVille());
		sauvegardeCoordonneeParticipant(coordonneeParticipant);
		System.out.println("Après Sauvegarde coordonnee entreprise :" + coordonneeEntreprise.getMail());
				
		if(!this.entrepriseService.existsEntrepriseBySiret(entreprise.getSiret())) {
			// Create dans entreprise
			System.out.println("If !siret exists");
			
			this.entrepriseService.save(entreprise);
			// create dans coordonnées
			System.out.println("Sauvegarde coordonnee entreprise :" + coordonneeEntreprise.getMail());
			coordonneeEntreprise.setEntreprise(entreprise);
			System.out.println("Sauvegarde coordonnee entreprise :" + coordonneeEntreprise.getMail());
			this.coordonneeService.save(coordonneeEntreprise);
		}
//			 //update dans lieu
//			this.lieuService.save(session.getLieu());
//			 //update participant avec l'id entreprise
			System.out.println("Save participant");
			participant.setEntreprise(entreprise);
			this.participantService.save(participant);

			// A voir front 
			// sauvegarder les coordonnées participant 
			// si existe 
			// sinon	
			// Si le partcipant à déjà une entreprise
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
		System.out.println("Sauvegarde coordonneeEntreprise :" + parts[1]);
		coordonneeEntreprise.setCodePostal(parts[0]);
		coordonneeEntreprise.setMail(parts[1]);
		coordonneeEntreprise.setPays(parts[2]);
		coordonneeEntreprise.setNumeroVoie(parts[3]);
		coordonneeEntreprise.setTelephone(parts[4]);
		coordonneeEntreprise.setTypeVoie(parts[5]);
		coordonneeEntreprise.setVille(parts[6]);
		entreprise.setSiret(parts[7]);
		entreprise.setNom(parts[8]);
		coordonneeParticipant.setCodePostal(parts[9]);
		System.out.println("Sauvegarde coordonneeParticipant :" + parts[10]);
		coordonneeParticipant.setMail(parts[10]);
		coordonneeParticipant.setPays(parts[11]);
		coordonneeParticipant.setNumeroVoie(parts[12]);
		coordonneeParticipant.setTelephone(parts[13]);
		coordonneeParticipant.setTypeVoie(parts[14]);
		coordonneeParticipant.setVille(parts[15]);
		System.out.println("Sauvegarde coordonnee entreprise :" + coordonneeEntreprise.getMail());
		System.out.println("Sauvegarde coordonneeParticipant :" + coordonneeParticipant.getMail());
		
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
