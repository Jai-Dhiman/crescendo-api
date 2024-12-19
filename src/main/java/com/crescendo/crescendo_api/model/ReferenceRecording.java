package com.crescendo.crescendo_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ReferenceRecording {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String spotifyTrackId;
  private String artistName;
  private String title;
  @ManyToOne
  @JoinColumn(name = "piece_id")
  private MusicalPiece piece;
}