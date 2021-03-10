package jags.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.DTO.EnseignerDTO;
import jags.backend.entities.Enseigner;
import jags.backend.entities.Formateur;
import jags.backend.entities.Formation;
import jags.backend.entities.composite.key.EnseignerCle;
import jags.backend.repositories.EnseignerRepository;

@Service
public class EnseignerService {
	
	@Autowired
	private EnseignerRepository repository;	
	@Autowired
	private Enseigner enseigner;
	@Autowired
	private FormationService formationService;
	@Autowired
	private FormateurService formateurService;
	
	public List<EnseignerDTO> findAll() {
		List<Enseigner> listEnseigner = repository.findAll();
		return recupererListeEnseignerDTO(listEnseigner);
	}

	public Enseigner findByFormationIdAndFormateurId(Long formationId, Long formateurId) {
		Enseigner enseigner = new Enseigner();
		enseigner = this.repository.findByFormationIdAndFormateurId(formationId, formateurId);
		if (enseigner==null) {
			   System.out.println("Enseigner est null");
		} else System.out.println("Enseigner = " + enseigner.getExperience());
		return this.repository.findByFormationIdAndFormateurId(formationId, formateurId); 
	}
	
	public EnseignerDTO save(EnseignerDTO enseignerDTO) {
		enseignerDTOToEnseigner(enseignerDTO);
		System.out.println(this.enseigner.toString());
		this.enseigner = this.repository.save(this.enseigner);
		return enseignerToEnseignerDTO(enseigner);
	}
	
	/**
	 * Conversion de EnseignerDTO vers Enseigner
	 * @param enseignerDTO
	 */
	public void enseignerDTOToEnseigner(EnseignerDTO enseignerDTO) {
		EnseignerCle cle = new EnseignerCle();
		cle.setFormateurId(enseignerDTO.getIdFormation());
		cle.setFormationId(enseignerDTO.getIdFormateur());
		this.enseigner.setId(cle);
		this.enseigner.setFormation(recupererFormationById(enseignerDTO.getIdFormation()));
		this.enseigner.setFormateur(recupererFormateurById(enseignerDTO.getIdFormateur()));
		this.enseigner.setExperience(enseignerDTO.getExperience());
	}
	
	/**
	 * Conversion de Enseigner vers EnseignerDTO
	 * @param Enseigner
	 * @return EnseignerDTO
	 */
	public EnseignerDTO enseignerToEnseignerDTO(Enseigner enseigner) {
		EnseignerDTO enseignerDTO = new EnseignerDTO();
		enseignerDTO.setIdFormation(enseigner.getId().getFormationId());
		enseignerDTO.setIdFormateur(enseigner.getId().getFormateurId());
		enseignerDTO.setExperience(enseigner.getExperience());	
		return enseignerDTO;
	}
	
	/**
	 * Recupération d'une liste au format EnseignerDTO
	 * @param une liste de Enseigner
	 * @return Une liste de EnseignerDTO
	 */
	public List<EnseignerDTO> recupererListeEnseignerDTO(List<Enseigner> listEnseigner){
		List<EnseignerDTO> listEnseignerDTO = new ArrayList<EnseignerDTO>();
		for (Enseigner enseigner : listEnseigner) {
			listEnseignerDTO.add(enseignerToEnseignerDTO(enseigner));
		}
		return listEnseignerDTO;
	}
	
	public Formation recupererFormationById(Long id){
		return this.formationService.findById(id);
	}
	
	public Formateur recupererFormateurById(Long id) {
		return this.formateurService.findById(id);
	}




}
