package com.example.weatherserach;

public class Type {

    AirObject airObject;// = new AirGuObject();
    SearchWeatherObject searchWeatherObject;// = new CurWeatherObject();
    SearchAirWeatherObject searchAirWeatherObject;
    String weatherType;
    //String singleParsed, dataParsed;

    public Type(AirObject airObject, SearchWeatherObject searchWeatherObject){
        this.airObject = airObject;
        this.searchWeatherObject = searchWeatherObject;
    }
    public Type(SearchAirWeatherObject searchAirWeatherObject){
        this.searchAirWeatherObject = searchAirWeatherObject;
    }

    public String WeatherType() {
        try {
            double temperature = searchAirWeatherObject.getCurrentlyTemperature();
            //System.out.println(airWeatherObject.getTemperature() + " / " + temperature);
            if (80 < searchAirWeatherObject.getAirPm10Value() && searchAirWeatherObject.getAirPm10Value() <= 600) {
                weatherType = "type_0";
            } else {
                switch (searchAirWeatherObject.getCurrentlyIcon()) {
                    case "snow":
                    case "Snow":
                        if (temperature < 5) weatherType = "type_1";
                        break;
                    case "rain":
                        if (5 <= temperature && temperature < 15)
                            weatherType = "type_2";
                        else if (15 <= temperature && temperature < 27)
                            weatherType = "type_3";
                        else if (27 <= temperature) weatherType = "type_4";
                        break;
                    case "clear":
                    case "Clear":
                    case "clear-night":
                    case "clear-day":
                        if (temperature < 5) weatherType = "type_11";
                        else if (5 <= temperature && temperature < 15)
                            weatherType = "type_12";

                        if (90 <= searchAirWeatherObject.getCurrentlyHumidity()) { //습함
                            if (15 <= temperature && temperature < 27)
                                weatherType = "type_13";
                            else if (27 <= temperature)
                                weatherType = "type_14";
                        } else if (0 <= searchAirWeatherObject.getCurrentlyHumidity() && searchAirWeatherObject.getCurrentlyHumidity() < 90) { //안습함
                            System.out.println(temperature);
                            if (15 <= temperature && temperature < 27) {
                                weatherType = "type_15";
                            } else if (27 <= temperature) {
                                weatherType = "type_16";
                            }
                        }
                        break;
                    default: //cloudy, mostly-cloudy, wind...
                        if (temperature < 5) weatherType = "type_5";
                        else if (5 <= temperature && temperature < 15)
                            weatherType = "type_6";

                        if (90 <= searchAirWeatherObject.getCurrentlyHumidity()) { //습함
                            if (15 <= temperature && temperature < 27)
                                weatherType = "type_7";
                            else if (27 <= temperature)
                                weatherType = "type_8";
                        } else if (0 <= searchAirWeatherObject.getCurrentlyHumidity() && searchAirWeatherObject.getCurrentlyHumidity() < 90) { //안습함
                            if (15 <= temperature && temperature < 27)
                                weatherType = "type_9";
                            else if (27 <= temperature)
                                weatherType = "type_10";
                        }
                        break;

                }
            }
        }catch (Exception e) {
            System.out.println("?");
        } return weatherType;
    }
}
