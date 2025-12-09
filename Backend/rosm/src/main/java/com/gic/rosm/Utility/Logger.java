package com.gic.rosm.Utility;

import org.springframework.stereotype.Component;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static Logger instance;
    private final String logFilePath;
    private final DateTimeFormatter formatter;

    // Private constructor
    private Logger() {

        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.logFilePath = "Logs//" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) + "_application.log";
    }

    // Thread-safe singleton getter
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Generic log writer
    private void write(String level, String className,String message) {
        try{
           // Ensure parent directories exist
            File logFile = new File(logFilePath);
            File parentDir = logFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs(); // create directories if not exist
            }

            // Ensure file exists
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
                String timestamp = LocalDateTime.now().format(formatter);
                writer.printf("[%s] [%s] [%s] %s%n", timestamp, level, className, message);
            }
        }
        catch (IOException e) {
            // fallback to console
            System.err.println("Logger Error: " + e.getMessage());
        }
    }

    // Log Types
    public void info(String className,String message) {
        write("INFO",className, message);
    }

    public void error(String className,String message) {
        write("ERROR",className, message);
    }

    public void debug(String className,String message) {
        write("DEBUG",className, message);
    }
}

