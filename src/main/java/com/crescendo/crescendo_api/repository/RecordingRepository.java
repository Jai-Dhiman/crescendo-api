package com.crescendo.crescendo_api.repository;

import com.crescendo.crescendo_api.model.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long> {
  List<Recording> findByPieceId(Long pieceId);
}