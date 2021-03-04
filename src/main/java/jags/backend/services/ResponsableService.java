package jags.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.DTO.ResponsableDTO;
import jags.backend.entities.Responsable;
import jags.backend.repositories.ResponsableRepository;

@Service
public class ResponsableService {
	
	@Autowired
	private ResponsableRepository repository;

	public List<Responsable> findByNom(String nom) {
		return repository.findByNom(nom);
	}

	public List<Responsable> findByPrenom(String prenom) {
		return repository.findByPrenom(prenom);
	}

	public List<Responsable> findByMail(String mail) {
		return repository.findByMail(mail);
	}


	public Responsable findById(Long id) {
		return repository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public ResponsableDTO save(ResponsableDTO dto) {
		Responsable entity = responsableDTOToResponsable(dto);
		return responsableToresponsableDTO(repository.save(entity));
	}
	
	
	/**
	 * Conversion d'un responsable au format DTO vers un responsable au format entité
	 * @param  responsableDTO au format {@link ResponsableDTO}
	 */
	public Responsable responsableDTOToResponsable(ResponsableDTO responsableDTO) {
		Responsable responsable = new Responsable();
		responsable.setNom(responsableDTO.getNom());
		responsable.setPrenom(responsableDTO.getPrenom());
		responsable.setRole(responsableDTO.getRole());
		responsable.setMail(responsableDTO.getMail());
		responsable.setTelephone(responsableDTO.getTelephone());
		return responsable;
}
	/**
	 * Conversion d'un responsable entité  vers un responsable au format DTO
	 * @param  responsable au format {@link Responsable}
     * @return responsableDto au format {@link ResponsableDTO}
	 */
	public ResponsableDTO responsableToresponsableDTO(Responsable responsable) {
		ResponsableDTO responsableDto = new ResponsableDTO();
		responsableDto.setId(responsable.getId());
		responsableDto.setNom(responsable.getNom());
		responsableDto.setPrenom(responsable.getPrenom());
		responsableDto.setMail(responsable.getMail());
		responsableDto.setTelephone(responsable.getTelephone());
		return responsableDto;
		
	}
	
	/**
	 * Conversion d'un responsable entité vers un responsable au format DTO
	 * @param responsable au format {@link Responsable}
	 * @return responsableDto au format {@link ResponsableDTO}
	 */
	public ResponsableDTO responsableToresponsableLightDTO(Responsable responsable) {
		ResponsableDTO responsableDto = new ResponsableDTO();
		responsableDto.setId(responsable.getId());
		responsableDto.setNom(responsable.getNom());
		responsableDto.setPrenom(responsable.getPrenom());
		return responsableDto;
		
	}
	

	public List<ResponsableDTO> findAll() {
		List<Responsable> responsables = repository.findAll();
		List<ResponsableDTO> responsableDTOs = new ArrayList<>();
		for (Responsable responsable : responsables) {
			responsableDTOs.add(responsableToresponsableLightDTO(responsable));
		}
		return responsableDTOs;
	}
	

}
