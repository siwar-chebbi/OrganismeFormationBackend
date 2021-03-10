package jags.backend.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.DTO.CoordonneeDTO;
import jags.backend.DTO.InscriptionParticipantEmploye;
import jags.backend.DTO.InscriptionParticipantParticulier;
import jags.backend.DTO.ResumeInscription;
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
	 * Alimentation de l'objet coordonnee provenant de coordonneeDTO
	 * @param coordonneeDTO Donnée reçu du front end (formulaire inscription session particulier)
	 */
	public void coordonneeDtoToCoordonnee(CoordonneeDTO coordonneeDTO) {
		
		this.coordonnee = new Coordonnee();
		this.coordonnee.setCodePostal(coordonneeDTO.getCodePostal());
		this.coordonnee.setMail(coordonneeDTO.getMail());
		this.coordonnee.setNumeroVoie(coordonneeDTO.getNumeroVoie());
		this.coordonnee.setPays(coordonneeDTO.getPays());
		this.coordonnee.setTelephone(coordonneeDTO.getTelephone());
		this.coordonnee.setTypeVoie(coordonneeDTO.getTypeVoie());
		this.coordonnee.setVille(coordonneeDTO.getVille());
	}
	
	/**
	 * Inscription d'un participant particulier, ne possedant pas d'entreprise, a une session 
	 * @param participantId : id du participant, requis pour l'insertion dans la table bilan
	 * @param sessionId : id de la session, requis pour l'insertion dans la table bilan
	 * @param coordonnee : les coordonnes du particpant a ajouter ou mettre a jour dans la base de donnees
	 */
	public ResumeInscription inscriptionSessionParticulier(InscriptionParticipantParticulier particulier) {
		traitementBilanEtCoordonneeParticipant(particulier.getIdParticipant(), particulier.getIdSession(), coordonnee);
		coordonneeDtoToCoordonnee(particulier.getCoordonneeParticipant());
		this.lieuService.save(session.getLieu());
		this.participantService.save(participant);
		return alimentationResumeInscription();
	}
	
	public ResumeInscription alimentationResumeInscription() {
		ResumeInscription resume = new ResumeInscription();
		resume.setId(this.bilan.getId());
		resume.setNomParticipant(participant.getNom());
		resume.setPrenomParticipant(participant.getPrenom());
		resume.setNumeroSessionEval(this.session.getNumero());
		return resume;
	}
	
	/**
	 * Traitement pour l'inscription d'un nouveau bilan en base de donnee
	 * @param participantId : Id du participant a inscrire dans le bilan
	 * @param sessionId : Id de la session a inscrire dans le bilan
	 */
	public void traitementBilanEtCoordonneeParticipant(Long participantId, Long sessionId, Coordonnee coordonnee) {
		recuperationSessionEtParticipantParId(participantId, sessionId);
		alimentationBilanParticipantEtSession();
		
		// Si l'objet bilan n'existe pas alors le crée
		// (aussi dis permet de vérifier que le participant n'est pas déjà inscrit à une session)
		if (!findByParticipantIdAndSessionId(participantId,sessionId)) {
			creationBilan();
			sauvegardeCoordonneeParticipant(coordonnee);
		}
		
	}
	
	public Boolean findByParticipantIdAndSessionId(Long participantId, Long sessionId) {
		 
		if (this.repository.findByParticipantIdAndSessionId(participantId, sessionId) == null) {
			return false; 
		}
		return true;
	}
	
	/**
	 * Alimentation du bilan avec le participant (id) et la session (id et numero de session)
	 */
	public void alimentationBilanParticipantEtSession() {
		this.bilan = new BilanParticipantSession();
		this.bilan.setParticipant(participant);
		this.bilan.setSession(session);
		this.bilan.setNumeroSessionEval(session.getNumero());
	}
	
	/**
	 * Inscription d'un participant, ayant une entreprise, a une session
	 * @param participantId : id du participant, requis pour l'insertion dans la table bilan
	 * @param sessionId : id de la session, requis pour l'insertion dans la table bilan
	 * @param bodyRequest String contenant les coordonnes du participant, de l'entreprise
	 *  et les informations de l'entreprise
	 */
	public ResumeInscription inscriptionSessionEntreprise(InscriptionParticipantEmploye employe) {
		coordonneeDtoToCoordonnee(employe.getCoordonneeParticipant());
		traitementBilanEtCoordonneeParticipant(employe.getIdParticipant(), employe.getIdSession(), coordonnee);
		coordonneeDtoToCoordonnee(employe.getCoordonneeEntreprise());
		sauvegardeEntrepriseParticipant(employe.getEntreprise());
		this.lieuService.save(session.getLieu());
		this.participantService.save(participant);
		return alimentationResumeInscription();
	}
	
	/**
	 * Sauvegarde d'une entreprise en base de donnees et recuperation de son Id
	 * @param nouvelleEntreprise 
	 */
	public void sauvegardeEntrepriseParticipant(Entreprise nouvelleEntreprise) {
		updateEntreprise(nouvelleEntreprise);
		participant.setEntreprise(this.entreprise);
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
		this.coordonnee.setEntreprise(this.entreprise);
		this.coordonneeService.save(this.coordonnee);
	}
	
	/**
	 * Creation / mise a jours des informations de l'entreprise en base de donnees et recuperation de son ID
	 * @param nouvelleEntreprise 
	 */
	public void updateEntreprise(Entreprise nouvelleEntreprise) {
		recupererDetailsEntreprise(nouvelleEntreprise);
		this.entrepriseService.save(this.entreprise);
		Long id = this.entrepriseService.findIdBySiret(entreprise.getSiret());
		entreprise.setId(id);
	}
	
	/**
	 * Methode permettant d'enregistrer l'evaluation d'une session d'un participant
	 * @param bilanParticipantSession objet contenant les valeurs de l'evalaution du participant 
	 */
	public void evaluationSession(BilanParticipantSession bilanParticipantSession) {
//		bilan = this.repository.findByParticipantIdAndSessionId(bilanParticipantSession.getParticipant().getId(), bilanParticipantSession.getSession().getId());
//		bilanParticipantSession.setId(bilan.getId());
//		this.repository.save(bilanParticipantSession);
	}
	
	/**
	 * Recuperation des details d'une entreprise à partir des informations provenant du bodyRequest
	 * @param bodyRequestSplit Liste contenant les elements lies aux coordonnees et details de l'entreprise
	 */
	public void recupererDetailsEntreprise(Entreprise nouvelleEntreprise) {
		this.entreprise.setSiret(nouvelleEntreprise.getSiret());
		this.entreprise.setNom(nouvelleEntreprise.getNom());
		
	}
	
	/**
	 * Ajout du bilan en base de donnees 
	 */
	public void creationBilan() {
		this.bilan = this.repository.save(bilan);
	}

	public void deleteByParticipantIdAndSessionId(Long participantId, Long sessionId) {
		this.repository.deleteByParticipantIdAndSessionId(participantId, sessionId);
		
	}

	public List<BilanParticipantSession> findParticipantBySessionId(Long sessionId) {
		return this.repository.findParticipantBySessionId(sessionId);
	}


	public List<Long> findAllSessionIdByParticipant(Participant participant) {
		List<BilanParticipantSession> bilans = this.repository.findAllSessionByParticipant(participant);
		List<Long> ids = new ArrayList<Long>();
		for (BilanParticipantSession bilan : bilans) {
			Long id = bilan.getSession().getId();
			ids.add(id);
		}
		return ids;
	}

	public List<String> findAllNumerosByParticipant(Participant participant) {

		List<BilanParticipantSession> bilans = this.repository.findAllNumerosByParticipant(participant);
		List<String> numeros = new ArrayList<String>();
		for (BilanParticipantSession bilan : bilans) {
			String numero = bilan.getNumeroSessionEval();
			numeros.add(numero);
		}
		return numeros;
	}

	public List<BilanParticipantSession> findAllByParticipant(Participant participant) {
		return this.repository.findAllByParticipant(participant);
	}
}
