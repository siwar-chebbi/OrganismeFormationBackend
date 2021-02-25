package jags.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.entities.Responsable;
import jags.backend.services.ResponsableService;

@RestController
@RequestMapping("responsable")
@CrossOrigin
public class ResponsableController {
	
	private ResponsableService service;
	
	@GetMapping("nom/{nom}")
	public List<Responsable> findByNom(@PathVariable String nom) {
		return service.findByNom(nom);
	}
	@GetMapping("prenom/{prenom}")
	public List<Responsable> findByPrenom(@PathVariable String prenom) {
		return service.findByPrenom(prenom);
	}
	
	@GetMapping("email/{email}")
	public List<Responsable> findByEmail(@PathVariable String email) {
		return service.findByEmail(email);
	}
	
	@GetMapping
	public List<Responsable> findAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public Responsable findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public <S extends Responsable> S save(@RequestBody S entity) {
		return service.save(entity);
	}
	

}
