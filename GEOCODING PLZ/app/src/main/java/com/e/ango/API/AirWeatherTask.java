package com.e.ango.API;

import android.os.AsyncTask;

import com.e.ango.API.Air.AirDto;
import com.e.ango.API.Air.List;
import com.e.ango.API.Weather.CurWeatherDto;
import com.e.ango.API.Weather.Currently;
import com.e.ango.CurrentLocationActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;


public class AirWeatherTask extends AsyncTask<Void, Void, String> {
    OkHttpClient client = new OkHttpClient();

    String longitude; // 경도 127
    String latitude; // 위도 36.5

    private AirWeatherObject originalAirWeatherObject;
    private String weather_type;
    private String str_Humidity, str_Temperature, str_Weather, str_Air;

    private String fullCityName;
    double pm10Value;

    public AirWeatherTask(String latitude, String longitude, String Stringaddr) {
        this.latitude = latitude;
        this.longitude = longitude;
        fullCityName = Stringaddr;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String urlA = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?sidoName=%EC%84%9C%EC%9A%B8&searchCondition=DAILY&pageNo=1&numOfRows=25&ServiceKey=w%2BvIOXxlMW05eJcl2Fw894grerR3LUGL1LepRRDEjPN1ntgk2i2%2FV00sSzbn7QZAnF5iqz2WG%2BiDxWnf2tdy4A%3D%3D&_returnType=json";
        String urlW = "https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/" + String.valueOf(latitude) + "," + String.valueOf(longitude) + "?exclude=hourly,daily";


        String cityName1 = fullCityName.split(", ")[1]; //hwayangdong, Gwangjin-gu, Seoul
        String cityName2 = fullCityName.split(". ")[2]; //19, hwayangdong, Gwanjin-gu, Seoul
        String guName = "";

        try {
            Request requestA = new Request.Builder()
                    .url(urlA)
                    .build();
            Request requestW = new Request.Builder()
                    .url(urlW)
                    .build();

            okhttp3.Response responseA = client.newCall(requestA).execute();
            okhttp3.Response responseW = client.newCall(requestW).execute();

            Gson gson = new Gson();
            AirDto airDto = gson.fromJson(responseA.body().string(), AirDto.class);
            CurWeatherDto curWeatherDto = gson.fromJson(responseW.body().string(), CurWeatherDto.class);

            ArrayList<List> lists = airDto.list;
            Currently currently = curWeatherDto.currently;

            System.out.println("AIRWEATHER1 : " + cityName1);
            System.out.println("AIRWEATHER2 : " + cityName2);

            //주소가 영어로 되어 있으므로 districtEngName과 일치하는 주소 찾은 후 한국어로 바꿔주기
            for (int i = 0; i < CurrentLocationActivity.districtEngName.length; i++) {
                if (cityName1.equals(CurrentLocationActivity.districtEngName[i])) {
                    guName = CurrentLocationActivity.districtName[i];
                } else if(cityName2.equals(CurrentLocationActivity.districtEngName[i])){
                    guName = CurrentLocationActivity.districtName[i];
                }
            } System.out.println("AIRWEATHER KOR : " + guName);


            for (int i = 0; i < lists.size(); i++) {
                List list = lists.get(i);

                if(list.cityName.equals(guName)) {
                    currently.temperature = (currently.temperature - 32) / 1.8;
                    currently.humidity = currently.humidity * 100;

                    if(list.pm10Value == null || list.pm10Value == "0.0") pm10Value = 0;
                    else pm10Value = Double.parseDouble(list.pm10Value);

                    originalAirWeatherObject = new AirWeatherObject(
                            list.sidoName, guName, list.cityNameEng, list.dataTime, pm10Value, // air
                            currently.time, currently.summary, currently.icon, currently.temperature, currently.humidity // weather
                    );

                    originalAirWeatherObject.humidity = (int) (originalAirWeatherObject.humidity + 0.5); // 반올림
                    str_Humidity = Double.toString(originalAirWeatherObject.getHumidity());
                    originalAirWeatherObject.temperature = (int) (originalAirWeatherObject.temperature + 0.5); // 반올림
                    str_Temperature = Double.toString(originalAirWeatherObject.getTemperature());
                    str_Weather = originalAirWeatherObject.getIcon();
                    str_Air = Double.toString(originalAirWeatherObject.pm10Value);
                }

            }

            CurrentLocationWeatherType currentLocationWeatherType = new CurrentLocationWeatherType(originalAirWeatherObject);
            weather_type = currentLocationWeatherType.WeatherType();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("aw1");
        } catch (NumberFormatException e){
            e.printStackTrace();
            System.out.println("aw2");
        }
        return weather_type;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        CurrentLocationActivity.tv_Humidity.setText("습도 : " + str_Humidity);//Math.round(Double.parseDouble(this.str_Humidity) * 100)/100.0);
        CurrentLocationActivity.tv_Temperature.setText("온도 : " + str_Temperature);//Math.round(Double.parseDouble(this.str_Temperature) * 100) / 100.0);
        CurrentLocationActivity.tv_Weather.setText("날씨 : " + str_Weather);
        CurrentLocationActivity.tv_Air.setText("미세먼지 : " + str_Air);

    }
}
