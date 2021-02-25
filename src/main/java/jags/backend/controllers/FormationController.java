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

import jags.backend.entities.Formation;
import jags.backend.services.FormationService;


@RestController
@RequestMapping("formations")
@CrossOrigin
public class FormationController {
	
	@Autowired
	private FormationService service;
	
	
	@GetMapping("titre/{titre}")
	public List<Formation> findByTitre(@PathVariable String titre) {
		return service.findByTitre(titre);
	}
	
	@GetMapping("contenu/{contenu}")
	public List<Formation> findByContenu(@PathVariable String contenu) {
		return service.findByContenu(contenu);
	}
	
	@PostMapping
	public <S extends Formation> S save(@RequestBody S entity) {
		return service.save(entity);
	}
	
	@GetMapping
	public List<Formation> findAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public Formation findById(@PathVariable Long id) {
		return service.findById(id);
	}




}
