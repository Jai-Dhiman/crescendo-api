package com.crescendo.crescendo_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableScheduling
public class DatabaseBackupConfig {

  @Value("${spring.datasource.url}")
  private String databaseUrl;

  private static final String BACKUP_DIR = "database-backups";

  @Scheduled(cron = "0 0 0 * * ?") // Run at midnight every day
  public void backupDatabase() {
    try {
      // Create backup directory if it doesn't exist
      new File(BACKUP_DIR).mkdirs();

      // Extract database file path from URL
      String dbPath = databaseUrl.replace("jdbc:sqlite:", "");

      // Create backup file name with timestamp
      String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
      String backupPath = BACKUP_DIR + "/crescendo_backup_" + timestamp + ".db";

      // Copy database file to backup location
      Files.copy(Path.of(dbPath), Path.of(backupPath), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
      // In a production environment, you'd want to log this error properly
    }
  }
}