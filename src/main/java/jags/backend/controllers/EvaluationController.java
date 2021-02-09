package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.entities.Evaluation;
import jags.backend.services.EvaluationService;

@RestController
@RequestMapping("evaluations")
@CrossOrigin
public class EvaluationController {

	@Autowired
	EvaluationService service;
	
	@GetMapping("")
	public List<Evaluation> findAll(){
		return this.service.findAll();
	}
}
