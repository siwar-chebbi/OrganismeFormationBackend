package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Formation;

public interface FormationRepository extends JpaRepository<Formation, Long>{
	public List<Formation> findByContenu(String contenu);
	public List<Formation> findByTitre(String titre);

}
