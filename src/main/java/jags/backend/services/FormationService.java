package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.Formation;
import jags.backend.repositories.FormationRepository;


@Service
public class FormationService {
	
	@Autowired
	private FormationRepository repository;
	

	public List<Formation> findByTitre(String titre) {
		return repository.findByTitre(titre);
	}
	
	public List<Formation> findByContenu(String contenu) {
		return repository.findByContenu(contenu);
	}

	public <S extends Formation> S save(S entity) {
		return repository.save(entity);
	}

	public List<Formation> findAll() {
		return repository.findAll();
	}
	
	public Formation findById(Long id) {
		return repository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}





}
