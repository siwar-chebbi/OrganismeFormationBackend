package jags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>  {
	
}
