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

  @Scheduled(cron = "0 0 0 * * ?")
  public void backupDatabase() {
    try {
      new File(BACKUP_DIR).mkdirs();

      String dbPath = databaseUrl.replace("jdbc:sqlite:", "");

      String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
      String backupPath = BACKUP_DIR + "/crescendo_backup_" + timestamp + ".db";

      Files.copy(Path.of(dbPath), Path.of(backupPath), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}