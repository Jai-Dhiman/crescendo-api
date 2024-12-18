package com.crescendo.crescendo_api.repository;

import com.crescendo.crescendo_api.model.MusicalPiece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicalPieceRepository extends JpaRepository<MusicalPiece, Long> {
  // Basic CRUD operations are automatically provided
  // Custom queries can be added here
}