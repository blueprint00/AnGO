package com.e.air;

public class AirGuObject {

    String cityName;
    //String cityNameEng;
    String coValue;
    String dataTime;
    //String districtCode;
    //String districtNumSeq;
    String no2Value;
    //String numOfRows;
    String o3Value;
    String pm10Value;
    String pm25Value;
    String sidoName;
    String so2Value;

    public AirGuObject(String cityName, String coValue, String dataTime, String no2Value, String o3Value, String pm10Value, String pm25Value, String sidoName, String so2Value) {
        this.cityName = cityName;
        //this.cityNameEng = cityNameEng;
        this.coValue = coValue;
        this.dataTime = dataTime;
        //this.districtCode = districtCode;
        //this.districtNumSeq = districtNumSeq;
        this.no2Value = no2Value;
        //this.numOfRows = numOfRows;
        this.o3Value = o3Value;
        this.pm10Value = pm10Value;
        this.pm25Value = pm25Value;
        this.sidoName = sidoName;
        this.so2Value = so2Value;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCoValue(String coValue) {
        this.coValue = coValue;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public void setNo2Value(String no2Value) {
        this.no2Value = no2Value;
    }

    public void setO3Value(String o3Value) {
        this.o3Value = o3Value;
    }

    public void setPm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }

    public void setPm25Value(String pm25Value) {
        this.pm25Value = pm25Value;
    }

    public void setSidoName(String sidoName) {
        this.sidoName = sidoName;
    }

    public void setSo2Value(String so2Value) {
        this.so2Value = so2Value;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCoValue() {
        return coValue;
    }

    public String getDataTime() {
        return dataTime;
    }

    public String getNo2Value() {
        return no2Value;
    }

    public String getO3Value() {
        return o3Value;
    }

    public String getPm10Value() {
        return pm10Value;
    }

    public String getPm25Value() {
        return pm25Value;
    }

    public String getSidoName() {
        return sidoName;
    }

    public String getSo2Value() {
        return so2Value;
    }
}
