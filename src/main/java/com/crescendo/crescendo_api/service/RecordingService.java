package com.crescendo.crescendo_api.service;

import com.crescendo.crescendo_api.dto.RecordingDTO;
import com.crescendo.crescendo_api.model.Recording;
import com.crescendo.crescendo_api.model.MusicalPiece;
import com.crescendo.crescendo_api.repository.RecordingRepository;
import com.crescendo.crescendo_api.repository.MusicalPieceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecordingService {
  private final RecordingRepository recordingRepository;
  private final MusicalPieceRepository musicalPieceRepository;

  public RecordingService(RecordingRepository recordingRepository,
      MusicalPieceRepository musicalPieceRepository) {
    this.recordingRepository = recordingRepository;
    this.musicalPieceRepository = musicalPieceRepository;
  }

  public RecordingDTO createRecording(RecordingDTO dto) {
    Recording recording = convertToEntity(dto);
    Recording savedRecording = recordingRepository.save(recording);
    return convertToDTO(savedRecording);
  }

  public RecordingDTO getRecording(Long id) {
    Recording recording = recordingRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Recording not found"));
    return convertToDTO(recording);
  }

  public List<RecordingDTO> getAllRecordings() {
    return recordingRepository.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public List<RecordingDTO> getRecordingsByMusicalPiece(Long musicalPieceId) {
    return recordingRepository.findByMusicalPieceId(musicalPieceId).stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public RecordingDTO updateRecording(Long id, RecordingDTO dto) {
    Recording recording = recordingRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Recording not found"));
    updateEntityFromDTO(recording, dto);
    return convertToDTO(recordingRepository.save(recording));
  }

  public void deleteRecording(Long id) {
    recordingRepository.deleteById(id);
  }

  private RecordingDTO convertToDTO(Recording recording) {
    return new RecordingDTO(
        recording.getId(),
        recording.getFilePath(),
        recording.getDateRecorded(),
        recording.getNotes(),
        recording.getMusicalPiece().getId());
  }

  private Recording convertToEntity(RecordingDTO dto) {
    Recording recording = new Recording();
    updateEntityFromDTO(recording, dto);
    return recording;
  }

  private void updateEntityFromDTO(Recording recording, RecordingDTO dto) {
    recording.setFilePath(dto.getFilePath());
    recording.setDateRecorded(dto.getDateRecorded());
    recording.setNotes(dto.getNotes());

    MusicalPiece musicalPiece = musicalPieceRepository.findById(dto.getMusicalPieceId())
        .orElseThrow(() -> new RuntimeException("Musical piece not found"));
    recording.setMusicalPiece(musicalPiece);
  }
}