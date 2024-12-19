package com.crescendo.crescendo_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class PracticeSession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime dateTime;
  private Integer duration;
  private String notes;
  @ManyToOne
  @JoinColumn(name = "piece_id")
  private MusicalPiece piece;
}