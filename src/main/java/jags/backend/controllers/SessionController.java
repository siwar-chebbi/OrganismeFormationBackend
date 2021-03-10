package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.DTO.SessionDTO;
import jags.backend.DTO.SessionsParticipant;
import jags.backend.entities.Session;
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

	@GetMapping("/formations/{id}")
	public List<SessionDTO> findAllByFormationId(@PathVariable Long id){
		return this.service.findAllByFormationId(id);
	}

	@GetMapping("/{id}")
	public SessionDTO findById(@PathVariable Long id) {
		return this.service.findByIdSessionDTO(id);
	}

	@PostMapping("")
	public SessionDTO save(@RequestBody SessionDTO entity) {
		return this.service.save(entity);
	}
	
	@GetMapping("/mail")
	public List<SessionsParticipant> findSessionsByMailParticipant(@RequestParam String mail) {
		return this.service.findSessionsByMailParticipant(mail);
	}
}
