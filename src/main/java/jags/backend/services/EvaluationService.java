package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.Evaluation;
import jags.backend.repositories.EvaluationRepository;

@Service
public class EvaluationService {

	@Autowired
	private EvaluationRepository repository;
	
	public List<Evaluation> findAll(){
		return this.repository.findAll();
	}
}
