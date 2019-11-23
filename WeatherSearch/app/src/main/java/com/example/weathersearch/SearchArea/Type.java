package com.example.weathersearch.SearchArea;

public class Type {
    SearchAirWeatherObject searchAirWeatherObject;
    String weatherType;
    Double pm10Value;  //pm10Value의 String 값을 double로 값을 바꿔줌
    double temperature;

    public Type(SearchAirWeatherObject searchAirWeatherObject){
        this.searchAirWeatherObject = searchAirWeatherObject;
    }

    public String WeatherType() {
        try {
            if(searchAirWeatherObject.airPm10Value == null)
                searchAirWeatherObject.airPm10Value = "0.0";
            pm10Value = Double.parseDouble(searchAirWeatherObject.airPm10Value);
            temperature = searchAirWeatherObject.getCurrentlyTemperature();

            if (80 < pm10Value && pm10Value <= 600) {
                weatherType = "type_0";
            }
            else {
                switch (searchAirWeatherObject.getCurrentlyIcon()) {
                    case "snow":
                    case "Snow":
                        if (temperature < 5)
                            weatherType = "type_1";
                        break;
                    case "rain":
                        if (5 <= temperature && temperature < 15)
                            weatherType = "type_2";
                        else if (15 <= temperature && temperature < 27)
                            weatherType = "type_3";
                        else if (27 <= temperature)
                            weatherType = "type_4";
                        break;
                    case "clear":
                    case "Clear":
                    case "clear-night":
                    case "clear-day":
                        if (temperature < 5)
                            weatherType = "type_11";
                        else if (5 <= temperature && temperature < 15)
                            weatherType = "type_12";

                        else if (90 <= searchAirWeatherObject.getCurrentlyHumidity())
                        { //습함
                            if (15 <= temperature && temperature < 27)
                                weatherType = "type_13";
                            else if (27 <= temperature)
                                weatherType = "type_14";
                        }
                        else if (0 <= searchAirWeatherObject.getCurrentlyHumidity() && searchAirWeatherObject.getCurrentlyHumidity() < 90)
                        { //안습함
                            if (15 <= temperature && temperature < 27) {
                                weatherType = "type_15";
                            }
                            else if (27 <= temperature) {
                                weatherType = "type_16";
                            }
                        }
                        break;
                    default: //cloudy, mostly-cloudy, wind...
                        if (temperature < 5)
                            weatherType = "type_5";
                        else if (5 <= temperature && temperature < 15)
                            weatherType = "type_6";

                        else if (90 <= searchAirWeatherObject.getCurrentlyHumidity())
                        { //습함
                            if (15 <= temperature && temperature < 27)
                                weatherType = "type_7";
                            else if (27 <= temperature)
                                weatherType = "type_8";
                        }
                        else if (0 <= searchAirWeatherObject.getCurrentlyHumidity() && searchAirWeatherObject.getCurrentlyHumidity() < 90)
                        { //안습함
                            if (15 <= temperature && temperature < 27)
                                weatherType = "type_9";
                            else if (27 <= temperature)
                                weatherType = "type_10";
                        }
                        break;

                }
            }
        } catch (Exception e) {
            System.out.println("?");
        } return weatherType;
    }
}
