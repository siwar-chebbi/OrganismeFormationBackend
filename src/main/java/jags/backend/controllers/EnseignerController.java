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

import jags.backend.DTO.EnseignerDTO;
import jags.backend.entities.Enseigner;
import jags.backend.services.EnseignerService;

@RequestMapping("enseigner")
@RestController
@CrossOrigin
public class EnseignerController {
	
	@Autowired
	EnseignerService service;
	
	@GetMapping("")
	public List<EnseignerDTO> findAll(){
		return this.service.findAll();
	}
	
	@GetMapping("{formationId}/{formateurId}")
	public Enseigner findByFormationIdAndFormateurId(@PathVariable Long formationId, Long formateurId) {
		return this.service.findByFormationIdAndFormateurId(formationId, formateurId);
	}
		
	@PostMapping("")
	public EnseignerDTO save(@RequestBody EnseignerDTO entity) {
		System.out.println("@RequestBody = " + entity.toString());
		return this.service.save(entity);
	}
	
}
