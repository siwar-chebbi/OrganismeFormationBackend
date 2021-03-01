package jags.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jags.backend.DTO.FormationDTO;
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

	public List<Responsable> findAll() {
		return repository.findAll();
	}

	public Responsable findById(Long id) {
		return repository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public ResponsableDTO save(ResponsableDTO dto) {
		Responsable entity = responsableDTOToResponsable(dto);
		ResponsableDTO result = responsableToresponsableDTO(repository.save(entity));
				
		return result;
	}
	
	
	/**
	 * Conversion d'un responsable au format DTO vers un responsable au format Responsable
	 * @param  responsableDTO au format ResponsableDTO
	 */
	public Responsable responsableDTOToResponsable(ResponsableDTO responsableDTO) {
		Responsable responsable = new Responsable();
		responsable.setNom(responsableDTO.getNom());
		responsable.setPrenom(responsableDTO.getPrenom());
		responsable.setRole(responsableDTO.getRole());
		responsable.setTelephone(responsableDTO.getTelephone());
		return responsable;
}
	/**
	 * Conversion d'un responsable  vers un responsable au format DTO
	 * @param  responsableDTO au format ResponsableDTO
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

}
