package com.example.weathersearch.SearchArea;

import android.os.AsyncTask;

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

public class SearchWeatherTask extends AsyncTask<Void, Void, String> {
    OkHttpClient client = new OkHttpClient();
    String parsingResult = null;
    String data = "";

    @Override
    protected String doInBackground(Void... params) {
        String result = null;
        String strUrl ="https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/"
                          + xCoordinate + "," + yCoordinate + ","
                           + date + "T" + time + "?exclude=hourly,flags";

        try{
            Request request = new Request.Builder()
                    .url(strUrl)
                    .build();

            Response response = client.newCall(request).execute();

            Gson gson = new Gson();
            SearchWeatherJson searchWeatherJson = gson.fromJson(response.body().string(), SearchWeatherJson.class);
            ArrayList<Datum> datums = searchWeatherJson.daily.data;
            Datum d = datums.get(0);

            SearchAirWeatherObject searchAirWeatherObject = new SearchAirWeatherObject(
                    districtResult, null, null, date, null,"서울",
                    searchWeatherJson.currently.time, searchWeatherJson.currently.icon, searchWeatherJson.currently.temperature, searchWeatherJson.currently.humidity,
                    d.time, d.icon, d.humidity, d.apparentTemperatureMax, d.apparentTemperatureMin
            );

            String currentlyTime = searchAirWeatherObject.unixTimeToCurrentlytime();
            String dailyTime = searchAirWeatherObject.unixTimeToDailytime();
            searchAirWeatherObject.faherenheitToCelcius();

            Type type = new Type(searchAirWeatherObject);
            System.out.println(type.WeatherType());

            parsingResult = "SearchWeatherTask\n";
            parsingResult = parsingResult + type.WeatherType() + "\n";
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
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        SearchAreaActivity.textView_weatherPasredData.setText(this.parsingResult);
    }
}
