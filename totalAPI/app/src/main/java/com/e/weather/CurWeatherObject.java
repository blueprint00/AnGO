package com.e.weather;

public class CurWeatherObject {
    private long time;
    private String summary;
    private String icon;
    private double temperature;
    private double humidity;

    public CurWeatherObject() {
    }

    public CurWeatherObject(long time, String summary, String icon, double temperature, double humidity) {
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public long getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}
