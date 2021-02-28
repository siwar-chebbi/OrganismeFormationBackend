package jags.backend.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jags.backend.DTO.FormationDTO;
import jags.backend.entities.Formation;
import jags.backend.entities.Responsable;
import jags.backend.entities.Theme;
import jags.backend.repositories.FormationRepository;


@Service
public class FormationService {
	
	@Autowired
	private FormationRepository repository;
	@Autowired
	private Formation formation;
	@Autowired
	private ResponsableService responsableService;
	@Autowired
	private ThemeService themeService;
	
	/**
	 * Récupération de toutes les formations présentent en base de données
	 * @return la liste des formation au format demandé par le FRONTEND (formationDTO)
	 */
	public List<FormationDTO> findAll() {
		List<Formation> formations = repository.findAll();
		return recupererListeFormationDTO(formations);
	}
	
	public Formation findById(Long id) {
		return repository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public FormationDTO findDTOById(Long id) {
		Formation formation = findById(id);		
		if (formation!=null)
			return formationToFormationDTO(formation);
		else return null;
	}
	
	/**
	 * Recupération d'une liste au format formationDTO
	 * @param formations Une liste de formations de type Formation
	 * @return Une liste de formations de type FormationDTO (format FRONTEND)
	 */
	public List<FormationDTO> recupererListeFormationDTO(List<Formation> formations){
		List<FormationDTO> formationsDTO = new ArrayList<FormationDTO>();
		for (Formation formation : formations) {
			formationsDTO.add(formationToFormationDTO(formation));
		}
		return formationsDTO;
	}

	/**
	 * Recuperation d'un responsable par son Id
	 * @param id du responsable recherche
	 * @return le responsable recherche par l'Id
	 */
	public Responsable recupererResponsableById(Long id){
		return this.responsableService.findById(id);
	}
	
	public Theme recupererThemeById(Long id) {
		return this.themeService.findById(id);
	}

	/**
	 * Conversion d'une formation au format DTO vers une formation au format Formation
	 * @param formation au format FormationDTO
	 */
	public void formationDTOToFormation(FormationDTO formationDTO) {
		this.formation.setId(null);
		this.formation.setTitre(formationDTO.getTitre());
		this.formation.setContenu(formationDTO.getContenu());
		this.formation.setNumero(formationDTO.getNumero());
		this.formation.setLogiciel(formationDTO.getLogiciel());
		this.formation.setResponsable(recupererResponsableById(formationDTO.getIdResponsable()));
		this.formation.setSupport(formationDTO.getSupport());
		this.formation.setExperience(null);
		this.formation.setSessions(null);
		this.formation.setTestPrerequis(null);
		//this.formation.setThemes(recupererThemeById(formationDTO.getIdTheme()));
	}
	
	/**
	 * Conversion d'une formation au format Fession vers une formation au format FormationDTO
	 * @param formation format Formation
	 * @return formation format FormationDTO
	 */
	public FormationDTO formationToFormationDTO(Formation formation) {
		FormationDTO formationDTO = new FormationDTO();
		formationDTO.setTitre(formation.getTitre());
		formationDTO.setContenu(formation.getContenu());
		formationDTO.setNumero(formation.getNumero());
		formationDTO.setLogiciel(formation.getLogiciel());
		formationDTO.setSupport(formation.getSupport());
		formationDTO.setIdResponsable(formation.getResponsable().getId());
		//formationDTO.setIdTheme(formation.getThemes().get(0).getId());		
		return formationDTO;
	}
	
	/**
	 * Enregistrement d'une formation
	 * @param formationDTO Formation format formationDTO fournit par le FRONTEND
	 * @return formation format DTO pour récupération de l'id dans le FRONTEND
	 */
	public FormationDTO save(FormationDTO formationDTO) {
		formationDTOToFormation(formationDTO);
		this.formation = this.repository.save(this.formation);
		return formationToFormationDTO(formation);
	}
	
}
