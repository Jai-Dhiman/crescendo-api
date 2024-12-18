package com.crescendo.crescendo_api.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "musical_pieces")
public class MusicalPiece {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(name = "pdf_file_path")
  private String pdfFilePath;

  @Column(name = "saved_annotations", columnDefinition = "TEXT")
  private String savedAnnotations;

  @OneToMany(mappedBy = "musicalPiece", cascade = CascadeType.ALL)
  private Set<PracticeSession> practiceSessions = new HashSet<>();

  @OneToMany(mappedBy = "musicalPiece", cascade = CascadeType.ALL)
  private Set<Recording> recordings = new HashSet<>();

  @OneToMany(mappedBy = "musicalPiece", cascade = CascadeType.ALL)
  private Set<ReferenceRecording> referenceRecordings = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPdfFilePath() {
    return pdfFilePath;
  }

  public void setPdfFilePath(String pdfFilePath) {
    this.pdfFilePath = pdfFilePath;
  }

  public String getSavedAnnotations() {
    return savedAnnotations;
  }

  public void setSavedAnnotations(String savedAnnotations) {
    this.savedAnnotations = savedAnnotations;
  }
}