package com.shakepoint.scheduler;

import com.shakepoint.dto.ScheduledExecution;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PropertiesParser {

        private static final String CONFIGURATION_PROPERTY_NAME = "schedulers";
        private static final String PROPERTIES_SEPARATOR = ".";
        private static final String ENABLED_PROPERTY_NAME = "enabled";
        private static final String QUEUE_PROPERTY_NAME = "queue.name";
        private static final String PATTERN_PROPERTY_NAME = "pattern";
        private static final Logger log = Logger.getLogger(PropertiesParser.class);
        private List<ScheduledExecution> scheduledExecutions;

        public PropertiesParser convertProperties(final Properties configurationProperties) {
                final String availableSchedulers = configurationProperties.getProperty(CONFIGURATION_PROPERTY_NAME);

                if (availableSchedulers == null) {
                        log.error("No schedulers found in configuration file");
                        return null;
                }

                final String[] splitedSchedulerNames = availableSchedulers.split(",");

                this.scheduledExecutions = Stream.of(splitedSchedulerNames).map(name -> convertToScheduledExecution(name, configurationProperties)).filter(scheduledExpression -> scheduledExpression.isEnabled()).collect(Collectors.toList());

                return this;
        }

        private ScheduledExecution convertToScheduledExecution(final String schedulerName, final Properties configurationProperties) {
                ScheduledExecution se = new ScheduledExecution();

                se.setName(schedulerName);

                final String enabled = String.format("%s%s%s", schedulerName, PROPERTIES_SEPARATOR, ENABLED_PROPERTY_NAME);
                if (configurationProperties.containsKey(enabled)) {
                        se.setEnabled(Boolean.parseBoolean(configurationProperties.getProperty(enabled)));
                } else {
                        log.info(String.format("Scheduled %s is disabled", schedulerName));
                        se.setEnabled(false);
                        return se;
                }

                final String queueName = String.format("%s%s%s", schedulerName, PROPERTIES_SEPARATOR, QUEUE_PROPERTY_NAME);

                if (configurationProperties.containsKey(queueName)) {
                        se.setQueueName(String.valueOf(configurationProperties.get(queueName)));
                }

                return se;
        }

        public List<ScheduledExecution> getScheduledExecutions() {
                return scheduledExecutions;
        }
}
