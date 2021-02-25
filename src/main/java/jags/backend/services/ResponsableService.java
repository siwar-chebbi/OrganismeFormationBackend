package jags.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.Responsable;
import jags.backend.repositories.ResponsableRepository;

@Service
public class ResponsableService {
	
	@Autowired
	private ResponsableRepository repository;

	public List<Responsable> findByNom(String nom) {
		return repository.findByNom(nom);
	}

	public List<Responsable> findByPrenom(String prenom) {
		return repository.findByPrenom(prenom);
	}

	public List<Responsable> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public List<Responsable> findAll() {
		return repository.findAll();
	}

	public Responsable findById(Long id) {
		return repository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public <S extends Responsable> S save(S entity) {
		return repository.save(entity);
	}
	
	
	
	

}
