package com.crescendo.crescendo_api.repository;

import com.crescendo.crescendo_api.model.ReferenceRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReferenceRecordingRepository extends JpaRepository<ReferenceRecording, Long> {
  List<ReferenceRecording> findByMusicalPieceId(Long musicalPieceId);

  Optional<ReferenceRecording> findBySpotifyTrackId(String spotifyTrackId);
}