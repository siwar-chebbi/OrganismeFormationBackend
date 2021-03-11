package jags.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		List<FormationDTO> formationsDTO = new ArrayList<>();
		for (Formation formation : formations) {
			FormationDTO formationDto =formationToFormationDTO(formation);
			formationsDTO.add(formationDto);
		}
		return formationsDTO;
	}

	/**
	 * Recuperation d'un responsable par son Id
	 * @param id du responsable recherche
	 *   le responsable recherche par l'Id
	 */
	public Responsable recupererResponsableById(Long id){
		return this.responsableService.findById(id);
	}
	
	public Theme recupererThemeById(Long id) {
		return this.themeService.findById(id);
	}

	/**
	 * Conversion d'une formation au format DTO vers une formation au format Formation
	 * @param formationDTO au format FormationDTO
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
		this.formation.setThemes(recuperationListThemeParId(formationDTO.getIdTheme()));
	}
	
	public List<Theme> recuperationListThemeParId(List<Long> themeIds){
		List<Theme> themes = new ArrayList<>();
		for (Long themeId : themeIds) {
			themes.add(recupererThemeById(themeId));
		}
		return themes;
	}
	
	/**
	 * Conversion d'une formation au format Fession vers une formation au format FormationDTO
	 * @param formation format Formation
	 * @return formation format FormationDTO
	 */
	public FormationDTO formationToFormationDTO(Formation formation) {
		FormationDTO formationDTO = new FormationDTO();
		formationDTO.setId(formation.getId());
		formationDTO.setTitre(formation.getTitre());
		formationDTO.setContenu(formation.getContenu());
		formationDTO.setNumero(formation.getNumero());
		formationDTO.setLogiciel(formation.getLogiciel());
		formationDTO.setSupport(formation.getSupport());
		formationDTO.setIdResponsable(formation.getResponsable().getId());
		//formationDTO.setIdTheme(formation.getThemes().get(0).getId());
		formationDTO.setIdTheme(formation.getThemes().stream().map(Theme::getId).collect(Collectors.toList()));
		return formationDTO;
	}
	
	/**
	 * Enregistrement d'une formation
	 * @param formationDTO Formation format formationDTO fournit par le FRONTEND
	 * @return formation format DTO pour récupération de l'id dans le FRONTEND
	 */
	public FormationDTO save(FormationDTO formationDTO) {
	    formationDTOToFormation(formationDTO);
		//for (Theme theme : formation.getThemes()) {
		//}
		this.formation = repository.save(this.formation);
		return formationToFormationDTO(formation);
	}

	/**
	 * Recuperation d'une liste de formation faisant partie du theme passé en paramètre
	 * @param id du theme
	 * @return liste de formation
	 */
	public List<FormationDTO> findFormationByThemeId(Long id) {
		List<Formation> formations = recuperationListeFormationParIdTheme(id);
		for (Formation formation : formations) {
		}
		return listeFormationtoFormationDTO(formations);
	}
	
	/**
	 * Conversion d'une liste de type Formations vers une liste de type FormationDTO
	 * @param formations liste de formation type Formation
	 * @return liste de foramtion type FormationDTO
	 */
	public List<FormationDTO> listeFormationtoFormationDTO(List<Formation> formations){
		List<FormationDTO> formationsDTO = new ArrayList<>();
		for (Formation formation : formations) {
			formationsDTO.add(formationToFormationDTO(formation));
		}
		return formationsDTO;
	}
	
	/**
	 * Recuperation de la liste des formations associees a un theme
	 * @param id l'id du theme
	 * @return liste de formation type Formation
	 */
	public List<Formation>recuperationListeFormationParIdTheme(Long id) {
		Theme themeFormation = this.themeService.findById(id);
		return this.repository.findAllByThemes(themeFormation);
	}

	public String findTitreById(Long id) {
		return this.repository.findTitreById(id);
		
	}
	
}
