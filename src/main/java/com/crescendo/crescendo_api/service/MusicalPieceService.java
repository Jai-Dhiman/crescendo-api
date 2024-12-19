package com.crescendo.crescendo_api.service;

import com.crescendo.crescendo_api.model.MusicalPiece;
import java.util.List;

public interface MusicalPieceService {
  List<MusicalPiece> getAllPieces();

  MusicalPiece getPieceById(Long id);

  MusicalPiece createPiece(MusicalPiece piece);

  MusicalPiece updatePiece(Long id, MusicalPiece piece);

  void deletePiece(Long id);
}