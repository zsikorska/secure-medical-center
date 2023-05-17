package com.example.ua.secorgapp.logger;

public class LoggerFactory {
    public static Log4j createLogger(String className){
        return new Log4j(className);
    }
}
