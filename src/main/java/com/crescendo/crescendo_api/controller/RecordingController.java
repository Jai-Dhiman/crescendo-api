package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.model.Recording;
import com.crescendo.crescendo_api.service.RecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces/{pieceId}/recordings")
@RequiredArgsConstructor
public class RecordingController {
  private final RecordingService recordingService;

  @GetMapping
  public ResponseEntity<List<Recording>> getRecordingsByPiece(@PathVariable Long pieceId) {
    return ResponseEntity.ok(recordingService.getRecordingsByPieceId(pieceId));
  }

  @GetMapping("/{recordingId}")
  public ResponseEntity<Recording> getRecording(
      @PathVariable Long pieceId,
      @PathVariable Long recordingId) {
    return ResponseEntity.ok(recordingService.getRecording(pieceId, recordingId));
  }

  @PostMapping
  public ResponseEntity<Recording> createRecording(
      @PathVariable Long pieceId,
      @RequestBody Recording recording) {
    return ResponseEntity.ok(recordingService.createRecording(pieceId, recording));
  }

  @PutMapping("/{recordingId}")
  public ResponseEntity<Recording> updateRecording(
      @PathVariable Long pieceId,
      @PathVariable Long recordingId,
      @RequestBody Recording recording) {
    return ResponseEntity.ok(recordingService.updateRecording(pieceId, recordingId, recording));
  }

  @DeleteMapping("/{recordingId}")
  public ResponseEntity<Void> deleteRecording(
      @PathVariable Long pieceId,
      @PathVariable Long recordingId) {
    recordingService.deleteRecording(pieceId, recordingId);
    return ResponseEntity.noContent().build();
  }
}