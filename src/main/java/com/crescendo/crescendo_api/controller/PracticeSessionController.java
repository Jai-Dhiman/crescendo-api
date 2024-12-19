package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.model.PracticeSession;
import com.crescendo.crescendo_api.service.PracticeSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces/{pieceId}/practice-sessions")
@RequiredArgsConstructor
public class PracticeSessionController {
  private final PracticeSessionService practiceSessionService;

  @GetMapping
  public ResponseEntity<List<PracticeSession>> getSessionsByPiece(@PathVariable Long pieceId) {
    return ResponseEntity.ok(practiceSessionService.getSessionsByPieceId(pieceId));
  }

  @PostMapping
  public ResponseEntity<PracticeSession> createSession(
      @PathVariable Long pieceId,
      @RequestBody PracticeSession session) {
    return ResponseEntity.ok(practiceSessionService.createSession(pieceId, session));
  }

  @PutMapping("/{sessionId}")
  public ResponseEntity<PracticeSession> updateSession(
      @PathVariable Long pieceId,
      @PathVariable Long sessionId,
      @RequestBody PracticeSession session) {
    return ResponseEntity.ok(practiceSessionService.updateSession(pieceId, sessionId, session));
  }

  @DeleteMapping("/{sessionId}")
  public ResponseEntity<Void> deleteSession(
      @PathVariable Long pieceId,
      @PathVariable Long sessionId) {
    practiceSessionService.deleteSession(pieceId, sessionId);
    return ResponseEntity.noContent().build();
  }
}