package com.crescendo.crescendo_api.controller;

import com.crescendo.crescendo_api.model.ReferenceRecording;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.crescendo.crescendo_api.service.SpotifyService;

@RestController
@RequestMapping("/api/spotify")
@RequiredArgsConstructor
public class SpotifyController {
  private final SpotifyService spotifyService;

  @GetMapping("/search")
  public ResponseEntity<List<ReferenceRecording>> searchTracks(@RequestParam String query) {
    return ResponseEntity.ok(spotifyService.searchTracks(query));
  }

  @GetMapping("/tracks/{trackId}")
  public ResponseEntity<ReferenceRecording> getTrack(@PathVariable String trackId) {
    return ResponseEntity.ok(spotifyService.getTrackById(trackId));
  }
}
