package com.shakepoint.repository;

import com.shakepoint.dto.ScheduledExecution;

import javax.ejb.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton public class ExecutionRepository {
        private final Map<String, ScheduledExecution> EXECUTIONS = new ConcurrentHashMap<>();

        public void put(String name, ScheduledExecution scheduledExecution) {
                EXECUTIONS.put(name, scheduledExecution);
        }

        public ScheduledExecution get(String name) {
                return EXECUTIONS.get(name);
        }
}
