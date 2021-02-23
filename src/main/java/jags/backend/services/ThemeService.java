package jags.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.entities.Theme;
import jags.backend.repositories.ThemeRepository;

@Service
public class ThemeService {

	@Autowired
	private ThemeRepository repository;

	public List<Theme> findAll() {
		return this.repository.findAll();
	}

	public Theme save(Theme entity) {
		return this.repository.save(entity);
	}

	public Theme findById(Long themeId) {
		return this.repository.findById(themeId)
							.orElseThrow(() ->  new ResponseStatusException (HttpStatus.NOT_FOUND));
	}
}
