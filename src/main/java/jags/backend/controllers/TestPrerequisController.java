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

import jags.backend.entities.TestPrerequis;
import jags.backend.services.TestPrerequisService;

@RestController
@RequestMapping("testPrerequis")
@CrossOrigin

public class TestPrerequisController {
	@Autowired
	private TestPrerequisService service;
	
	@GetMapping("numero/{numero}")
	public List<TestPrerequis> findByNumero(@PathVariable String numero) {
		return service.findByNumero(numero);
	}
	
	@GetMapping("")
	public List<TestPrerequis> findAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public TestPrerequis findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public TestPrerequis save(@RequestBody TestPrerequis entity) {
		return service.save(entity);
	}
	

}
