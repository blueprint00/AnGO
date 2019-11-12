package com.example.weatherserach;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class SearchAirJson {
    protected ArrayList<AirList> list;

    @JsonProperty("list")
    public ArrayList<AirList> getList() { return list; }
    @JsonProperty("list")
    public void setList(ArrayList<AirList> value) { this.list = value; }

}
