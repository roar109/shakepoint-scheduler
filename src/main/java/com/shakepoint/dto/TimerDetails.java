package com.shakepoint.dto;

public class TimerDetails {

        private String second;
        private String minute;
        private String hour;
        private String dayOfWeek;
        private String dayOfMonth;
        private String month;
        private String year;

        public String getSecond() {
                return second;
        }

        public void setSecond(String second) {
                this.second = second;
        }

        public String getMinute() {
                return minute;
        }

        public void setMinute(String minute) {
                this.minute = minute;
        }

        public String getHour() {
                return hour;
        }

        public void setHour(String hour) {
                this.hour = hour;
        }

        public String getDayOfWeek() {
                return dayOfWeek;
        }

        public void setDayOfWeek(String dayOfWeek) {
                this.dayOfWeek = dayOfWeek;
        }

        public String getDayOfMonth() {
                return dayOfMonth;
        }

        public void setDayOfMonth(String dayOfMonth) {
                this.dayOfMonth = dayOfMonth;
        }

        public String getMonth() {
                return month;
        }

        public void setMonth(String month) {
                this.month = month;
        }

        public String getYear() {
                return year;
        }

        public void setYear(String year) {
                this.year = year;
        }

        @Override public String toString() {
                return "TimerDetails{" + "second='" + second + '\'' + ", minute='" + minute + '\'' + ", hour='" + hour + '\'' + ", dayOfWeek='" + dayOfWeek + '\'' + ", dayOfMonth='" + dayOfMonth + '\'' + ", month='" + month + '\'' + ", year='" + year + '\'' + '}';
        }
}
