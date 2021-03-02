package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Formation;
import jags.backend.entities.Session;


public interface SessionRepository extends JpaRepository<Session, Long> {

	List<Session> findAllByFormation(Formation formation);

	List<Session> findAllByFormationId(Long id);

}
