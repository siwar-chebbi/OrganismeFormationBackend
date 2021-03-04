package jags.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.DTO.ThemeDTO;
import jags.backend.entities.Theme;
import jags.backend.repositories.ThemeRepository;

@Service
public class ThemeService {

	@Autowired
	private ThemeRepository repository;

	public List<ThemeDTO> findAll() {
		return themesToThemeDTOs(this.repository.findAll()) ;
	}

	public Theme save(Theme entity) {
		return this.repository.save(entity);
	}

	public Theme findById(Long themeId) {
		return repository.findById(themeId)
							.orElseThrow(() ->  new ResponseStatusException (HttpStatus.NOT_FOUND));
	}


	public ThemeDTO findDtoById(Long themeId) {
		return themeToThemeDTO(findById(themeId));
	}

	public ThemeDTO themeToThemeDTO(Theme theme) {
		ThemeDTO themeDTO = new ThemeDTO();
		themeDTO.setId(theme.getId());
		themeDTO.setNom(theme.getNom());
		themeDTO.setNumero(theme.getNumero());
		return themeDTO;
	}

	public List<ThemeDTO> themesToThemeDTOs(List<Theme> themes) {
		List<ThemeDTO> themeDTOs = new ArrayList<>();
		for (Theme theme : themes) {
			themeDTOs.add(themeToThemeDTO(theme));
		}
		return themeDTOs;
	}
}
