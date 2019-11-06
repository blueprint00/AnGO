package com.e.air;

public class AirGuObject {

    String sidoName; //서울
    String cityName; //구
    String dataTime; //정시측정시간
    long pm10Value; //미세먼지
    long pm25Value; //초미세먼지
    String pm10condition;
    String pm25condition;

    public AirGuObject() {
    }

    public AirGuObject(String cityName, String sidoName, String dataTime, long pm10Value, long pm25Value) {
        this.sidoName = sidoName;
        this.cityName = cityName;
        this.dataTime = dataTime;
        this.pm10Value = pm10Value;
        this.pm25Value = pm25Value;
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

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public long getPm10Value() {
        return pm10Value;
    }

    public void setPm10Value(long pm10Value) {
        this.pm10Value = pm10Value;
    }

    public long getPm25Value() {
        return pm25Value;
    }

    public void setPm25Value(long pm25Value) {
        this.pm25Value = pm25Value;
    }

    public String getPm10condition() {
        return pm10condition;
    }

    public void setPm10condition(String pm10condition) {
        this.pm10condition = pm10condition;
    }

    public String getPm25condition() {
        return pm25condition;
    }

    public void setPm25condition(String pm25condition) {
        this.pm25condition = pm25condition;
    }
}
