package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Coordonnee;
import jags.backend.entities.Entreprise;
import jags.backend.entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>  {

	List<Participant> findByEntreprise(Entreprise entrepriseId);

	Participant findByCoordonnee(Coordonnee coordonnee);
}
