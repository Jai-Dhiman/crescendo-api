package com.crescendo.crescendo_api.service;

import com.crescendo.crescendo_api.dto.MusicalPieceDTO;
import com.crescendo.crescendo_api.model.MusicalPiece;
import com.crescendo.crescendo_api.repository.MusicalPieceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MusicalPieceService {

  private final MusicalPieceRepository musicalPieceRepository;

  public MusicalPieceService(MusicalPieceRepository musicalPieceRepository) {
    this.musicalPieceRepository = musicalPieceRepository;
  }

  public MusicalPieceDTO createMusicalPiece(MusicalPieceDTO dto) {
    MusicalPiece piece = convertToEntity(dto);
    MusicalPiece savedPiece = musicalPieceRepository.save(piece);
    return convertToDTO(savedPiece);
  }

  public MusicalPieceDTO getMusicalPiece(Long id) {
    MusicalPiece piece = musicalPieceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Musical piece not found"));
    return convertToDTO(piece);
  }

  public List<MusicalPieceDTO> getAllMusicalPieces() {
    return musicalPieceRepository.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public MusicalPieceDTO updateMusicalPiece(Long id, MusicalPieceDTO dto) {
    MusicalPiece piece = musicalPieceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Musical piece not found"));

    piece.setTitle(dto.getTitle());
    piece.setPdfFilePath(dto.getPdfFilePath());
    piece.setSavedAnnotations(dto.getSavedAnnotations());

    return convertToDTO(musicalPieceRepository.save(piece));
  }

  public void deleteMusicalPiece(Long id) {
    musicalPieceRepository.deleteById(id);
  }

  private MusicalPieceDTO convertToDTO(MusicalPiece piece) {
    return new MusicalPieceDTO(
        piece.getId(),
        piece.getTitle(),
        piece.getPdfFilePath(),
        piece.getSavedAnnotations());
  }

  private MusicalPiece convertToEntity(MusicalPieceDTO dto) {
    MusicalPiece piece = new MusicalPiece();
    piece.setTitle(dto.getTitle());
    piece.setPdfFilePath(dto.getPdfFilePath());
    piece.setSavedAnnotations(dto.getSavedAnnotations());
    return piece;
  }
}