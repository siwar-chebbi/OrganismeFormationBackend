package jags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

}
