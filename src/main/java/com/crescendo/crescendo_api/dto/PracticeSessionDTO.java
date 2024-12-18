package com.crescendo.crescendo_api.dto;

import java.time.LocalDateTime;

public class PracticeSessionDTO {
  private Long id;
  private Integer duration;
  private LocalDateTime dateTime;
  private String notes;
  private Long musicalPieceId;

  public PracticeSessionDTO() {
  }

  public PracticeSessionDTO(Long id, Integer duration, LocalDateTime dateTime, String notes, Long musicalPieceId) {
    this.id = id;
    this.duration = duration;
    this.dateTime = dateTime;
    this.notes = notes;
    this.musicalPieceId = musicalPieceId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
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