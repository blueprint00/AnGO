package com.example.weatherserach;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class AirList {

    protected String cityName;
    protected String cityNameEng;
    protected double pm25Value;
    protected String dataTime;
    protected double pm10Value;
    protected String sidoName;

    @JsonProperty("cityName")
    public String getCityName() { return cityName; }
    @JsonProperty("cityName")
    public void setCityName(String value) { this.cityName = value; }

    @JsonProperty("cityNameEng")
    public String getCityNameEng() { return cityNameEng; }
    @JsonProperty("cityNameEng")
    public void setCityNameEng(String value) { this.cityNameEng = value; }


    @JsonProperty("dataTime")
    public String getDataTime() { return dataTime; }
    @JsonProperty("dataTime")
    public void setDataTime(String value) { this.dataTime = value; }

    @JsonProperty("pm10Value")
    public double getPm10Value() { return pm10Value; }
    @JsonProperty("pm10Value")
    public void setPm10Value(double value) { this.pm10Value = value; }

    @JsonProperty("pm25Value")
    public double getPm25Value() { return pm25Value; }
    @JsonProperty("pm25Value")
    public void setPm25Value(double value) { this.pm25Value = value; }


    @JsonProperty("sidoName")
    public String getSidoName() { return sidoName; }
    @JsonProperty("sidoName")
    public void setSidoName(String value) { this.sidoName = value; }


}
