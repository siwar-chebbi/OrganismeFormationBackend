package jags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Domaine;

public interface DomaineRepository extends JpaRepository<Domaine, Long> {

}
