package jags.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.DTO.SessionDTO;
import jags.backend.DTO.SessionDetails;
import jags.backend.DTO.SessionsParticipant;
import jags.backend.entities.BilanParticipantSession;
import jags.backend.entities.Coordonnee;
import jags.backend.entities.Formation;
import jags.backend.entities.Lieu;
import jags.backend.entities.Participant;
import jags.backend.entities.Session;
import jags.backend.entities.Theme;
import jags.backend.repositories.SessionRepository;

@Service
public class SessionService {
	@Autowired
	private SessionRepository repository;
	@Autowired
	private FormationService formationService;
	@Autowired
	private LieuService lieuService;
	@Autowired
	private CoordonneeService coordonneeService;
	@Autowired
	private ParticipantService participantService;
	@Autowired
	private BilanParticipantSessionService bilanService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private Session session;
	
	
	/**
	 * Récupération de toutes les sessions présentent en base de données
	 * @return la liste des session au format demandé par le FRONTEND (sessionDTO)
	 */
	public List<SessionDTO> findAll(){
		List<Session> sessions = this.repository.findAll();
		return recupererListeSessionDTO(sessions);
	}
	
	/**
	 * Recupération d'une liste format sessionDTO
	 * @param sessions Une liste de sessions de type Session
	 * @return Une liste de sessions de type SessionDTO (format FRONTEND)
	 */
	public List<SessionDTO> recupererListeSessionDTO(List<Session> sessions){
		List<SessionDTO> sessionsDTO = new ArrayList<SessionDTO>();
		for (Session session : sessions) {
			sessionsDTO.add(sessionToSessionDTO(session));
		}
		return sessionsDTO;
	}
	
	/**
	 * Conversion d'une session format Session vers une session format SessionDTO
	 * @param session format Session
	 * @return session format SessionDTO
	 */
	public SessionDTO sessionToSessionDTO(Session session) {
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setId(session.getId());
		sessionDTO.setNumero(session.getNumero());
		sessionDTO.setType(session.getType());
		sessionDTO.setPrixHT(session.getPrixHT());
		sessionDTO.setDuree(session.getDuree());
		sessionDTO.setDateDebut(session.getDateDebut());
		determinerDatedeFinSession(sessionDTO.getDuree());
		sessionDTO.setDateFin(session.getDateFin());
		sessionDTO.setIdFormation(session.getFormation().getId());
		sessionDTO.setIdLieu(session.getLieu().getId());
		return sessionDTO;
	}
	
