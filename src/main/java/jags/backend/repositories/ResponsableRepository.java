package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Responsable;


public interface ResponsableRepository extends JpaRepository<Responsable, Long>{
	public List<Responsable> findByNom(String nom);
	public List<Responsable> findByPrenom(String prenom);
	public List<Responsable> findByMail(String email);
	
}
