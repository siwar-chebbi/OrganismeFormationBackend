package jags.backend.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jags.backend.entities.Enseigner;
import jags.backend.services.EnseignerService;

@RequestMapping("enseigner")
@RestController
@CrossOrigin
public class EnseignerController {
	
	@Autowired
	EnseignerService service;
	
	@GetMapping("")
	public List<Enseigner> findAll(){
		return this.service.findAll();
	}
	
}
