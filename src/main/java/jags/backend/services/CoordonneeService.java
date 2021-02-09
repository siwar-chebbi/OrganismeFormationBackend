package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.Coordonnee;
import jags.backend.repositories.CoordonneeRepository;

@Service
public class CoordonneeService {
	
	@Autowired
	private CoordonneeRepository repository;

	public List<Coordonnee> findAll() {
		return repository.findAll();
	}
	
	public Coordonnee save(Coordonnee entity) {
		return repository.save(entity);
	}
}
