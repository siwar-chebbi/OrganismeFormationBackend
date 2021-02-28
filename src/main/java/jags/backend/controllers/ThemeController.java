package jags.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jags.backend.entities.Theme;
import jags.backend.services.ThemeService;

@RequestMapping("themes")
@RestController
@CrossOrigin
public class ThemeController {
	
	@Autowired
	private ThemeService service;
	
	@GetMapping("")
	public List<Theme> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("/{themeId}")
	public Theme findById(@PathVariable Long themeId) {
		return this.service.findById(themeId);
	}
	
	@PostMapping("")
	public Theme save(@RequestBody Theme entity) {
		return this.service.save(entity);
	}

}
