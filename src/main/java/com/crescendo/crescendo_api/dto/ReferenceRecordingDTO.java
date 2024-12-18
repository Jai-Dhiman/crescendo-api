package com.crescendo.crescendo_api.dto;

public class ReferenceRecordingDTO {
  private Long id;
  private String spotifyTrackId;
  private String artistName;
  private String recordingTitle;
  private String notes;
  private Long musicalPieceId;

  public ReferenceRecordingDTO() {
  }

  public ReferenceRecordingDTO(Long id, String spotifyTrackId, String artistName,
      String recordingTitle, String notes, Long musicalPieceId) {
    this.id = id;
    this.spotifyTrackId = spotifyTrackId;
    this.artistName = artistName;
    this.recordingTitle = recordingTitle;
    this.notes = notes;
    this.musicalPieceId = musicalPieceId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSpotifyTrackId() {
    return spotifyTrackId;
  }

  public void setSpotifyTrackId(String spotifyTrackId) {
    this.spotifyTrackId = spotifyTrackId;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public String getRecordingTitle() {
    return recordingTitle;
  }

  public void setRecordingTitle(String recordingTitle) {
    this.recordingTitle = recordingTitle;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Long getMusicalPieceId() {
    return musicalPieceId;
  }

  public void setMusicalPieceId(Long musicalPieceId) {
    this.musicalPieceId = musicalPieceId;
  }
}