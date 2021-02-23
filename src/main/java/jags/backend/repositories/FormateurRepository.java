package jags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Formateur;

public interface FormateurRepository  extends JpaRepository<Formateur, Long>  {

}
