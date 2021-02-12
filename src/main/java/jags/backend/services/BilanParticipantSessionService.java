package jags.backend.services;

import java.util.ArrayList;
import java.util.List;
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
	 * Recuperation de tous les bilans de la base de donnees
	 * @return Une liste contenant tout les bilans contenu dans la base de donnees
	 */
	public List<BilanParticipantSession> findAll(){
		return this.repository.findAll();
	}
	
	/**
	 * Inscription d'un participant particulier, ne possedant pas d'entreprise, a une session 
	 * @param participantId : id du participant, requis pour l'insertion dans la table bilan
	 * @param sessionId : id de la session, requis pour l'insertion dans la table bilan
	 * @param coordonnee : les coordonnes du particpant a ajouter ou mettre a jour dans la base de donnees
	 */
	public void inscriptionSessionParticulier(Long participantId, Long sessionId, Coordonnee coordonnee) {
		traitementBilanEtCoordonneeParticipant(participantId, sessionId, coordonnee);
		this.lieuService.save(session.getLieu());
		this.participantService.save(participant);
	}
	
	/**
	 * Traitement pour l'inscription d'un nouveau bilan en base de donnee
	 * @param participantId : Id du participant a inscrire dans le bilan
	 * @param sessionId : Id de la session a inscrire dans le bilan
	 */
	public void traitementBilanEtCoordonneeParticipant(Long participantId, Long sessionId, Coordonnee coordonnee) {
		recuperationSessionEtParticipantParId(participantId, sessionId);
		alimentationBilanParticipantEtSession();
		creationBilan();
		sauvegardeCoordonneeParticipant(coordonnee);
	}
	
	/**
	 * Alimentation du bilan avec le participant (id) et la session (id et numero de session)
	 */
	public void alimentationBilanParticipantEtSession() {
		bilan.setParticipant(participant);
		bilan.setSession(session);
		bilan.setNumeroSessionEval(session.getNumero());
	}
	
	/**
	 * Inscription d'un participant, ayant une entreprise, a une session
	 * @param participantId : id du participant, requis pour l'insertion dans la table bilan
	 * @param sessionId : id de la session, requis pour l'insertion dans la table bilan
	 * @param bodyRequest String contenant les coordonnes du participant, de l'entreprise
	 *  et les informations de l'entreprise
	 */
	public void inscriptionSessionEntreprise(Long participantId, Long sessionId, String bodyRequest) {
		splitBody(bodyRequest);
		getCoordonneeParticipant(bodyRequestSplit);
		traitementBilanEtCoordonneeParticipant(participantId, sessionId, coordonnee);
		sauvegardeEntrepriseParticipant();
		this.lieuService.save(session.getLieu());
		this.participantService.save(participant);
	}
	
	/**
	 * Sauvegarde d'une entreprise en base de donnees et recuperation de son Id
	 */
	public void sauvegardeEntrepriseParticipant() {
		updateEntreprise();
		participant.setEntreprise(entreprise);
		sauvegardeCoordonneeEntreprise();
	}
	
	/**
	 * Recuperation du participant et de la session en fonction de leur ID
	 * @param participantId : Id du participant a recuperer
	 * @param sessionId : Id de la session a recuperer
	 */
	public void recuperationSessionEtParticipantParId(Long participantId, Long sessionId) {
		session = this.sessionService.findById(sessionId);
		participant = this.participantService.findById(participantId);
		
	}
	
	/**
	 * Sauvegarde des coordonnee du participant en base de donnee et recuperation de son ID
	 * @param coordonneeTemp coordonee du participant a sauvegarder
	 */
	public void sauvegardeCoordonneeParticipant(Coordonnee coordonnee) {
		updateCoordonnee(coordonnee);
		// update participant avec l'id coordonnee
		participant.setCoordonnee(coordonnee);
	}
	
	/**
	 * Methode sauvegardant les coordonnees en base de donnees et de recuperer son id
	 * @param coordonnee les coordonnes a sauvegarder en base de donnees
	 */
	public void updateCoordonnee(Coordonnee coordonnee) {
		this.coordonneeService.save(coordonnee);
		Long id = this.coordonneeService.findIdByMail(coordonnee.getMail());
		coordonnee.setId(id);
	}

	/**
	 * Sauvegarde des coordonnees de l'entreprise en base de donnees 
	 */
	public void sauvegardeCoordonneeEntreprise() {
		getCoordonneeEntreprise(bodyRequestSplit);
		coordonnee.setEntreprise(entreprise);
		this.coordonneeService.save(coordonnee);
	}
	
	/**
	 * Creation / mise a jours des informations de l'entreprise en base de donnees et recuperation de son ID
	 */
	public void updateEntreprise() {
		recupererDetailsEntreprise(bodyRequestSplit);
		this.entrepriseService.save(entreprise);
		Long id = this.entrepriseService.findIdBySiret(entreprise.getSiret());
		entreprise.setId(id);
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
	 * Methode permettant de découper le string contenant les coordonnee du participant et de l'entreprise
	 *  ainsi que les details de l'entreprise en element separes
	 * @param bodyRequest String contenant les coordonnes du participant, de l'entreprise
	 *  et les informations de l'entreprise
	 */
	public List<String> splitBody(String bodyRequest) {
		String parts[] = bodyRequest.split(";");
		for (int i = 0; i < parts.length; i++) {
			bodyRequestSplit.add(i, parts[i]);
		}
		return bodyRequestSplit;
	}
	
	/**
	 * Recuperation des coordonnees d'un participant à partir des informations provenant du bodyRequest
	 * @param bodyRequestSplit Liste contenant les elements lies aux coordonnees et details de l'entreprise
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
	 * Recuperation des coordonnees d'une entreprise à partir des informations provenant du bodyRequest
	 * @param bodyRequestSplit Liste contenant les elements lies aux coordonnees et details de l'entreprise
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
	 * Recuperation des details d'une entreprise à partir des informations provenant du bodyRequest
	 * @param bodyRequestSplit Liste contenant les elements lies aux coordonnees et details de l'entreprise
	 */
	public void recupererDetailsEntreprise(List<String> bodyRequestSplit) {
		entreprise.setSiret(bodyRequestSplit.get(7));
		entreprise.setNom(bodyRequestSplit.get(8));
		
	}
	
	/**
	 * Ajout du bilan en base de donnees 
	 */
	public void creationBilan() {
		this.repository.save(bilan);
	}
}
