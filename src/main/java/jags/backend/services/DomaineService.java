package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.Domaine;
import jags.backend.repositories.DomaineRepository;

@Service
public class DomaineService {
	
	@Autowired
	private DomaineRepository repository;

	public List<Domaine> findAll() {
		return this.repository.findAll();
	}

	public Domaine save(Domaine entity) {
		return this.repository.save(entity);
	}

	public Domaine findById(Long domaineId) {
		return this.repository.findById(domaineId)
							.orElseThrow(() ->  new ResponseStatusException (HttpStatus.NOT_FOUND));
	}

}
