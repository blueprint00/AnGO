package com.e.futureWeather;

public class FutureWeatherObject {
    //hourly
    long timeH;
    String summaryH;
    String iconH;
    double temperatureH;
    double humidityH;

    //daily
    long timeD;
    String summaryD;
    String iconD;
    double humidityD;
    double temperatureMaxD;
    double temperatureMinD;

    public FutureWeatherObject(long timeH, String summaryHD, String iconHD, double temperatureH, double humidityH) {
        this.timeH = timeH;
        this.summaryH = summaryHD;
        this.iconH = iconHD;
        this.temperatureH = temperatureH;
        this.humidityH = humidityH;
    }

    public FutureWeatherObject(long timeD, String summaryDD, String iconDD, double humidityD, double temperatureMaxD, double temperatureMinD) {
        this.timeD = timeD;
        this.summaryD = summaryDD;
        this.iconD = iconDD;
        this.humidityD = humidityD;
        this.temperatureMaxD = temperatureMaxD;
        this.temperatureMinD = temperatureMinD;
    }

    public void setTimeH(long timeH) {
        this.timeH = timeH;
    }

    public void setSummaryH(String summaryHD) {
        this.summaryH = summaryHD;
    }

    public void setIconH(String iconHD) {
        this.iconH = iconHD;
    }

    public void setTemperatureH(double temperatureH) {
        this.temperatureH = temperatureH;
    }

    public void setHumidityH(double humidityH) {
        this.humidityH = humidityH;
    }

    public void setTimeD(long timeD) {
        this.timeD = timeD;
    }

    public void setSummaryD(String summaryDD) {
        this.summaryD = summaryDD;
    }

    public void setIconD(String iconDD) {
        this.iconD = iconDD;
    }

    public void setHumidityD(double humidityD) {
        this.humidityD = humidityD;
    }

    public void setTemperatureMaxD(double temperatureMaxD) {
        this.temperatureMaxD = temperatureMaxD;
    }

    public void setTemperatureMinD(double temperatureMinD) {
        this.temperatureMinD = temperatureMinD;
    }

    public long getTimeH() {
        return timeH;
    }

    public String getSummaryH() {
        return summaryH;
    }

    public String getIconH() {
        return iconH;
    }

    public double getTemperatureH() {
        return temperatureH;
    }

    public double getHumidityH() {
        return humidityH;
    }

    public long getTimeD() {
        return timeD;
    }

    public String getSummaryD() {
        return summaryD;
    }

    public String getIconD() {
        return iconD;
    }

    public double getHumidityD() {
        return humidityD;
    }

    public double getTemperatureMaxD() {
        return temperatureMaxD;
    }

    public double getTemperatureMinD() {
        return temperatureMinD;
    }
}
