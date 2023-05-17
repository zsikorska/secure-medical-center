package com.example.ua.secorgapp.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j {
    private Logger logger;

    public Log4j(String className){
         this.logger = LogManager.getLogger(className);
    }

    public void error(String msg){
        logger.error(msg);
    }

    public void warn(String msg){
        logger.warn(msg);
    }

    public void info(String msg){
        logger.info(msg);
    }
}