package jags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Absence;

public interface AbsenceRepository extends JpaRepository<Absence, Long>{

}
