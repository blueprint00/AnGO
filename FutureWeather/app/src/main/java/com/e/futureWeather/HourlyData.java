package com.e.futureWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HourlyData {
    long time;
    String summary;
    String icon;
    double temperature;
    double humidity;

    @JsonProperty("time")
    public long getTime() { return time; }
    @JsonProperty("time")
    public void setTime(long value) { this.time = value; }

    @JsonProperty("summary")
    public String getSummary() { return summary; }
    @JsonProperty("summary")
    public void setSummary(String value) { this.summary = value; }

    @JsonProperty("icon")
    public String getIcon() { return icon; }
    @JsonProperty("icon")
    public void setIcon(String value) { this.icon = value; }

    @JsonProperty("temperature")
    public double getTemperature() { return temperature; }
    @JsonProperty("temperature")
    public void setTemperature(double value) { this.temperature = value; }

    @JsonProperty("humidity")
    public double getHumidity() { return humidity; }
    @JsonProperty("humidity")
    public void setHumidity(double value) { this.humidity = value; }
}
