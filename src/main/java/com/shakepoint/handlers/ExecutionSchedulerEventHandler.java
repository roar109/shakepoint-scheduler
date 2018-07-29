package com.shakepoint.handlers;

import com.shakepoint.dto.ScheduledExecution;
import org.apache.log4j.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Singleton public class ExecutionSchedulerEventHandler {

        @Inject private Logger log;

        @Asynchronous public void handleEvent(@Observes final ScheduledExecution scheduledExecution) {
                log.info(String.format("Executing event from %s", scheduledExecution));
        }
}
