package com.crescendo.crescendo_api.service.impl;

import com.crescendo.crescendo_api.service.AudioProcessingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@Service
public class AudioProcessingServiceImpl implements AudioProcessingService {

  @Value("${audio.storage.location}")
  private String audioStorageLocation;

  private final Path rootLocation;

  public AudioProcessingServiceImpl(@Value("${audio.storage.location}") String audioStorageLocation) {
    this.rootLocation = Paths.get(audioStorageLocation);
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize storage location", e);
    }
  }

  @Override
  public String storeAudioFile(MultipartFile file, Long pieceId, Long recordingId) throws IOException {
    if (file.isEmpty()) {
      throw new IllegalArgumentException("Failed to store empty file");
    }

    String originalFilename = file.getOriginalFilename();
    String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf("."))
        : ".wav";

    String newFileName = pieceId + "_" + recordingId + "_" + UUID.randomUUID() + fileExtension;
    Path destinationFile = rootLocation.resolve(newFileName);

    try (InputStream inputStream = file.getInputStream()) {
      Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }

    return newFileName;
  }

  @Override
  public InputStream getAudioFile(String fileName) throws IOException {
    Path file = rootLocation.resolve(fileName);
    return Files.newInputStream(file);
  }

  @Override
  public void deleteAudioFile(String fileName) throws IOException {
    Path file = rootLocation.resolve(fileName);
    Files.deleteIfExists(file);
  }

  @Override
  public byte[] convertAudioFormat(byte[] audioData, String targetFormat) throws IOException {
    try (ByteArrayInputStream bais = new ByteArrayInputStream(audioData);
        ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bais);
      AudioFormat sourceFormat = audioInputStream.getFormat();

      AudioFormat targetAudioFormat = new AudioFormat(
          AudioFormat.Encoding.PCM_SIGNED,
          sourceFormat.getSampleRate(),
          16,
          sourceFormat.getChannels(),
          sourceFormat.getChannels() * 2,
          sourceFormat.getSampleRate(),
          false);

      AudioInputStream convertedStream = AudioSystem.getAudioInputStream(
          targetAudioFormat, audioInputStream);

      AudioSystem.write(convertedStream, AudioFileFormat.Type.WAVE, baos);
      return baos.toByteArray();
    } catch (UnsupportedAudioFileException | IOException e) {
      throw new IOException("Error converting audio format", e);
    }
  }

  @Override
  public double getAudioDuration(String fileName) throws IOException {
    Path file = rootLocation.resolve(fileName);
    try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.toFile())) {
      AudioFormat format = audioInputStream.getFormat();
      long frames = audioInputStream.getFrameLength();
      return (frames / format.getFrameRate());
    } catch (UnsupportedAudioFileException e) {
      throw new IOException("Error reading audio file duration", e);
    }
  }

  @Override
  public byte[] trimAudio(String fileName, double startTime, double endTime) throws IOException {
    throw new UnsupportedOperationException("Audio trimming not implemented yet");
  }
}