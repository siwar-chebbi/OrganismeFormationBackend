package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Lieu;

public interface LieuRepository extends JpaRepository<Lieu, Long>{

//	List<Lieu> finbByDisponibilite(Boolean bool);

	//todo
	@Query("SELECT * FROM lieu as l"
			+ "inner join session as s on l.id = s.lieu_id "
			+ "where s.id = ?1)")
	List<Lieu> findLieuBySessionId(Long sessionId);

}
