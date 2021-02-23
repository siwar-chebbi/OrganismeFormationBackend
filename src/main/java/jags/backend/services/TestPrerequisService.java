package jags.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.Formation;
import jags.backend.entities.TestPrerequis;
import jags.backend.repositories.FormationRepository;
import jags.backend.repositories.TestPrerequisRepository;

@Service
public class TestPrerequisService {
	
	@Autowired
	private TestPrerequisRepository repository;

	public List<TestPrerequis> findByNumero(String numero) {
		return repository.findByNumero(numero);
	}

	public List<TestPrerequis> findAll() {
		return repository.findAll();
	}

	public TestPrerequis findById(Long id) {
		return repository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public <S extends TestPrerequis> S save(S entity) {
		return repository.save(entity);
	}
	
	

}
