package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.entities.BilanParticipantSession;
import jags.backend.entities.Cordonnee;
import jags.backend.services.BilanParticipantSessionService;

@RestController
@RequestMapping("bilan")
@CrossOrigin
public class BilanParticipantSessionController {

	@Autowired
	BilanParticipantSessionService service;
	
	@GetMapping("")
	public List<BilanParticipantSession> findAll(){
		return this.service.findAll();
	}
	
	@PostMapping("/inscriptionSession/{participantId}/{sessionId}")
	public void inscriptionSession(@PathVariable Long participantId, @PathVariable Long sessionId, @RequestBody Coordonnee coordonnee) {
		this.service.inscriptionSession(participantId, sessionId, coordonnee);
	}
}
