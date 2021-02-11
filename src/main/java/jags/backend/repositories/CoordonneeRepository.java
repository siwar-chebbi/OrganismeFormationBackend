package jags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Coordonnee;

public interface CoordonneeRepository extends JpaRepository<Coordonnee, Long> {

	public Coordonnee findByMail(String mail);

	public Boolean existsCoordonneeByMail(String mail);

}
