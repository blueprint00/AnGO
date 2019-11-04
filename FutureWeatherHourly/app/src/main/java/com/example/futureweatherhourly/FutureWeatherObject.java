package com.example.futureweatherhourly;

public class FutureWeatherObject {
    long currentlyTime;
    String currentlyIcon;
    double currentlyTemperature;
    double currentlyHumidity;

    long dailyTime;
    String dailyIcon;
    double dailyHumidity;
    double dailyApparentTemperatureMIn;
    double dailyApparentTemperatureMax;

    public FutureWeatherObject() {
    }

    public FutureWeatherObject(long currentlyTime, String currentlyIcon, double currentlyTemperature, double currentlyHumidity, long dailyTime, String dailyIcon, double dailyHumidity, double dailyApparentTemperatureMax, double dailyApparentTemperatureMIn) {
        this.currentlyTime = currentlyTime;
        this.currentlyIcon = currentlyIcon;
        this.currentlyTemperature = currentlyTemperature;
        this.currentlyHumidity = currentlyHumidity;
        this.dailyTime = dailyTime;
        this.dailyIcon = dailyIcon;
        this.dailyHumidity = dailyHumidity;
        this.dailyApparentTemperatureMIn = dailyApparentTemperatureMIn;
        this.dailyApparentTemperatureMax = dailyApparentTemperatureMax;
    }

    public long getCurrentlyTime() {
        return currentlyTime;
    }

    public void setCurrentlyTime(long currentlyTime) {
        this.currentlyTime = currentlyTime;
    }

    public String getCurrentlyIcon() {
        return currentlyIcon;
    }

    public void setCurrentlyIcon(String currentlyIcon) {
        this.currentlyIcon = currentlyIcon;
    }

    public double getCurrentlyTemperature() {
        return currentlyTemperature;
    }

    public void setCurrentlyTemperature(double currentlyTemperature) {
        this.currentlyTemperature = currentlyTemperature;
    }

    public double getCurrentlyHumidity() {
        return currentlyHumidity;
    }

    public void setCurrentlyHumidity(double currentlyHumidity) {
        this.currentlyHumidity = currentlyHumidity;
    }

    public long getDailyTime() {
        return dailyTime;
    }

    public void setDailyTime(long dailyTime) {
        this.dailyTime = dailyTime;
    }

    public String getDailyIcon() {
        return dailyIcon;
    }

    public void setDailyIcon(String dailyIcon) {
        this.dailyIcon = dailyIcon;
    }

    public double getDailyHumidity() {
        return dailyHumidity;
    }

    public void setDailyHumidity(double dailyHumidity) {
        this.dailyHumidity = dailyHumidity;
    }

    public double getDailyApparentTemperatureMIn() {
        return dailyApparentTemperatureMIn;
    }

    public void setDailyApparentTemperatureMIn(double dailyApparentTemperatureMIn) {
        this.dailyApparentTemperatureMIn = dailyApparentTemperatureMIn;
    }

    public double getDailyApparentTemperatureMax() {
        return dailyApparentTemperatureMax;
    }

    public void setDailyApparentTemperatureMax(double dailyApparentTemperatureMax) {
        this.dailyApparentTemperatureMax = dailyApparentTemperatureMax;
    }

    @Override
    public String toString() {
        return "FutureWeatherObject{" +
                "currentlyTime=" + currentlyTime +
                ", currentlyIcon='" + currentlyIcon + '\'' +
                ", currentlyTemperature=" + currentlyTemperature +
                ", currentlyHumidity=" + currentlyHumidity +
                ", dailyTime=" + dailyTime +
                ", dailyIcon='" + dailyIcon + '\'' +
                ", dailyHumidity=" + dailyHumidity +
                ", dailyApparentTemperatureMIn=" + dailyApparentTemperatureMIn +
                ", getDailyApparentTemperatureMax=" + dailyApparentTemperatureMax +
                '}';
    }
}
