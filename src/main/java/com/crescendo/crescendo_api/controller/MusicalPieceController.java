package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.model.MusicalPiece;
import com.crescendo.crescendo_api.service.MusicalPieceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces")
@RequiredArgsConstructor
public class MusicalPieceController {
  private final MusicalPieceService musicalPieceService;

  @GetMapping
  public ResponseEntity<List<MusicalPiece>> getAllPieces() {
    return ResponseEntity.ok(musicalPieceService.getAllPieces());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MusicalPiece> getPiece(@PathVariable Long id) {
    return ResponseEntity.ok(musicalPieceService.getPieceById(id));
  }

  @PostMapping
  public ResponseEntity<MusicalPiece> createPiece(@RequestBody MusicalPiece piece) {
    return ResponseEntity.ok(musicalPieceService.createPiece(piece));
  }

  @PutMapping("/{id}")
  public ResponseEntity<MusicalPiece> updatePiece(@PathVariable Long id, @RequestBody MusicalPiece piece) {
    return ResponseEntity.ok(musicalPieceService.updatePiece(id, piece));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePiece(@PathVariable Long id) {
    musicalPieceService.deletePiece(id);
    return ResponseEntity.noContent().build();
  }
}