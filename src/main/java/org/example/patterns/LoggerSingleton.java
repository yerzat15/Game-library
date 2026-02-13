package org.example.patterns;

import java.time.LocalDateTime;

public class LoggerSingleton {

    private static LoggerSingleton instance;

    private LoggerSingleton() {}

    public static synchronized LoggerSingleton getInstance() {
        if (instance == null) {
            instance = new LoggerSingleton();
        }
        return instance;
    }

    public void info(String msg) {
        System.out.println(LocalDateTime.now() + " [INFO] " + msg);
    }

    public void error(String msg) {
        System.out.println(LocalDateTime.now() + " [ERROR] " + msg);
    }
}
