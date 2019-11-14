package com.e.ango.API;

import com.e.ango.API.Air.AirGuObject;
import com.e.ango.API.Weather.CurWeatherObject;

public class Type {

    AirGuObject airGuObject;// = new AirGuObject();
    CurWeatherObject curWeatherObject;// = new CurWeatherObject();
    AirWeatherObject airWeatherObject;
    String weatherType;
    String singleParsed, dataParsed = "", data = "";

    public Type(AirGuObject airGuObject, CurWeatherObject curWeatherObject) {
        this.airGuObject = airGuObject;
        this.curWeatherObject = curWeatherObject;
    }

    public Type(AirWeatherObject airWeatherObject) {
        this.airWeatherObject = airWeatherObject;
    }

    public String WeatherType() {
        try {
            Double pm10Value = Double.parseDouble(airWeatherObject.getPm10Value());
            if (80 < pm10Value  && pm10Value <= 600) {
                weatherType = "type_0";
            } else {
                switch (airWeatherObject.getIcon()) {
                    case "snow":
                    case "Snow":
                        if (airWeatherObject.getTemperature() < 5) weatherType = "type_1";
                        break;
                    case "rain":
                        if (5 <= airWeatherObject.getTemperature() && airWeatherObject.getTemperature() < 15)
                            weatherType = "type_2";
                        else if (15 <= airWeatherObject.getTemperature() && airWeatherObject.getTemperature() < 27)
                            weatherType = "type_3";
                        else if (27 <= airWeatherObject.getTemperature()) weatherType = "type_4";
                        break;
                    case "clear":
                    case "Clear":
                    case "clear-night":
                    case "clear-day":
                        if (airWeatherObject.getTemperature() < 5) weatherType = "type_11";
                        else if (5 <= airWeatherObject.getTemperature() && airWeatherObject.getTemperature() < 15)
                            weatherType = "type_12";

                        if (90 <= airWeatherObject.getHumidity()) { //습함
                            if (15 <= airWeatherObject.getTemperature() && airWeatherObject.getTemperature() < 27)
                                weatherType = "type_13";
                            else if (27 <= airWeatherObject.getTemperature())
                                weatherType = "type_14";
                        } else if (0 <= airWeatherObject.getHumidity() && airWeatherObject.getHumidity() < 90) { //안습함
                            if (15 <= airWeatherObject.getTemperature() && airWeatherObject.getTemperature() < 27) {
                                weatherType = "type_15";
                            } else if (27 <= airWeatherObject.getTemperature()) {
                                weatherType = "type_16";
                            }
                        }
                        break;
                    default: //cloudy, mostly-cloudy, wind...
                        if (airWeatherObject.getTemperature() < 5) weatherType = "type_5";
                        else if (5 <= airWeatherObject.getTemperature() && airWeatherObject.getTemperature() < 15)
                            weatherType = "type_6";

                        if (90 <= airWeatherObject.getHumidity()) { //습함
                            if (15 <= airWeatherObject.getTemperature() && airWeatherObject.getTemperature() < 27)
                                weatherType = "type_7";
                            else if (27 <= airWeatherObject.getTemperature())
                                weatherType = "type_8";
                        } else if (0 <= airWeatherObject.getHumidity() && airWeatherObject.getHumidity() < 90) { //안습함
                            if (15 <= airWeatherObject.getTemperature() && airWeatherObject.getTemperature() < 27)
                                weatherType = "type_9";
                            else if (27 <= airWeatherObject.getTemperature())
                                weatherType = "type_10";
                        }
                        break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("?");
        }
        dataParsed = dataParsed + weatherType;
        return weatherType;
    }
}
