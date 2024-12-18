package com.crescendo.crescendo_api.service;

import com.crescendo.crescendo_api.dto.ReferenceRecordingDTO;
import com.crescendo.crescendo_api.model.ReferenceRecording;
import com.crescendo.crescendo_api.model.MusicalPiece;
import com.crescendo.crescendo_api.repository.ReferenceRecordingRepository;
import com.crescendo.crescendo_api.repository.MusicalPieceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReferenceRecordingService {
  private final ReferenceRecordingRepository referenceRecordingRepository;
  private final MusicalPieceRepository musicalPieceRepository;

  public ReferenceRecordingService(ReferenceRecordingRepository referenceRecordingRepository,
      MusicalPieceRepository musicalPieceRepository) {
    this.referenceRecordingRepository = referenceRecordingRepository;
    this.musicalPieceRepository = musicalPieceRepository;
  }

  public ReferenceRecordingDTO createReferenceRecording(ReferenceRecordingDTO dto) {
    ReferenceRecording referenceRecording = convertToEntity(dto);
    ReferenceRecording savedRecording = referenceRecordingRepository.save(referenceRecording);
    return convertToDTO(savedRecording);
  }

  public ReferenceRecordingDTO getReferenceRecording(Long id) {
    ReferenceRecording referenceRecording = referenceRecordingRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Reference recording not found"));
    return convertToDTO(referenceRecording);
  }

  public List<ReferenceRecordingDTO> getAllReferenceRecordings() {
    return referenceRecordingRepository.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public List<ReferenceRecordingDTO> getReferenceRecordingsByMusicalPiece(Long musicalPieceId) {
    return referenceRecordingRepository.findByMusicalPieceId(musicalPieceId).stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public ReferenceRecordingDTO updateReferenceRecording(Long id, ReferenceRecordingDTO dto) {
    ReferenceRecording referenceRecording = referenceRecordingRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Reference recording not found"));
    updateEntityFromDTO(referenceRecording, dto);
    return convertToDTO(referenceRecordingRepository.save(referenceRecording));
  }

  public void deleteReferenceRecording(Long id) {
    referenceRecordingRepository.deleteById(id);
  }

  private ReferenceRecordingDTO convertToDTO(ReferenceRecording referenceRecording) {
    return new ReferenceRecordingDTO(
        referenceRecording.getId(),
        referenceRecording.getSpotifyTrackId(),
        referenceRecording.getArtistName(),
        referenceRecording.getRecordingTitle(),
        referenceRecording.getNotes(),
        referenceRecording.getMusicalPiece().getId());
  }

  private ReferenceRecording convertToEntity(ReferenceRecordingDTO dto) {
    ReferenceRecording referenceRecording = new ReferenceRecording();
    updateEntityFromDTO(referenceRecording, dto);
    return referenceRecording;
  }

  private void updateEntityFromDTO(ReferenceRecording referenceRecording, ReferenceRecordingDTO dto) {
    referenceRecording.setSpotifyTrackId(dto.getSpotifyTrackId());
    referenceRecording.setArtistName(dto.getArtistName());
    referenceRecording.setRecordingTitle(dto.getRecordingTitle());
    referenceRecording.setNotes(dto.getNotes());

    MusicalPiece musicalPiece = musicalPieceRepository.findById(dto.getMusicalPieceId())
        .orElseThrow(() -> new RuntimeException("Musical piece not found"));
    referenceRecording.setMusicalPiece(musicalPiece);
  }
}