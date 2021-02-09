package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.Participant;
import jags.backend.repositories.ParticipantRepository;

@Service
public class ParticipantService {
	
	@Autowired
	private ParticipantRepository repository;

	public List<Participant> findAll() {
		return repository.findAll();
	}

	public Participant save(Participant entity) {
		return repository.save(entity);
	}
}
