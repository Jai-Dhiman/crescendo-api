package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.dto.ReferenceRecordingDTO;
import com.crescendo.crescendo_api.service.ReferenceRecordingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reference-recordings")
public class ReferenceRecordingController {
  private final ReferenceRecordingService referenceRecordingService;

  public ReferenceRecordingController(ReferenceRecordingService referenceRecordingService) {
    this.referenceRecordingService = referenceRecordingService;
  }

  @PostMapping
  public ResponseEntity<ReferenceRecordingDTO> createReferenceRecording(
      @RequestBody ReferenceRecordingDTO referenceRecordingDTO) {
    return ResponseEntity.ok(referenceRecordingService.createReferenceRecording(referenceRecordingDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReferenceRecordingDTO> getReferenceRecording(@PathVariable Long id) {
    return ResponseEntity.ok(referenceRecordingService.getReferenceRecording(id));
  }

  @GetMapping
  public ResponseEntity<List<ReferenceRecordingDTO>> getAllReferenceRecordings() {
    return ResponseEntity.ok(referenceRecordingService.getAllReferenceRecordings());
  }

  @GetMapping("/by-piece/{musicalPieceId}")
  public ResponseEntity<List<ReferenceRecordingDTO>> getReferenceRecordingsByMusicalPiece(
      @PathVariable Long musicalPieceId) {
    return ResponseEntity.ok(
        referenceRecordingService.getReferenceRecordingsByMusicalPiece(musicalPieceId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ReferenceRecordingDTO> updateReferenceRecording(
      @PathVariable Long id,
      @RequestBody ReferenceRecordingDTO referenceRecordingDTO) {
    return ResponseEntity.ok(
        referenceRecordingService.updateReferenceRecording(id, referenceRecordingDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReferenceRecording(@PathVariable Long id) {
    referenceRecordingService.deleteReferenceRecording(id);
    return ResponseEntity.noContent().build();
  }
}