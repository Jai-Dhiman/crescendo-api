package com.crescendo.crescendo_api.service.impl;

import com.crescendo.crescendo_api.model.MusicalPiece;
import com.crescendo.crescendo_api.repository.MusicalPieceRepository;
import com.crescendo.crescendo_api.service.MusicalPieceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MusicalPieceServiceImpl implements MusicalPieceService {
  private final MusicalPieceRepository musicalPieceRepository;

  @Override
  public List<MusicalPiece> getAllPieces() {
    return musicalPieceRepository.findAll();
  }

  @Override
  public MusicalPiece getPieceById(Long id) {
    return musicalPieceRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Musical piece not found with id: " + id));
  }

  @Override
  public MusicalPiece createPiece(MusicalPiece piece) {
    return musicalPieceRepository.save(piece);
  }

  @Override
  public MusicalPiece updatePiece(Long id, MusicalPiece pieceDetails) {
    MusicalPiece piece = getPieceById(id);
    piece.setTitle(pieceDetails.getTitle());
    if (pieceDetails.getPdfFile() != null) {
      piece.setPdfFile(pieceDetails.getPdfFile());
    }
    return musicalPieceRepository.save(piece);
  }

  @Override
  public void deletePiece(Long id) {
    if (!musicalPieceRepository.existsById(id)) {
      throw new EntityNotFoundException("Musical piece not found with id: " + id);
    }
    musicalPieceRepository.deleteById(id);
  }
}