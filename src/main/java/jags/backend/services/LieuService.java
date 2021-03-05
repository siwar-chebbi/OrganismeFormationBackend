package jags.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import jags.backend.entities.Lieu;
import jags.backend.repositories.LieuRepository;

@Service
public class LieuService {

	@Autowired
	private LieuRepository repository;
	
	public List<Lieu> findAll(){
		return this.repository.findAll();
	}

	public Lieu findById(Long id){
		return this.repository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public void save(Lieu lieu) {
		lieu.setDisponibilite(false);
		this.repository.save(lieu);
	}

	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}

	public List<Lieu> findLieuBySessionsId(Long sessionId) {
		return this.repository.findLieuBySessionsId(sessionId);
	}
}
