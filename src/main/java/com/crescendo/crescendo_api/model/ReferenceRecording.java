package com.crescendo.crescendo_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reference_recordings")
public class ReferenceRecording {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "spotify_track_id", nullable = false)
  private String spotifyTrackId;

  @Column(nullable = false)
  private String artistName;

  @Column(nullable = false)
  private String recordingTitle;

  @Column(columnDefinition = "TEXT")
  private String notes;

  @ManyToOne
  @JoinColumn(name = "musical_piece_id", nullable = false)
  private MusicalPiece musicalPiece;

  // Getters, setters, constructors
}