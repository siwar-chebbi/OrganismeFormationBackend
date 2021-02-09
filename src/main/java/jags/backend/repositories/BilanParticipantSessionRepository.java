package jags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.BilanParticipantSession;

public interface BilanParticipantSessionRepository extends JpaRepository<BilanParticipantSession, Long>{

	void saveBilanParticipantSession(String numero, Long participantId, Long sessionId);

}
