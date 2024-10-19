package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Logs {
    private static final Logger log = LoggerFactory.getLogger(Logs.class);

    public void run(){
        System.out.println("running____");
        log.trace("trace");
        log.error("Printing an error");
        log.debug("Debugging the code");
        log.warn("Last Warning");
    }
}
