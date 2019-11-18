package com.example.weathersearch.SearchArea.WeatherAPI;

import com.fasterxml.jackson.annotation.*;

public class SearchWeatherJson {
    private double latitude;
    private double longitude;
    private String timezone;
    public Currently currently;//날짜 + 시간을 검색한 그 시각의 날씨
    public Daily daily;//검색한 날짜의 전체 날씨
    private long offset;

    @JsonProperty("latitude")
    public double getLatitude() { return latitude; }
    @JsonProperty("latitude")
    public void setLatitude(double value) { this.latitude = value; }

    @JsonProperty("longitude")
    public double getLongitude() { return longitude; }
    @JsonProperty("longitude")
    public void setLongitude(double value) { this.longitude = value; }

    @JsonProperty("timezone")
    public String getTimezone() { return timezone; }
    @JsonProperty("timezone")
    public void setTimezone(String value) { this.timezone = value; }

    @JsonProperty("currently")
    public Currently getCurrently() { return currently; }
    @JsonProperty("currently")
    public void setCurrently(Currently value) { this.currently = value; }

    @JsonProperty("daily")
    public Daily getDaily() { return daily; }
    @JsonProperty("daily")
    public void setDaily(Daily value) { this.daily = value; }

    @JsonProperty("offset")
    public long getOffset() { return offset; }
    @JsonProperty("offset")
    public void setOffset(long value) { this.offset = value; }
}
