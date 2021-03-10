package jags.backend.controllers;

import java.text.MessageFormat;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jags.backend.DTO.FormationDTO;
import jags.backend.services.FormationService;


@RestController
@RequestMapping("formations")
@Slf4j
@CrossOrigin
public class FormationController {
	
	@Autowired
	private FormationService service;
	
	@GetMapping("")
	public List<FormationDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public FormationDTO findDTOById(@PathVariable Long id) {
		return service.findDTOById(id);
	}
	
	@GetMapping("/themes/{id}")
	public List<FormationDTO> findFormationByThemeId(@PathVariable Long id) {
		return service.findFormationByThemeId(id);
	}
	
	@PostMapping("")
	public FormationDTO save(@RequestBody FormationDTO entity) {
		log.info(MessageFormat.format("starting saving formation, {0}", entity.toString()));
		return service.save(entity);
	}

}
