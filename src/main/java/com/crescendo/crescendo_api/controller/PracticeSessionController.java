package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.dto.PracticeSessionDTO;
import com.crescendo.crescendo_api.service.PracticeSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practice-sessions")
public class PracticeSessionController {

  private final PracticeSessionService practiceSessionService;

  public PracticeSessionController(PracticeSessionService practiceSessionService) {
    this.practiceSessionService = practiceSessionService;
  }

  @PostMapping
  public ResponseEntity<PracticeSessionDTO> createPracticeSession(@RequestBody PracticeSessionDTO practiceSessionDTO) {
    return ResponseEntity.ok(practiceSessionService.createPracticeSession(practiceSessionDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PracticeSessionDTO> getPracticeSession(@PathVariable Long id) {
    return ResponseEntity.ok(practiceSessionService.getPracticeSession(id));
  }

  @GetMapping
  public ResponseEntity<List<PracticeSessionDTO>> getAllPracticeSessions() {
    return ResponseEntity.ok(practiceSessionService.getAllPracticeSessions());
  }

  @GetMapping("/by-piece/{musicalPieceId}")
  public ResponseEntity<List<PracticeSessionDTO>> getPracticeSessionsByMusicalPiece(@PathVariable Long musicalPieceId) {
    return ResponseEntity.ok(practiceSessionService.getPracticeSessionsByMusicalPiece(musicalPieceId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PracticeSessionDTO> updatePracticeSession(
      @PathVariable Long id,
      @RequestBody PracticeSessionDTO practiceSessionDTO) {
    return ResponseEntity.ok(practiceSessionService.updatePracticeSession(id, practiceSessionDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePracticeSession(@PathVariable Long id) {
    practiceSessionService.deletePracticeSession(id);
    return ResponseEntity.noContent().build();
  }
}