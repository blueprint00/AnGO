package com.e.airweather.AirWeather;

import com.e.airweather.MainActivity;

public class Type {

    AirGuObject airGuObject;// = new AirGuObject();
    CurWeatherObject curWeatherObject;// = new CurWeatherObject();
    AirWeatherObject airWeatherObject;
    String weatherType;
    String singleParsed, dataParsed;

    public Type(AirGuObject airGuObject, CurWeatherObject curWeatherObject){
        this.airGuObject = airGuObject;
        this.curWeatherObject = curWeatherObject;
    }
    public Type(AirWeatherObject airWeatherObject){
        this.airWeatherObject = airWeatherObject;
    }

    public String WeatherType() {
        try {
            System.out.println("1");
            System.out.println(airWeatherObject.getCityName());
            if (80 < this.airWeatherObject.getPm10Value() && this.airWeatherObject.getPm10Value() <= 600) {
                System.out.println(airWeatherObject.getPm10Value());
                this.weatherType = "type_0";
            }
            else {
                System.out.println("2");
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
                            if (15 <= airWeatherObject.getTemperature() && airWeatherObject.getTemperature() < 27)
                                weatherType = "type_15";
                            else if (27 <= airWeatherObject.getTemperature())
                                weatherType = "type_16";
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
                com.e.airweather.MainActivity.data.setText(this.weatherType);
            }
        } catch (Exception e) {
            System.out.println("?");
        } return weatherType;
    }
}
