package jags.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import jags.backend.entities.Evaluation;


public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{
	

}
