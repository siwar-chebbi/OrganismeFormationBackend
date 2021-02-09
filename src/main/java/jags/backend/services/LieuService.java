package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jags.backend.entities.Lieu;
import jags.backend.repositories.LieuRepository;

@Service
public class LieuService {

	@Autowired
	private LieuRepository repository;
	
	public List<Lieu> findAll(){
		return this.repository.findAll();
	}
}
