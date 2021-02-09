package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.entities.BilanParticipantSession;
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
}
