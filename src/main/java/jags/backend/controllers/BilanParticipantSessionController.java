package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.DTO.Evaluation;
import jags.backend.DTO.InscriptionParticipantEmploye;
import jags.backend.DTO.InscriptionParticipantParticulier;
import jags.backend.DTO.ResumeInscription;
import jags.backend.entities.BilanParticipantSession;
import jags.backend.services.BilanParticipantSessionService;

@RestController
@RequestMapping("bilanParticipantSessions")
@CrossOrigin(origins = {"*"}, allowedHeaders={"*"})
public class BilanParticipantSessionController {

	@Autowired
	BilanParticipantSessionService service;
	
	@GetMapping("")
	public List<BilanParticipantSession> findAll(){
		return this.service.findAll();
	}
	
	@PostMapping("/inscriptionSessionParticulier")
	public ResumeInscription inscriptionSessionParticulier(@RequestBody InscriptionParticipantParticulier particulier) {
		return this.service.inscriptionSessionParticulier(particulier);
	}
	
	@PostMapping("/inscriptionSessionEntreprise")
	public ResumeInscription inscriptionSession(@RequestBody InscriptionParticipantEmploye employe) {
		return this.service.inscriptionSessionEntreprise(employe);
	}

	@PutMapping("/evaluations")
	public void evaluationSession(@RequestBody Evaluation bilanParticipantSession) {
		this.service.evaluationSession(bilanParticipantSession);
	}
	
	@DeleteMapping("{participantId}/{sessionId}")
	public void deleteByParticipantIdAndSessionId(@PathVariable Long participantId, Long sessionId) {
		this.service.deleteByParticipantIdAndSessionId(participantId, sessionId);
	}
	
	
}
