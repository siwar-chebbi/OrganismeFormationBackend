package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.entities.Entreprise;
import jags.backend.services.EntrepriseService;

@RequestMapping("entreprises")
@RestController
@CrossOrigin
public class EntrepriseController {
	
	@Autowired
	private EntrepriseService service;

	@GetMapping("")
	public List<Entreprise> findAll(){
		return this.service.findAll();
	}
	
	@PostMapping("")
	public Entreprise save(@RequestBody Entreprise entity) {
		return this.service.save(entity);
	}
}
