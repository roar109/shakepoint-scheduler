package com.shakepoint.scheduler;

import com.shakepoint.dto.ScheduledExecution;
import com.shakepoint.dto.TimerDetails;
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
        private static final String TIMER_PROPERTY_NAME = "scheduler.timer.attribute";

        private static final Logger log = Logger.getLogger(PropertiesParser.class);
        private List<ScheduledExecution> scheduledExecutions;

        public PropertiesParser convertProperties(final Properties configurationProperties) {
                final String availableSchedulers = configurationProperties.getProperty(CONFIGURATION_PROPERTY_NAME);
                log.info(configurationProperties);
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

                //Get scheduler related information
                readTimerPropertiesIfExists(se, configurationProperties);

                return se;
        }

        private void readTimerPropertiesIfExists(ScheduledExecution se, final Properties configurationProperties) {
                TimerDetails td = new TimerDetails();

                final String secondProperty = buildTimerPropertyName(se.getName(), "second");

                if (configurationProperties.containsKey(secondProperty)) {
                        td.setSecond(String.valueOf(configurationProperties.get(secondProperty)));
                }

                final String minuteProperty = buildTimerPropertyName(se.getName(), "minute");

                if (configurationProperties.containsKey(minuteProperty)) {
                        td.setMinute(String.valueOf(configurationProperties.get(minuteProperty)));
                }

                final String hourProperty = buildTimerPropertyName(se.getName(), "hour");

                if (configurationProperties.containsKey(hourProperty)) {
                        td.setHour(String.valueOf(configurationProperties.get(hourProperty)));
                }

                final String dayOfWeekProperty = buildTimerPropertyName(se.getName(), "dayOfWeek");

                if (configurationProperties.containsKey(dayOfWeekProperty)) {
                        td.setDayOfWeek(String.valueOf(configurationProperties.get(dayOfWeekProperty)));
                }

                final String dayOfMonthProperty = buildTimerPropertyName(se.getName(), "dayOfMonth");

                if (configurationProperties.containsKey(dayOfMonthProperty)) {
                        td.setDayOfMonth(String.valueOf(configurationProperties.get(dayOfMonthProperty)));
                }

                final String monthProperty = buildTimerPropertyName(se.getName(), "month");

                if (configurationProperties.containsKey(monthProperty)) {
                        td.setMonth(String.valueOf(configurationProperties.get(monthProperty)));
                }

                final String yearProperty = buildTimerPropertyName(se.getName(), "year");

                if (configurationProperties.containsKey(yearProperty)) {
                        td.setYear(String.valueOf(configurationProperties.get(yearProperty)));
                }

                se.setTimerDetails(td);
        }

        private String buildTimerPropertyName(final String name, final String attribute) {
                return new StringBuilder(name).append(PROPERTIES_SEPARATOR).append(TIMER_PROPERTY_NAME).append(PROPERTIES_SEPARATOR).append(attribute).toString();
        }

        public List<ScheduledExecution> getScheduledExecutions() {
                return scheduledExecutions;
        }
}
