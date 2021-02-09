package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.entities.Assister;
import jags.backend.services.AssisterService;

@RestController
@RequestMapping("assister")
@CrossOrigin
public class AssisterController {

	@Autowired
	AssisterService service;
	
	@GetMapping("")
	public List<Assister> findAll(){
		return this.service.findAll();
	}
}
