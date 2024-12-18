package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.dto.RecordingDTO;
import com.crescendo.crescendo_api.service.RecordingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recordings")
public class RecordingController {
  private final RecordingService recordingService;

  public RecordingController(RecordingService recordingService) {
    this.recordingService = recordingService;
  }

  @PostMapping
  public ResponseEntity<RecordingDTO> createRecording(@RequestBody RecordingDTO recordingDTO) {
    return ResponseEntity.ok(recordingService.createRecording(recordingDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RecordingDTO> getRecording(@PathVariable Long id) {
    return ResponseEntity.ok(recordingService.getRecording(id));
  }

  @GetMapping
  public ResponseEntity<List<RecordingDTO>> getAllRecordings() {
    return ResponseEntity.ok(recordingService.getAllRecordings());
  }

  @GetMapping("/by-piece/{musicalPieceId}")
  public ResponseEntity<List<RecordingDTO>> getRecordingsByMusicalPiece(@PathVariable Long musicalPieceId) {
    return ResponseEntity.ok(recordingService.getRecordingsByMusicalPiece(musicalPieceId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<RecordingDTO> updateRecording(
      @PathVariable Long id,
      @RequestBody RecordingDTO recordingDTO) {
    return ResponseEntity.ok(recordingService.updateRecording(id, recordingDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRecording(@PathVariable Long id) {
    recordingService.deleteRecording(id);
    return ResponseEntity.noContent().build();
  }
}