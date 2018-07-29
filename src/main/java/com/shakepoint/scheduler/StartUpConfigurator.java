package com.shakepoint.scheduler;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Startup
@Singleton
public class StartUpConfigurator {

    @Inject
    private Logger log;

    @Inject
    @Named("fileNameConfiguration")
    private Event<String> configurationEvent;

    @PostConstruct public void init() {
        log.info("starting...");
            configurationEvent.fire(System.getProperty("com.shakepoint.scheduler.configuration.file"));
    }
}