	/**
	 * recupération d'une session par son ID
	 * @param id de la session recherchee
	 * @return la session de l'id recherchee
	 */
	public Session findById(Long id) {
		return this.repository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	/**
	 * Recuperation d'une sessionDTO par son ID
	 * @param id id de la session recherchee
	 * @return le session de type SessionDTO
	 */
	public SessionDTO findByIdSessionDTO(Long id) {
		return sessionToSessionDTO(findById(id));
	}

	/**
	 * Enregistrement d'une session
	 * @param sessionDTO Session format SessionDTO fournit par le FRONTEND
	 * @return session format DTO pour récupération de l'id dans le FRONTEND
	 */
	public SessionDTO save(SessionDTO sessionDTO) {
		sessionDTOToSession(sessionDTO);
		return sessionToSessionDTO(this.repository.save(this.session));
	}
	
	/**
	 * Conversion d'une session format SessionDTO vers une session format Session
	 * @param session format SessionDTO
	 */
	public void sessionDTOToSession(SessionDTO sessionDTO) {
		// clear id of session
		this.session.setId(null); 
		this.session.setNumero(sessionDTO.getNumero());
		this.session.setType(sessionDTO.getType());
		this.session.setPrixHT(sessionDTO.getPrixHT());
		this.session.setDuree(sessionDTO.getDuree());
		this.session.setDateDebut(sessionDTO.getDateDebut());
		determinerDatedeFinSession(sessionDTO.getDuree());
		this.session.setFormation(recupererFormationById(sessionDTO.getIdFormation()));
		this.session.setLieu(recupererLieuById(sessionDTO.getIdLieu()));
		this.session.setPersonalisee(0);
		this.session.setType(0);
		this.session.setValidation(0);
	}
	
	/**
	 * Determine la date de fin d'une session à partir de la date de début et du nombre de demi journee
	 * @param nombreDemiJournee que va durer une session
	 */
	public void determinerDatedeFinSession(int nombreDemiJournee) {
		DateTime dateTime = new DateTime(this.session.getDateDebut());
		dateTime = recupererDatePlusJourTravail(dateTime, nombreDemiJournee);
		this.session.setDateFin(dateTime.toDate());
	}
	
	/**
	 * Recuperation de la date apres avoir ajouter les jours travailles
	 * @param dateTime	la date de depart
	 * @param nombreDemiJournee le nombre de jours travailles a ajouter a la date de depart
	 * @return la date de fin
	 */
	public DateTime recupererDatePlusJourTravail(DateTime dateTime, int nombreDemiJournee) {
		int jourAjoute = 0;
		while(jourAjoute < (nombreDemiJournee/2)) {
			dateTime = dateTime.plusDays(1);
			if (!(dateTime.getDayOfWeek() == 6 || dateTime.getDayOfWeek() == 7 )) {
				jourAjoute++;
			}
		}
		return dateTime;
	}
	/**
	 * Recuperation d'une formation par son Id
	 * @param id de la formation recherchee
	 * @return la formation recherchee par l'Id
	 */
	public Formation recupererFormationById(Long id){
		return this.formationService.findById(id);
	}

	/**
	 * Recuperation d'un lieu par son Id
	 * @param id du lieu recherchee
	 * @return le lieu recherchee par l'Id
	 */
	public Lieu recupererLieuById(Long id){
		return this.lieuService.findById(id);
	}

	/**
	 * Recuperation de toute les sessions a partir d'un id formation
	 * @param id de la formation pour laquelle on cherche les sessions
	 * @return liste de session de type SessionDTO
	 */
	public List<SessionDTO> findAllByFormationId(Long id) {
		List<Session> sessions = this.repository.findAllByFormationId(id);
		return listSessionsToListSessionsDTO(sessions);
	}
	
	/**
	 *  Conversion d'un liste de Session vers une liste de SessionDTO
	 * @param sessions liste de sessions à convertir
	 * @return liste de Session type SessionDTO
	 */
	public List<SessionDTO> listSessionsToListSessionsDTO(List<Session> sessions){
		List<SessionDTO> sessionsDTO = new ArrayList<SessionDTO>();
		for (Session session : sessions) {
			sessionsDTO.add(sessionToSessionDTO(session));
		}
		return sessionsDTO;
		
	}

	/**
	 * Recuperation de la liste des sessions à laquelle un participant a participant pour l'evaluation a partir d'une adresse mail
	 * @param mail du participant souhaitant evaluer une session
	 * @return une liste d'IdBilan et de titre de formation, pour que l'utilisateur puisse choisir la sessions a evaluer
	 */
	public List<SessionsParticipant> findSessionsByParticipantId(long id) {
		List<SessionsParticipant> sessionsParticipant = new ArrayList<SessionsParticipant>();
		Participant participant = this.participantService.findById(id);
		List<BilanParticipantSession> bilans = new ArrayList<BilanParticipantSession>();
		bilans = this.bilanService.findAllByParticipant(participant);
		for (BilanParticipantSession bilan : bilans) {
			sessionsParticipant.add(alimentationParticipant(bilan));
		}
		return sessionsParticipant;
	}
	
	/**
	 * Alimentation d'un participant a partir d'un bilan
	 * @param bilan servant a rercuperer les informations du participant
	 * @return 
	 */
	public SessionsParticipant alimentationParticipant(BilanParticipantSession bilan) {
		SessionsParticipant sessionParticipant = new SessionsParticipant();
		sessionParticipant.setIdBilan(bilan.getId());
		session = sessionService.findById(bilan.getSession().getId());
		String titreFormation = session.getFormation().getTitre();
		sessionParticipant.setTitre(titreFormation);
		return sessionParticipant;
	}
	
	/**
	 * Recuperation de Coordonnee  a partir d'un mail
	 * @param mail dont on cherche l'id
	 * @return la coordonnee de l'id
	 */
	public Coordonnee recuperationCoordonne(String mail) {
		return this.coordonneeService.findByMail(mail);
	}
	
	/**
	 * Recuperation d'un participant a partir d'une coordonnee
	 * @param coordonnee dont on cherche le participant
	 * @return participant correspondant à la coordonnee
	 */
	public long findIdParticipantByCoordonnee(Coordonnee coordonnee) {
		return this.participantService.findIdParticipantByCoordonneeId(coordonnee).getId();
	}

	public SessionDetails findSessionDetailsById(long id) {
		SessionDetails sessionDetails = new SessionDetails();
		//Recuperer le prix de la formation
		this.session = findById(id);
		sessionDetails.setPrix(session.getPrixHT());
		//Recuperer le titre de la formation
		Formation formation = session.getFormation();
		sessionDetails.setTitreFormation(formation.getTitre());
		//Recuperer le theme de la formation
		List<String> themes = new ArrayList<String>();
		for (Theme theme : formation.getThemes()) {
			themes.add(theme.getNom());
		}
		sessionDetails.setThemes(themes);
		//Recuperer la description du theme
		sessionDetails.setContenuFormation(formation.getContenu());
		//Recuperer le lieu de la formation
		sessionDetails.setLieu(this.session.getLieu().getNom());
		return sessionDetails;
	}
}
