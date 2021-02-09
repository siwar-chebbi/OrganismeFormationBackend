package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.Assister;

@Service
public class AssisterService {

	@Autowired
	private AssisterService service;
	
	public List<Assister> findAll(){
		return this.service.findAll();
	}
}
