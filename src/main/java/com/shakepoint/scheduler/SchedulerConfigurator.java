package com.shakepoint.scheduler;

import com.shakepoint.dto.ScheduledExecution;
import org.apache.log4j.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

@Singleton public class SchedulerConfigurator {

        @Inject private Logger log;

        @Asynchronous @Named("fileNameConfiguration") public void configure(@Observes final String propsFileName) {
                log.info(String.format("Receiving properties file on path %s", propsFileName));

                Properties configurationProperties = new Properties();
                try {
                        configurationProperties.load(new FileInputStream(propsFileName));

                        log.info(String.format("Schedulers found in configuration file %s", configurationProperties.get("schedulers")));

                        List<ScheduledExecution> executions = new PropertiesParser().convertProperties(configurationProperties).getScheduledExecutions();
                        log.info(executions);
                } catch (Exception e) {
                        log.error(e.getMessage(), e);
                }
        }
}
