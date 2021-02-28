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

import jags.backend.DTO.InscriptionParticipantParticulier;
import jags.backend.entities.BilanParticipantSession;
import jags.backend.entities.Coordonnee;
import jags.backend.services.BilanParticipantSessionService;

@RestController
@RequestMapping("bilanParticipantSessions")
@CrossOrigin
public class BilanParticipantSessionController {

	@Autowired
	BilanParticipantSessionService service;
	
	@GetMapping("")
	public List<BilanParticipantSession> findAll(){
		return this.service.findAll();
	}
	
	@PostMapping("/inscriptionSessionParticulier")
	public void inscriptionSessionParticulier(@RequestBody InscriptionParticipantParticulier particulier) {
		System.out.println(particulier.toString());
		this.service.inscriptionSessionParticulier(particulier);
	}
	
	@PostMapping("/inscriptionSessionEntreprise/{participantId}/{sessionId}")
	public void inscriptionSession(@PathVariable Long participantId, @PathVariable Long sessionId, @RequestBody String bodyRequest) {
		this.service.inscriptionSessionEntreprise(participantId, sessionId, bodyRequest);
	}
	
	@PutMapping("/evaluations")
	public void evaluationSession(@RequestBody BilanParticipantSession bilanParticipantSession) {
		this.service.evaluationSession(bilanParticipantSession);
	}
	
	@DeleteMapping("{participantId}/{sessionId}")
	public void deleteByParticipantIdAndSessionId(@PathVariable Long participantId, Long sessionId) {
		this.service.deleteByParticipantIdAndSessionId(participantId, sessionId);
	}
	
	
}
