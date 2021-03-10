package jags.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jdbc.repository.query.Query;

import jags.backend.entities.BilanParticipantSession;
import jags.backend.entities.Participant;
import jags.backend.entities.Session;

public interface BilanParticipantSessionRepository extends JpaRepository<BilanParticipantSession, Long>{

	BilanParticipantSession findByParticipantIdAndSessionId(Long id, Long id2);

	void deleteByParticipantIdAndSessionId(Long participantId, Long sessionId);

	@Query("SELECT * FROM participant AS p"
			+ "INNER JOIN bilan_participant_session AS b ON p.id = b.participant_id"
			+ "WHERE b.session_id = ?1")
	List<BilanParticipantSession> findParticipantBySessionId(Long sessionId);

	List<BilanParticipantSession> findAllNumerosByParticipant(Participant participant);

	List<BilanParticipantSession> findAllSessionByParticipant(Participant participant);

	List<BilanParticipantSession> findAllByParticipant(Participant participant);
}
