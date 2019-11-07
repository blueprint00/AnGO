package com.e.airweather.AirWeather;

import java.util.ArrayList;

public class AirWeatherVO{

    ArrayList<AirGuObject> airGuObject;
    CurWeatherObject curWeatherObject;

    public AirWeatherVO(ArrayList<AirGuObject> airGuObject, CurWeatherObject curWeatherObject) {
        this.airGuObject = airGuObject;
        this.curWeatherObject = curWeatherObject;
    }

    public ArrayList<AirGuObject> getAirGuObject() {
        return airGuObject;
    }

    public void setAirGuObject(ArrayList<AirGuObject> airGuObject) {
        this.airGuObject = airGuObject;
    }

    public CurWeatherObject getCurWeatherObject() {
        return curWeatherObject;
    }

    public void setCurWeatherObject(CurWeatherObject curWeatherObject) {
        this.curWeatherObject = curWeatherObject;
    }
}
