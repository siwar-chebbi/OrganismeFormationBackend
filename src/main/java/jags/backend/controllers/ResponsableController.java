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

import jags.backend.DTO.ResponsableDTO;
import jags.backend.entities.Responsable;
import jags.backend.services.ResponsableService;

@RestController
@RequestMapping("responsable")
@CrossOrigin
public class ResponsableController {
	
	@Autowired
	private ResponsableService service;

	@GetMapping("")
	public List<ResponsableDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping("nom/{nom}")
	public List<Responsable> findByNom(@PathVariable String nom) {
		return service.findByNom(nom);
	}
	@GetMapping("prenom/{prenom}")
	public List<Responsable> findByPrenom(@PathVariable String prenom) {
		return service.findByPrenom(prenom);
	}
	
	@GetMapping("mail/{mail}")
	public List<Responsable> findByMail(@PathVariable String mail) {
		return service.findByMail(mail);
	}
	
	@GetMapping("{id}")
	public Responsable findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public ResponsableDTO save(@RequestBody ResponsableDTO dto) {
		return service.save(dto);
	}
	

}
