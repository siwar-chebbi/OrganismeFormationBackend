package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Formation;
import jags.backend.entities.Theme;

public interface FormationRepository extends JpaRepository<Formation, Long>{
	public List<Formation> findByContenu(String contenu);
	public List<Formation> findByTitre(String titre);
	public List<Formation> findAllByThemes(Theme themes);
	public String findTitreById(Long id);

}
