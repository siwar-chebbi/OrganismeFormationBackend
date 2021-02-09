package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.Session;
import jags.backend.repositories.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository repository;
	
	public List<Session> findAll(){
		return this.repository.findAll();
	}
}
