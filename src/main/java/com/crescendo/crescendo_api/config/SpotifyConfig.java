package com.crescendo.crescendo_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import se.michaelthelin.spotify.SpotifyApi;

@Configuration
@PropertySource("file:.env")
public class SpotifyConfig {
  @Value("${SPOTIFY_CLIENT_ID}")
  private String clientId;

  @Value("${SPOTIFY_CLIENT_SECRET}")
  private String clientSecret;

  @Bean
  public SpotifyApi spotifyApi() {
    return new SpotifyApi.Builder()
        .setClientId(clientId)
        .setClientSecret(clientSecret)
        .build();
  }
}
