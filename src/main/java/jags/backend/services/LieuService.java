package jags.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jags.backend.DTO.LieuDTO;
import jags.backend.DTO.ResponsableDTO;
import jags.backend.entities.Responsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jags.backend.entities.Lieu;
import jags.backend.repositories.LieuRepository;

@Service
public class LieuService {

	@Autowired
	private LieuRepository repository;


	public List<LieuDTO> findAll() {
		List<Lieu> lieux = repository.findAll();
		return lieuxToLieuxDtos(lieux);
	}

	private List<LieuDTO> lieuxToLieuxDtos(List<Lieu> lieux) {
		return lieux.stream().map(this::lieuTolieuDTO).collect(Collectors.toList());
	}

	/**
	 * Conversion d'un lieu entité  vers un lieu au format DTO
	 * @param  lieu au format {@link Responsable}
	 * @return responsableDto au format {@link ResponsableDTO}
	 */
	public LieuDTO lieuTolieuDTO(Lieu lieu) {
		LieuDTO lieuDto = new LieuDTO();
		lieuDto.setId(lieu.getId());
		lieuDto.setNom(lieu.getNom());
		lieuDto.setDisponibilite(lieu.getDisponibilite());
		lieuDto.setSalle(lieu.getSalle());

		return lieuDto;
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
