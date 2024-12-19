package com.crescendo.crescendo_api.service;

import com.crescendo.crescendo_api.model.Recording;
import java.util.List;

public interface RecordingService {
  List<Recording> getRecordingsByPieceId(Long pieceId);

  Recording getRecording(Long pieceId, Long recordingId);

  Recording createRecording(Long pieceId, Recording recording);

  Recording updateRecording(Long pieceId, Long recordingId, Recording recording);

  void deleteRecording(Long pieceId, Long recordingId);
}