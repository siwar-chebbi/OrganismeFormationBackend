package jags.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.Session;
import jags.backend.repositories.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository repository;
	
	public List<Session> findAll(){
		return this.repository.findAll();
	}
	
	public Session findById(Long id) {
		return this.repository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
