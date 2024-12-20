package com.crescendo.crescendo_api.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface AudioProcessingService {
  String storeAudioFile(MultipartFile file, Long pieceId, Long recordingId) throws IOException;

  InputStream getAudioFile(String fileName) throws IOException;

  void deleteAudioFile(String fileName) throws IOException;

  byte[] convertAudioFormat(byte[] audioData, String targetFormat) throws IOException;

  double getAudioDuration(String fileName) throws IOException;

  byte[] trimAudio(String fileName, double startTime, double endTime) throws IOException;
}