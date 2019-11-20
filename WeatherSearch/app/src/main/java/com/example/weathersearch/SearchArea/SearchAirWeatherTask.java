package com.example.weathersearch.SearchArea;

import android.os.AsyncTask;


import com.example.weathersearch.SearchArea.AirAPI.AirGuVO;
import com.example.weathersearch.SearchArea.AirAPI.List;
import com.example.weathersearch.SearchArea.WeatherAPI.Datum;
import com.example.weathersearch.SearchArea.WeatherAPI.SearchWeatherJson;
import com.example.weathersearch.SearchAreaActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.weathersearch.SearchAreaActivity.date;
import static com.example.weathersearch.SearchAreaActivity.districtResult;
import static com.example.weathersearch.SearchAreaActivity.time;
import static com.example.weathersearch.SearchAreaActivity.xCoordinate;
import static com.example.weathersearch.SearchAreaActivity.yCoordinate;

public class SearchAirWeatherTask extends AsyncTask<Void, Void, String> {
    OkHttpClient client = new OkHttpClient();
    String parsingResult = null;
    String weatherType = "";

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        String urlA = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?sidoName=%EC%84%9C%EC%9A%B8&searchCondition=DAILY&pageNo=1&numOfRows=25&ServiceKey=w%2BvIOXxlMW05eJcl2Fw894grerR3LUGL1LepRRDEjPN1ntgk2i2%2FV00sSzbn7QZAnF5iqz2WG%2BiDxWnf2tdy4A%3D%3D&_returnType=json";
        String urlW ="https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/"
                + xCoordinate + "," + yCoordinate + ","
                + date + "T" + time + "?exclude=hourly,flags";
        try{
            Request requestA = new Request.Builder()
                    .url(urlA)
                    .build();
            Request requestW = new Request.Builder()
                    .url(urlW)
                    .build();

            Response responseA = client.newCall(requestA).execute();
            Response responseW = client.newCall(requestW).execute();
            Gson gson = new Gson();

            SearchWeatherJson searchWeatherJson = gson.fromJson(responseW.body().string(), SearchWeatherJson.class);
            ArrayList<Datum> datums = searchWeatherJson.daily.data;
            Datum d = datums.get(0);

            SearchAirWeatherObject searchAirWeatherObject = new SearchAirWeatherObject(
                    null, null, null, null, null,null,
                    searchWeatherJson.currently.time, searchWeatherJson.currently.icon, searchWeatherJson.currently.temperature, searchWeatherJson.currently.humidity,
                    d.time, d.icon, d.humidity, d.apparentTemperatureMax, d.apparentTemperatureMin
            );


            AirGuVO airGuVO= null;
            airGuVO = gson.fromJson(responseA.body().string(), AirGuVO.class);
            ArrayList<com.example.weathersearch.SearchArea.AirAPI.List> lists = airGuVO.list;

            for(int i = 0; i < lists.size(); i ++) {
                List l = lists.get(i);
                if(l.cityName.equals(districtResult)){
                    System.out.println(l.cityName);
                    searchAirWeatherObject.airCityName = l.cityName;
                    searchAirWeatherObject.aircityNameEng = l.cityNameEng;
                    searchAirWeatherObject.airPm25Value = l.pm25Value;
                    searchAirWeatherObject.airDataTime = l.dataTime;
                    searchAirWeatherObject.airPm10Value = l.pm10Value;
                    searchAirWeatherObject.airSidoName = l.sidoName;
                }
            }

            String currentlyTime = searchAirWeatherObject.unixTimeToCurrentlytime();
            String dailyTime = searchAirWeatherObject.unixTimeToDailytime();
            searchAirWeatherObject.faherenheitToCelcius();

            Type type = new Type(searchAirWeatherObject);
            weatherType = type.WeatherType();
            System.out.println(weatherType);

            parsingResult =  weatherType + "\n";
            parsingResult = parsingResult + "airweatherparsing" + "\n";
            parsingResult = parsingResult + searchAirWeatherObject.airCityName + "\n";
            parsingResult = parsingResult + "hourly time : " + currentlyTime + "\n" +
                    "hourly weather summary : " + searchAirWeatherObject.getCurrentlyIcon() + "\n" +
                    "hourly temperature : " + searchAirWeatherObject.getCurrentlyTemperature() + "\n" +
                    "hourly humidity : " + searchAirWeatherObject.getCurrentlyHumidity() + "\n";
//                  ArrayList<Datum> datums = searchWeatherJson.daily.data;
//                  Datum d = datums.get(0);
            parsingResult = parsingResult + "daily time : " + dailyTime + "\n" +
                    "daily weather summary : " + searchAirWeatherObject.getDailyIcon() + "\n" +
                    "daily apparentTemperatureMax : " + searchAirWeatherObject.getDailyApparentTemperatureMax() + "\n" +
                    "daily apparentTemperatureMin : " + searchAirWeatherObject.getDailyApparentTemperatureMin() + "\n" +
                    "daily humidity : " + searchAirWeatherObject.getDailyHumidity() + "\n";

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherType;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        SearchAreaActivity.textView_weatherPasredData.setText(this.parsingResult);
    }
}
