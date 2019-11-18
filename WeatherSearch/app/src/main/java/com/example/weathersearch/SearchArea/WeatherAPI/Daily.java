package com.example.weathersearch.SearchArea.WeatherAPI;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Daily {
    public ArrayList<Datum> data;

    @JsonProperty("data")
    public ArrayList<Datum> getData() { return data; }
    @JsonProperty("data")
    public void setData(ArrayList<Datum> value) { this.data = value; }
}
