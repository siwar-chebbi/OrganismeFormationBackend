package jags.backend.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.DTO.SessionDTO;
import jags.backend.entities.Formation;
import jags.backend.entities.Lieu;
import jags.backend.entities.Session;
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
		sessionDTO.setNumero(session.getNumero());
		sessionDTO.setType(session.getType());
		sessionDTO.setPrixHT(session.getPrixHT());
		sessionDTO.setDuree(session.getDuree());
		sessionDTO.setDateDebut(session.getDateDebut());
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
}
