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

import jags.backend.DTO.SessionDTO;
import jags.backend.entities.Session;
import jags.backend.entities.Theme;
import jags.backend.services.SessionService;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders={"*"})
@RequestMapping("/sessions")
public class SessionController {

	@Autowired
	SessionService service;
	
	@GetMapping("")
	public List<SessionDTO> findAll(){
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public Session findById(@PathVariable Long sessionId) {
		return this.service.findById(sessionId);
	}

	@PostMapping("")
	public SessionDTO save(@RequestBody SessionDTO entity) {
		return this.service.save(entity);
	}
}
