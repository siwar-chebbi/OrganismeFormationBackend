package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.DTO.AjoutParticipant;
import jags.backend.entities.Participant;
import jags.backend.services.ParticipantService;

@RequestMapping("participants")
@RestController
@CrossOrigin
public class ParticipantController {
	
	@Autowired
	private ParticipantService service;
	
	@GetMapping("")
	public List<Participant> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("/entreprise/{entrepriseId}")
	public List<Participant> findByEntreprise(@PathVariable Long entrepriseId) {
		return this.service.findByEntreprise(entrepriseId);
	}
	
	@GetMapping("/{participantId}")
	public Participant findById(@PathVariable Long participantId) {
		return this.service.findById(participantId);
	}
	
	@PostMapping("")
	public AjoutParticipant save(@RequestBody AjoutParticipant nouveauParticipant) {
		return this.service.saveNouveauParticipant(nouveauParticipant);
	}
	
	@GetMapping("/session/{sessionId}")
	public List<Participant> findParticipantBySessionId(@PathVariable Long sessionId){
		return this.service.findParticipantBySessionId(sessionId);
	}
}
