package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Lieu;

public interface LieuRepository extends JpaRepository<Lieu, Long>{

	//todo
	@Query("SELECT * FROM lieu AS l"
			+ "INNER JOIN session AS s on l.id = s.lieu_id "
			+ "WHERE s.id = ?1)")
	List<Lieu> findLieuBySessionsId(Long sessionId);

}
