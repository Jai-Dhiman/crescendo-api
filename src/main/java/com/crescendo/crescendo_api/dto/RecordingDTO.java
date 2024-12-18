package com.crescendo.crescendo_api.dto;

import java.time.LocalDateTime;

public class RecordingDTO {
  private Long id;
  private String filePath;
  private LocalDateTime dateRecorded;
  private String notes;
  private Long musicalPieceId;

  public RecordingDTO() {
  }

  public RecordingDTO(Long id, String filePath, LocalDateTime dateRecorded, String notes, Long musicalPieceId) {
    this.id = id;
    this.filePath = filePath;
    this.dateRecorded = dateRecorded;
    this.notes = notes;
    this.musicalPieceId = musicalPieceId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public LocalDateTime getDateRecorded() {
    return dateRecorded;
  }

  public void setDateRecorded(LocalDateTime dateRecorded) {
    this.dateRecorded = dateRecorded;
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