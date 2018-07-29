package com.shakepoint.dto;

public class ScheduledExecution {

        private String name;
        private boolean enabled;
        private String queueName;
        private String schedulerExpression;

        public ScheduledExecution() {
        }

        public ScheduledExecution(String name, String queueName, String schedulerExpression) {
                this.name = name;
                this.queueName = queueName;
                this.schedulerExpression = schedulerExpression;
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

        public String getSchedulerExpression() {
                return schedulerExpression;
        }

        public void setSchedulerExpression(String schedulerExpression) {
                this.schedulerExpression = schedulerExpression;
        }

        @Override public String toString() {
                return "ScheduledExecution{" + "name='" + name + '\'' + ", enabled=" + enabled + ", queueName='" + queueName + '\'' + ", schedulerExpression='" + schedulerExpression + '\'' + '}';
        }

}
