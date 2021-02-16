package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.entities.Session;
import jags.backend.services.SessionService;

@RestController
@RequestMapping("sessioons")
@CrossOrigin
public class SessionController {

	@Autowired
	SessionService service;
	
	@GetMapping("")
	public List<Session> findAll(){
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public Session findById(@PathVariable Long sessionId) {
		return this.service.findById(sessionId);
	}
	
}
