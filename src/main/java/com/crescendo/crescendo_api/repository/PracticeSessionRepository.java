package com.crescendo.crescendo_api.repository;

import com.crescendo.crescendo_api.model.PracticeSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PracticeSessionRepository extends JpaRepository<PracticeSession, Long> {
  List<PracticeSession> findByPieceId(Long pieceId);
}