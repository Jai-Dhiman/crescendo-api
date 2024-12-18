package com.crescendo.crescendo_api.service;

import com.crescendo.crescendo_api.dto.PracticeSessionDTO;
import com.crescendo.crescendo_api.model.PracticeSession;
import com.crescendo.crescendo_api.model.MusicalPiece;
import com.crescendo.crescendo_api.repository.PracticeSessionRepository;
import com.crescendo.crescendo_api.repository.MusicalPieceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PracticeSessionService {

  private final PracticeSessionRepository practiceSessionRepository;
  private final MusicalPieceRepository musicalPieceRepository;

  public PracticeSessionService(PracticeSessionRepository practiceSessionRepository,
      MusicalPieceRepository musicalPieceRepository) {
    this.practiceSessionRepository = practiceSessionRepository;
    this.musicalPieceRepository = musicalPieceRepository;
  }

  public PracticeSessionDTO createPracticeSession(PracticeSessionDTO dto) {
    PracticeSession session = convertToEntity(dto);
    PracticeSession savedSession = practiceSessionRepository.save(session);
    return convertToDTO(savedSession);
  }

  public PracticeSessionDTO getPracticeSession(Long id) {
    PracticeSession session = practiceSessionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Practice session not found"));
    return convertToDTO(session);
  }

  public List<PracticeSessionDTO> getAllPracticeSessions() {
    return practiceSessionRepository.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public List<PracticeSessionDTO> getPracticeSessionsByMusicalPiece(Long musicalPieceId) {
    return practiceSessionRepository.findByMusicalPieceId(musicalPieceId).stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public PracticeSessionDTO updatePracticeSession(Long id, PracticeSessionDTO dto) {
    PracticeSession session = practiceSessionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Practice session not found"));

    updateEntityFromDTO(session, dto);
    return convertToDTO(practiceSessionRepository.save(session));
  }

  public void deletePracticeSession(Long id) {
    practiceSessionRepository.deleteById(id);
  }

  private PracticeSessionDTO convertToDTO(PracticeSession session) {
    return new PracticeSessionDTO(
        session.getId(),
        session.getDuration(),
        session.getDateTime(),
        session.getNotes(),
        session.getMusicalPiece().getId());
  }

  private PracticeSession convertToEntity(PracticeSessionDTO dto) {
    PracticeSession session = new PracticeSession();
    updateEntityFromDTO(session, dto);
    return session;
  }

  private void updateEntityFromDTO(PracticeSession session, PracticeSessionDTO dto) {
    session.setDuration(dto.getDuration());
    session.setDateTime(dto.getDateTime());
    session.setNotes(dto.getNotes());

    MusicalPiece musicalPiece = musicalPieceRepository.findById(dto.getMusicalPieceId())
        .orElseThrow(() -> new RuntimeException("Musical piece not found"));
    session.setMusicalPiece(musicalPiece);
  }
}