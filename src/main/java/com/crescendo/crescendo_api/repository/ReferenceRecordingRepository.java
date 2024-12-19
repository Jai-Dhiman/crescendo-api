package com.crescendo.crescendo_api.repository;

import com.crescendo.crescendo_api.model.ReferenceRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReferenceRecordingRepository extends JpaRepository<ReferenceRecording, Long> {
  List<ReferenceRecording> findByPieceId(Long pieceId);
}