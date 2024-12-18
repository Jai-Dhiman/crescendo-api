package com.crescendo.crescendo_api.dto;

public class MusicalPieceDTO {
  private Long id;
  private String title;
  private String pdfFilePath;
  private String savedAnnotations;

  // Constructors
  public MusicalPieceDTO() {
  }

  public MusicalPieceDTO(Long id, String title, String pdfFilePath, String savedAnnotations) {
    this.id = id;
    this.title = title;
    this.pdfFilePath = pdfFilePath;
    this.savedAnnotations = savedAnnotations;
  }

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