package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.model.Recording;
import com.crescendo.crescendo_api.service.RecordingService;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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

  @PostMapping("/{recordingId}/audio")
  public ResponseEntity<Recording> uploadAudio(
      @PathVariable Long pieceId,
      @PathVariable Long recordingId,
      @RequestParam("file") MultipartFile file) throws IOException {

    Recording recording = recordingService.getRecording(pieceId, recordingId);
    String fileName = audioProcessingService.storeAudioFile(file, pieceId, recordingId);
    recording.setFileName(fileName);
    return ResponseEntity.ok(recordingService.updateRecording(pieceId, recordingId, recording));
  }

  @GetMapping("/{recordingId}/audio")
  public ResponseEntity<Resource> downloadAudio(
      @PathVariable Long pieceId,
      @PathVariable Long recordingId) throws IOException {

    Recording recording = recordingService.getRecording(pieceId, recordingId);
    InputStream audioStream = audioProcessingService.getAudioFile(recording.getFileName());

    InputStreamResource resource = new InputStreamResource(audioStream);

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType("audio/wav"))
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + recording.getFileName() + "\"")
        .body(resource);
  }
}