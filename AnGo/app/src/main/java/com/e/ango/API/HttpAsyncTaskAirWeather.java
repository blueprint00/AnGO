package com.e.ango.API;

import android.os.AsyncTask;
import android.widget.TextView;

import com.e.ango.API.Air.AirDto;
import com.e.ango.API.Air.List;
import com.e.ango.API.Weather.CurWeatherDto;
import com.e.ango.API.Weather.Currently;
import com.e.ango.MainActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAsyncTaskAirWeather extends AsyncTask<Void, Void, String> {
    OkHttpClient client = new OkHttpClient();
    String dataParsed = "";
    String cityname = "광진구";

    public static AirWeatherObject awob;
    public String weather_type;
    public String str_Humidity, str_Temperature,str_Weather;

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        String urlA = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?sidoName=%EC%84%9C%EC%9A%B8&searchCondition=DAILY&pageNo=1&numOfRows=25&ServiceKey=w%2BvIOXxlMW05eJcl2Fw894grerR3LUGL1LepRRDEjPN1ntgk2i2%2FV00sSzbn7QZAnF5iqz2WG%2BiDxWnf2tdy4A%3D%3D&_returnType=json";
        String urlW = "https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/37.6,127?exclude=hourly,daily";

        try {
            Request requestA = new Request.Builder()
                            .url(urlA)
                            .build();
            Request requestW = new Request.Builder()
                        .url(urlW)
                        .build();

            okhttp3.Response responseA = client.newCall(requestA).execute();
            okhttp3.Response responseW = client.newCall(requestW).execute();

            AirDto airDto = new AirDto();
            Gson gson = new Gson();
            airDto = gson.fromJson(responseA.body().string(), AirDto.class);
            CurWeatherDto curWeatherDto = gson.fromJson(responseW.body().string(), CurWeatherDto.class);

            ArrayList<List> lists = airDto.list;
            Currently c = curWeatherDto.currently;

            awob = new AirWeatherObject();
            double temp = 0;
            for(int i = 0; i < lists.size(); i ++){
                List l = lists.get(i);

                if(l.cityName.equals(cityname)){
                    c.temperature  = (c.temperature - 32) / 1.8;
                    c.humidity = c.humidity * 100;
                    double pm10Value = Double.parseDouble(l.pm10Value);

                    awob = new AirWeatherObject(
                            l.sidoName, l.cityName, l.cityNameEng, l.dataTime, pm10Value,
                            c.time, c.summary, c.icon, c.temperature, c.humidity
                    );
                    str_Humidity = Double.toString(awob.getHumidity());
                    awob.temperature = (int)(awob.temperature + 0.5);
                    str_Temperature = Double.toString(awob.getTemperature());
                    str_Weather = awob.getIcon();
                }
            }

            Type type = new Type(awob);
            weather_type = type.WeatherType();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return weather_type;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MainActivity.tv_Humidity.setText("습도 : " + this.str_Humidity);
        MainActivity.tv_Temperature.setText("온도 : " + this.str_Temperature);
        MainActivity.tv_Weather.setText("날씨 : " + this.str_Weather);

    }
}
