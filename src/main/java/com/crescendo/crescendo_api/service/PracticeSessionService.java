package com.crescendo.crescendo_api.service;

import com.crescendo.crescendo_api.model.PracticeSession;
import java.util.List;

public interface PracticeSessionService {
  List<PracticeSession> getSessionsByPieceId(Long pieceId);

  PracticeSession createSession(Long pieceId, PracticeSession session);

  PracticeSession updateSession(Long pieceId, Long sessionId, PracticeSession session);

  void deleteSession(Long pieceId, Long sessionId);
}