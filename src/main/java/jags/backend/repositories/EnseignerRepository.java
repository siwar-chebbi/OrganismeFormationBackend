package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Enseigner;

public interface EnseignerRepository extends JpaRepository<Enseigner, Long> {
	
	Enseigner findByFormationIdAndFormateurId(Long formationID, Long formateurID);


}
