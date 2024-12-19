package com.crescendo.crescendo_api.service.impl;

import com.crescendo.crescendo_api.model.Recording;
import com.crescendo.crescendo_api.model.MusicalPiece;
import com.crescendo.crescendo_api.repository.RecordingRepository;
import com.crescendo.crescendo_api.repository.MusicalPieceRepository;
import com.crescendo.crescendo_api.service.RecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordingServiceImpl implements RecordingService {
  private final RecordingRepository recordingRepository;
  private final MusicalPieceRepository musicalPieceRepository;

  @Override
  public List<Recording> getRecordingsByPieceId(Long pieceId) {
    if (!musicalPieceRepository.existsById(pieceId)) {
      throw new EntityNotFoundException("Musical piece not found with id: " + pieceId);
    }
    return recordingRepository.findByPieceId(pieceId);
  }

  @Override
  public Recording getRecording(Long pieceId, Long recordingId) {
    Recording recording = recordingRepository.findById(recordingId)
        .orElseThrow(() -> new EntityNotFoundException("Recording not found with id: " + recordingId));

    if (!recording.getPiece().getId().equals(pieceId)) {
      throw new IllegalArgumentException("Recording does not belong to the specified piece");
    }

    return recording;
  }

  @Override
  public Recording createRecording(Long pieceId, Recording recording) {
    MusicalPiece piece = musicalPieceRepository.findById(pieceId)
        .orElseThrow(() -> new EntityNotFoundException("Musical piece not found with id: " + pieceId));
    recording.setPiece(piece);
    return recordingRepository.save(recording);
  }

  @Override
  public Recording updateRecording(Long pieceId, Long recordingId, Recording recordingDetails) {
    Recording recording = getRecording(pieceId, recordingId);

    recording.setFileName(recordingDetails.getFileName());
    recording.setDateRecorded(recordingDetails.getDateRecorded());
    recording.setNotes(recordingDetails.getNotes());

    return recordingRepository.save(recording);
  }

  @Override
  public void deleteRecording(Long pieceId, Long recordingId) {
    Recording recording = getRecording(pieceId, recordingId);
    recordingRepository.delete(recording);
  }
}