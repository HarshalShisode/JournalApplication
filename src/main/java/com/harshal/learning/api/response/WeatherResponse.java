package com.harshal.learning.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {

    private Current current;

    public Current getCurrent() { return current; }
    public void setCurrent(Current value) { this.current = value; }

    public class Current {
        @JsonProperty("temp_c")
        private double tempC;
        @JsonProperty("temp_f")
        private double tempF;
        @JsonProperty("is_day")
        private long isDay;
        @JsonProperty("feelslike_c")
        private double feelslikeC;
        @JsonProperty("feelslike_f")
        private double feelslikeF;


        public double getTempC() { return tempC; }
        public void setTempC(double value) { this.tempC = value; }

        public double getTempF() { return tempF; }
        public void setTempF(double value) { this.tempF = value; }

        public long getIsDay() { return isDay; }
        public void setIsDay(long value) { this.isDay = value; }

        public double getFeelslikeC() { return feelslikeC; }
        public void setFeelslikeC(double value) { this.feelslikeC = value; }

        public double getFeelslikeF() { return feelslikeF; }
        public void setFeelslikeF(double value) { this.feelslikeF = value; }

    }

}

