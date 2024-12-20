package com.crescendo.crescendo_api.service.impl;

import com.crescendo.crescendo_api.model.ReferenceRecording;
import com.crescendo.crescendo_api.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpotifyServiceImpl implements SpotifyService {
  private final SpotifyApi spotifyApi;
  private String accessToken;
  private Instant tokenExpiration;

  private void authenticateClient() {
    try {
      ClientCredentials credentials = spotifyApi.clientCredentials().build().execute();
      accessToken = credentials.getAccessToken();
      tokenExpiration = Instant.now().plusSeconds(credentials.getExpiresIn());
      spotifyApi.setAccessToken(accessToken);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      log.error("Error authenticating with Spotify", e);
      throw new RuntimeException("Failed to authenticate with Spotify", e);
    }
  }

  private void ensureValidToken() {
    if (accessToken == null || Instant.now().isAfter(tokenExpiration)) {
      authenticateClient();
    }
  }

  @Override
  public List<ReferenceRecording> searchTracks(String query) {
    ensureValidToken();
    try {
      Paging<Track> searchResults = spotifyApi.searchTracks(query)
          .limit(20)
          .build()
          .execute();

      return Arrays.stream(searchResults.getItems())
          .map(this::convertToReferenceRecording)
          .collect(Collectors.toList());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      log.error("Error searching Spotify tracks", e);
      throw new RuntimeException("Failed to search Spotify tracks", e);
    }
  }

  @Override
  public ReferenceRecording getTrackById(String spotifyTrackId) {
    ensureValidToken();
    try {
      Track track = spotifyApi.getTrack(spotifyTrackId).build().execute();
      return convertToReferenceRecording(track);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      log.error("Error fetching Spotify track", e);
      throw new RuntimeException("Failed to fetch Spotify track", e);
    }
  }

  private ReferenceRecording convertToReferenceRecording(Track track) {
    ReferenceRecording reference = new ReferenceRecording();
    reference.setSpotifyTrackId(track.getId());
    reference.setTitle(track.getName());
    reference.setArtistName(track.getArtists()[0].getName());
    return reference;
  }
}
