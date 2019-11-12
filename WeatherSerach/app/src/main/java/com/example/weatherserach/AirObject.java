package com.example.weatherserach;

public class AirObject {
    String airCityName;
    String aircityNameEng;
    double airPm25Value;
    String airDataTime;
    double airPm10Value;
    String airSidoName;

    public AirObject() {
    }

    public AirObject(String airCityName, String aircityNameEng, double airPm25Value, String airDataTime, double airPm10Value, String airSidoName) {
        this.airCityName = airCityName;
        this.aircityNameEng = aircityNameEng;
        this.airPm25Value = airPm25Value;
        this.airDataTime = airDataTime;
        this.airPm10Value = airPm10Value;
        this.airSidoName = airSidoName;
    }

    public String getAirCityName() {
        return airCityName;
    }

    public void setAirCityName(String airCityName) {
        this.airCityName = airCityName;
    }

    public String getAircityNameEng() {
        return aircityNameEng;
    }

    public void setAircityNameEng(String aircityNameEng) {
        this.aircityNameEng = aircityNameEng;
    }

    public double getAirPm25Value() {
        return airPm25Value;
    }

    public void setAirPm25Value(double airPm25Value) {
        this.airPm25Value = airPm25Value;
    }

    public String getAirDataTime() {
        return airDataTime;
    }

    public void setAirDataTime(String airDataTime) {
        this.airDataTime = airDataTime;
    }

    public double getAirPm10Value() {
        return airPm10Value;
    }

    public void setAirPm10Value(double airPm10Value) {
        this.airPm10Value = airPm10Value;
    }

    public String getAirSidoName() {
        return airSidoName;
    }

    public void setAirSidoName(String airSidoName) {
        this.airSidoName = airSidoName;
    }

    @Override
    public String toString() {
        return "AirObject{" +
                "airCityName='" + airCityName + '\'' +
                ", aircityNameEng='" + aircityNameEng + '\'' +
                ", airPm25Value='" + airPm25Value + '\'' +
                ", airDataTime='" + airDataTime + '\'' +
                ", airPm10Value='" + airPm10Value + '\'' +
                ", airSidoName='" + airSidoName + '\'' +
                '}';
    }
}
