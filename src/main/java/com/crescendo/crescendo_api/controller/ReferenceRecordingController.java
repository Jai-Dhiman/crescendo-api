package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.model.ReferenceRecording;
import com.crescendo.crescendo_api.service.ReferenceRecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces/{pieceId}/references")
@RequiredArgsConstructor
public class ReferenceRecordingController {
  private final ReferenceRecordingService referenceRecordingService;

  @GetMapping
  public ResponseEntity<List<ReferenceRecording>> getReferencesByPiece(@PathVariable Long pieceId) {
    return ResponseEntity.ok(referenceRecordingService.getReferencesByPieceId(pieceId));
  }

  @PostMapping
  public ResponseEntity<ReferenceRecording> createReference(
      @PathVariable Long pieceId,
      @RequestBody ReferenceRecording reference) {
    return ResponseEntity.ok(referenceRecordingService.createReference(pieceId, reference));
  }

  @DeleteMapping("/{referenceId}")
  public ResponseEntity<Void> deleteReference(
      @PathVariable Long pieceId,
      @PathVariable Long referenceId) {
    referenceRecordingService.deleteReference(pieceId, referenceId);
    return ResponseEntity.noContent().build();
  }
}