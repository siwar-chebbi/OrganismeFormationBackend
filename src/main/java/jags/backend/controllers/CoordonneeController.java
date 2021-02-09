package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.entities.Coordonnee;
import jags.backend.services.CoordonneeService;

@RequestMapping("coordonnees")
@RestController
@CrossOrigin
public class CoordonneeController {
	
	@Autowired
	private CoordonneeService service;
	
	@GetMapping("")
	public List<Coordonnee> findAll() {
		return this.service.findAll();
	}
	
	@PostMapping("")
	public Coordonnee save(@RequestBody Coordonnee entity) {
		return this.service.save(entity);
	}
}
