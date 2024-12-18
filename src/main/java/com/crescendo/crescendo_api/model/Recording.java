package com.crescendo.crescendo_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recordings")
public class Recording {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "file_path", nullable = false)
  private String filePath;

  @Column(nullable = false)
  private LocalDateTime dateRecorded;

  @Column(columnDefinition = "TEXT")
  private String notes;

  @ManyToOne
  @JoinColumn(name = "musical_piece_id", nullable = false)
  private MusicalPiece musicalPiece;

  // Getters, setters, constructors
}