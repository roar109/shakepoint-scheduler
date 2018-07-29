package com.shakepoint.executions;

import com.shakepoint.dto.ScheduledExecution;

//TODO review naming
public interface Execution {

        void schedule(final ScheduledExecution scheduledExecution);
}
