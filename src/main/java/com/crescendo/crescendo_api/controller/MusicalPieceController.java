package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.dto.MusicalPieceDTO;
import com.crescendo.crescendo_api.service.MusicalPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musical-pieces")
public class MusicalPieceController {

  private final MusicalPieceService musicalPieceService;

  @Autowired
  public MusicalPieceController(MusicalPieceService musicalPieceService) {
    this.musicalPieceService = musicalPieceService;
  }

  @PostMapping
  public ResponseEntity<MusicalPieceDTO> createMusicalPiece(@RequestBody MusicalPieceDTO musicalPieceDTO) {
    return ResponseEntity.ok(musicalPieceService.createMusicalPiece(musicalPieceDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MusicalPieceDTO> getMusicalPiece(@PathVariable Long id) {
    return ResponseEntity.ok(musicalPieceService.getMusicalPiece(id));
  }

  @GetMapping
  public ResponseEntity<List<MusicalPieceDTO>> getAllMusicalPieces() {
    return ResponseEntity.ok(musicalPieceService.getAllMusicalPieces());
  }

  @PutMapping("/{id}")
  public ResponseEntity<MusicalPieceDTO> updateMusicalPiece(
      @PathVariable Long id,
      @RequestBody MusicalPieceDTO musicalPieceDTO) {
    return ResponseEntity.ok(musicalPieceService.updateMusicalPiece(id, musicalPieceDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMusicalPiece(@PathVariable Long id) {
    musicalPieceService.deleteMusicalPiece(id);
    return ResponseEntity.noContent().build();
  }
}