package com.e.ango.API;

public class CurrentLocationWeatherType {

    AirWeatherObject airWeatherObject;
    String weatherType;


    public CurrentLocationWeatherType(AirWeatherObject airWeatherObject) {
        this.airWeatherObject = airWeatherObject;
    }

    //날씨 타입 계산
    public String WeatherType() {
        try {
            if (80 < airWeatherObject.getPm10Value() && airWeatherObject.getPm10Value() <= 600) {
                weatherType = "type_0";
                //iv_weatherIcon.setImageResource(R.drawable.medical_mask);
                //currentLocationActivity.setDrawable(R.drawable.medical_mask);
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
        } catch(NullPointerException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return weatherType;
    }
}
