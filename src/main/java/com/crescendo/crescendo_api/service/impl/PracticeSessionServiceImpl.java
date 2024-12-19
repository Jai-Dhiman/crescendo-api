package com.crescendo.crescendo_api.service.impl;

import com.crescendo.crescendo_api.model.PracticeSession;
import com.crescendo.crescendo_api.model.MusicalPiece;
import com.crescendo.crescendo_api.repository.PracticeSessionRepository;
import com.crescendo.crescendo_api.repository.MusicalPieceRepository;
import com.crescendo.crescendo_api.service.PracticeSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PracticeSessionServiceImpl implements PracticeSessionService {
  private final PracticeSessionRepository practiceSessionRepository;
  private final MusicalPieceRepository musicalPieceRepository;

  @Override
  public List<PracticeSession> getSessionsByPieceId(Long pieceId) {
    if (!musicalPieceRepository.existsById(pieceId)) {
      throw new EntityNotFoundException("Musical piece not found with id: " + pieceId);
    }
    return practiceSessionRepository.findByPieceId(pieceId);
  }

  @Override
  public PracticeSession createSession(Long pieceId, PracticeSession session) {
    MusicalPiece piece = musicalPieceRepository.findById(pieceId)
        .orElseThrow(() -> new EntityNotFoundException("Musical piece not found with id: " + pieceId));
    session.setPiece(piece);
    return practiceSessionRepository.save(session);
  }

  @Override
  public PracticeSession updateSession(Long pieceId, Long sessionId, PracticeSession sessionDetails) {
    if (!musicalPieceRepository.existsById(pieceId)) {
      throw new EntityNotFoundException("Musical piece not found with id: " + pieceId);
    }

    PracticeSession session = practiceSessionRepository.findById(sessionId)
        .orElseThrow(() -> new EntityNotFoundException("Practice session not found with id: " + sessionId));

    if (!session.getPiece().getId().equals(pieceId)) {
      throw new IllegalArgumentException("Practice session does not belong to the specified piece");
    }

    session.setDateTime(sessionDetails.getDateTime());
    session.setDuration(sessionDetails.getDuration());
    session.setNotes(sessionDetails.getNotes());

    return practiceSessionRepository.save(session);
  }

  @Override
  public void deleteSession(Long pieceId, Long sessionId) {
    PracticeSession session = practiceSessionRepository.findById(sessionId)
        .orElseThrow(() -> new EntityNotFoundException("Practice session not found with id: " + sessionId));

    if (!session.getPiece().getId().equals(pieceId)) {
      throw new IllegalArgumentException("Practice session does not belong to the specified piece");
    }

    practiceSessionRepository.deleteById(sessionId);
  }
}