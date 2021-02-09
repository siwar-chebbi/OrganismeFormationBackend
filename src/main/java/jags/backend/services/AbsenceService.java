package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.Absence;
import jags.backend.repositories.AbsenceRepository;

@Service
public class AbsenceService {

	@Autowired
	private AbsenceRepository repository;
	
	public List<Absence> findAll(){
		return this.repository.findAll();
	}
}
