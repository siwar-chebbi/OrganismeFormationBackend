package jags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Session;


public interface SessionRepository extends JpaRepository<Session, Long> {

}
