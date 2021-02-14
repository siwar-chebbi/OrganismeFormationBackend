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

import jags.backend.entities.Domaine;
import jags.backend.services.DomaineService;

@RequestMapping("domaines")
@RestController
@CrossOrigin
public class DomaineController {

	@Autowired
	private DomaineService service;
	
	@GetMapping("")
	public List<Domaine> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("/{domaineId}")
	public Domaine findById(@PathVariable Long domaineId) {
		return this.service.findById(domaineId);
	}
	
	@PostMapping("")
	public Domaine save(@RequestBody Domaine entity) {
		return this.service.save(entity);
	}

}
