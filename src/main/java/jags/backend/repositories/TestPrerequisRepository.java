package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.TestPrerequis;

public interface TestPrerequisRepository extends JpaRepository<TestPrerequis, Long>{
	public List<TestPrerequis> findByNumero(String numero);
}
