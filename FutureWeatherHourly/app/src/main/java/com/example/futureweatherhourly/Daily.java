package com.example.futureweatherhourly;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Daily {
    protected ArrayList<Datum> data;

    @JsonProperty("data")
    public ArrayList<Datum> getData() { return data; }
    @JsonProperty("data")
    public void setData(ArrayList<Datum> value) { this.data = value; }
}
