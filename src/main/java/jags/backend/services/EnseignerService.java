package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jags.backend.entities.Enseigner;
import jags.backend.repositories.EnseignerRepository;

@Service
public class EnseignerService {
	
	@Autowired
	private EnseignerRepository repository;
	
	public List<Enseigner> findAll() {
		return this.repository.findAll();
	}

	public Enseigner save(Enseigner entity) {
		return this.repository.save(entity);
	}

	public Enseigner findById(Long enseignerId) {
		return this.repository.findById(enseignerId)
							.orElseThrow(() ->  new ResponseStatusException (HttpStatus.NOT_FOUND));
	}

}
