package com.crescendo.crescendo_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "practice_sessions")
public class PracticeSession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Integer duration; // in minutes

  @Column(nullable = false)
  private LocalDateTime dateTime;

  @Column(columnDefinition = "TEXT")
  private String notes;

  @ManyToOne
  @JoinColumn(name = "musical_piece_id", nullable = false)
  private MusicalPiece musicalPiece;

  // Getters, setters, constructors
}