package com.e.ango.API;

public class AirWeatherObject {
    String sidoName; //서울
    String cityName; //구
    String cityNameEng;
    String dataTime; //정시측정시간
    String pm10Value; //미세먼지

    long time;
    String summary;
    String icon;
    double temperature;
    double humidity;

    public AirWeatherObject() {
    }

    public AirWeatherObject(String sidoName, String cityName, String cityNameEng, String dataTime, String pm10Value, long time, String summary, String icon, double temperature, double humidity){
        this.sidoName = sidoName;
        this.cityName = cityName;
        this.cityNameEng = cityNameEng;
        this.dataTime = dataTime;
        this.pm10Value = pm10Value;
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.temperature = temperature;
        this.humidity = humidity;
    }
    public AirWeatherObject(String sidoName, String cityName, String cityNameEng, String dataTime, String pm10Value) {
        this.sidoName = sidoName;
        this.cityName = cityName;
        this.cityNameEng = cityNameEng;
        this.dataTime = dataTime;
        this.pm10Value = pm10Value;
    }

    public AirWeatherObject(long time, String summary, String icon, double temperature, double humidity) {
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getSidoName() {
        return sidoName;
    }

    public void setSidoName(String sidoName) {
        this.sidoName = sidoName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameEng() {
        return cityNameEng;
    }

    public void setCityNameEng(String cityNameEng) {
        this.cityNameEng = cityNameEng;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getPm10Value() {
        return pm10Value;
    }

    public void setPm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
