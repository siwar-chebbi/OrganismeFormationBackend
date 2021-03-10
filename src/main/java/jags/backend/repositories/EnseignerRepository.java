package jags.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import jags.backend.entities.Enseigner;

public interface EnseignerRepository extends JpaRepository<Enseigner, Long> {
	
	Enseigner findByFormationIdAndFormateurId(Long id, Long id2);

}
