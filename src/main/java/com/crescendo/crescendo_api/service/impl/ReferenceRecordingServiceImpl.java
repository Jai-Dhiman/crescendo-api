package com.crescendo.crescendo_api.service.impl;

import com.crescendo.crescendo_api.model.ReferenceRecording;
import com.crescendo.crescendo_api.model.MusicalPiece;
import com.crescendo.crescendo_api.repository.ReferenceRecordingRepository;
import com.crescendo.crescendo_api.repository.MusicalPieceRepository;
import com.crescendo.crescendo_api.service.ReferenceRecordingService;
import com.crescendo.crescendo_api.service.SpotifyService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReferenceRecordingServiceImpl implements ReferenceRecordingService {
  private final ReferenceRecordingRepository referenceRecordingRepository;
  private final MusicalPieceRepository musicalPieceRepository;
  private final SpotifyService spotifyService;

  @Override
  public List<ReferenceRecording> getReferencesByPieceId(Long pieceId) {
    if (!musicalPieceRepository.existsById(pieceId)) {
      throw new EntityNotFoundException("Musical piece not found with id: " + pieceId);
    }
    return referenceRecordingRepository.findByPieceId(pieceId);
  }

  @Override
  public ReferenceRecording createReference(Long pieceId, ReferenceRecording reference) {
    MusicalPiece piece = musicalPieceRepository.findById(pieceId)
        .orElseThrow(() -> new EntityNotFoundException("Musical piece not found with id: " + pieceId));

    ReferenceRecording spotifyTrack = spotifyService.getTrackById(reference.getSpotifyTrackId());

    reference.setTitle(spotifyTrack.getTitle());
    reference.setArtistName(spotifyTrack.getArtistName());
    reference.setPiece(piece);

    return referenceRecordingRepository.save(reference);
  }

  @Override
  public void deleteReference(Long pieceId, Long referenceId) {
    ReferenceRecording reference = referenceRecordingRepository.findById(referenceId)
        .orElseThrow(() -> new EntityNotFoundException("Reference recording not found with id: " + referenceId));

    if (!reference.getPiece().getId().equals(pieceId)) {
      throw new IllegalArgumentException("Reference recording does not belong to the specified piece");
    }

    referenceRecordingRepository.delete(reference);
  }
}