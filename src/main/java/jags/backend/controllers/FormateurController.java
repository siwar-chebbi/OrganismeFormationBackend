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

import jags.backend.entities.Formateur;
import jags.backend.services.FormateurService;

@RequestMapping("formateurs")
@RestController
@CrossOrigin
public class FormateurController {
	
	@Autowired
	private FormateurService service;
	
	@GetMapping("")
	public List<Formateur> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("/{formateurId}")
	public Formateur findById(@PathVariable Long formateurId) {
		return this.service.findById(formateurId);
	}
		
	@PostMapping("")
	public Formateur save(@RequestBody Formateur entity) {
		return this.service.save(entity);
	}

}
