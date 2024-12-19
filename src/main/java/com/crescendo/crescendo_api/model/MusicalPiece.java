package com.crescendo.crescendo_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class MusicalPiece {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  @Lob
  private byte[] pdfFile;
  @OneToMany(mappedBy = "piece", cascade = CascadeType.ALL)
  private List<PracticeSession> practiceSessions;
  @OneToMany(mappedBy = "piece", cascade = CascadeType.ALL)
  private List<Recording> recordings;
  @OneToMany(mappedBy = "piece", cascade = CascadeType.ALL)
  private List<ReferenceRecording> referenceRecordings;
}