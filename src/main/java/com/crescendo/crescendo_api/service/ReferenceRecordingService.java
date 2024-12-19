package com.crescendo.crescendo_api.service;

import com.crescendo.crescendo_api.model.ReferenceRecording;
import java.util.List;

public interface ReferenceRecordingService {
  List<ReferenceRecording> getReferencesByPieceId(Long pieceId);

  ReferenceRecording createReference(Long pieceId, ReferenceRecording reference);

  void deleteReference(Long pieceId, Long referenceId);
}