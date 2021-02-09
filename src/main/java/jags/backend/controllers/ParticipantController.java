package jags.backend.controllers;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("")
	public Participant save(@RequestBody Participant entity) {
		return this.service.save(entity);
	}
}
