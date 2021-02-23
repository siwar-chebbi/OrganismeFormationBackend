package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.Formateur;
import jags.backend.repositories.FormateurRepository;

@Service
public class FormateurService {
	
	@Autowired
	private FormateurRepository repository;

	public List<Formateur> findAll() {
		return this.repository.findAll();
	}

	public Formateur save(Formateur entity) {
		return this.repository.save(entity);
	}

	public Formateur findById(Long formateurId) {
		return this.repository.findById(formateurId)
							.orElseThrow(() ->  new ResponseStatusException (HttpStatus.NOT_FOUND));
	}

}
