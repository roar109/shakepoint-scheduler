package com.shakepoint.scheduler;

import org.apache.log4j.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

@Singleton
public class SchedulerConfigurator {

    @Inject
    private Logger log;

    @Asynchronous
    @Named("fileNameConfiguration")
    public void configure(@Observes final String propsFileName) {
        log.info(String.format("Receiving properties file on path %s",propsFileName));

    }
}
