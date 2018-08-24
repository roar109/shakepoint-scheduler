package com.shakepoint.handlers;

import com.shakepoint.dto.ScheduledExecution;
import com.shakepoint.dto.TimerDetails;
import com.shakepoint.repository.ExecutionRepository;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Singleton public class ExecutionSchedulerEventHandler {

        @Inject private Logger log;

        @Resource TimerService timerService;

        @Inject private ExecutionRepository executionRepository;

        @Asynchronous public void handleEvent(@Observes final ScheduledExecution scheduledExecution) {
                log.info(String.format("Executing event from %s", scheduledExecution));

                //Register on timer
                Timer timer = timerService.createCalendarTimer(createScheduleExpressionFromTimerDetails(scheduledExecution.getTimerDetails()));

                executionRepository.put(scheduledExecution.getName(), scheduledExecution);

        }

        private ScheduleExpression createScheduleExpressionFromTimerDetails(final TimerDetails timerDetails) {
                final ScheduleExpression scheduleExpression = new ScheduleExpression();
                if (timerDetails.getSecond() != null) {
                        scheduleExpression.second(timerDetails.getSecond());
                }
                if (timerDetails.getMinute() != null) {
                        scheduleExpression.minute(timerDetails.getMinute());
                }
                if (timerDetails.getHour() != null) {
                        scheduleExpression.hour(timerDetails.getHour());
                }
                if (timerDetails.getDayOfWeek() != null) {
                        scheduleExpression.dayOfWeek(timerDetails.getDayOfWeek());
                }
                if (timerDetails.getDayOfMonth() != null) {
                        scheduleExpression.dayOfMonth(timerDetails.getDayOfMonth());
                }
                if (timerDetails.getMonth() != null) {
                        scheduleExpression.month(timerDetails.getMonth());
                }
                if (timerDetails.getYear() != null) {
                        scheduleExpression.year(timerDetails.getYear());
                }
                return scheduleExpression;
        }
}
