package com.shakepoint.dto;

public class ScheduledExecution {

        private String name;
        private boolean enabled;
        private String queueName;
        private TimerDetails timerDetails;

        public ScheduledExecution() {
        }

        public ScheduledExecution(String name, String queueName, TimerDetails timerDetails) {
                this.name = name;
                this.queueName = queueName;
                this.timerDetails = timerDetails;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public boolean isEnabled() {
                return enabled;
        }

        public void setEnabled(boolean enabled) {
                this.enabled = enabled;
        }

        public String getQueueName() {
                return queueName;
        }

        public void setQueueName(String queueName) {
                this.queueName = queueName;
        }

        public TimerDetails getTimerDetails() {
                return timerDetails;
        }

        public void setTimerDetails(TimerDetails timerDetails) {
                this.timerDetails = timerDetails;
        }

        @Override public String toString() {
                return "ScheduledExecution{" + "name='" + name + '\'' + ", enabled=" + enabled + ", queueName='" + queueName + '\'' + ", timerDetails=" + timerDetails + '}';
        }
}
