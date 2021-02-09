package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.entities.Absence;
import jags.backend.services.AbsenceService;

@RestController
@RequestMapping("Absence")
@CrossOrigin
public class AbsenceController {

	@Autowired
	AbsenceService service;
	
	@GetMapping("")
	public List<Absence> findAll(){
		return this.service.findAll();
	}
}
