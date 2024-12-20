package com.crescendo.crescendo_api.service;

import com.crescendo.crescendo_api.model.ReferenceRecording;
import java.util.List;

public interface SpotifyService {
  List<ReferenceRecording> searchTracks(String query);

  ReferenceRecording getTrackById(String spotifyTrackId);
}
