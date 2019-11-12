package com.example.weatherserach;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.weatherserach.MainActivity.date;
import static com.example.weatherserach.MainActivity.districtResult;

public class SearchWeatherParsing extends AsyncTask<String, Void, String> {
    OkHttpClient client = new OkHttpClient();
    String parsingResult = null;
    String data = "";

    @Override
    protected String doInBackground(String... params) {
        String result = null;
        String strUrl =params[0];

        try{
            Request request = new Request.Builder()
                    .url(strUrl)
                    .build();

            Response response = client.newCall(request).execute();

            Gson gson = new Gson();
            SearchWeatherJson searchWeatherJson = gson.fromJson(response.body().string(), SearchWeatherJson.class);
            ArrayList<Datum> datums = searchWeatherJson.daily.data;
            Datum d = datums.get(0);

            SearchWeatherObject ob = new SearchWeatherObject(searchWeatherJson.currently.time, searchWeatherJson.currently.icon, searchWeatherJson.currently.temperature, searchWeatherJson.currently.humidity,
                    d.time, d.icon, d.humidity, d.apparentTemperatureMax, d.apparentTemperatureMin);

            SearchAirWeatherObject searchAirWeatherObject = new SearchAirWeatherObject(
                    districtResult, null, 0, date, 0,"서울",
                    ob.getCurrentlyTime(),ob.getCurrentlyIcon(),ob.getCurrentlyTemperature(),ob.getCurrentlyHumidity(),
                    ob.getDailyTime(),ob.getDailyIcon(),ob.getDailyHumidity(),ob.getDailyApparentTemperatureMax(), ob.getDailyApparentTemperatureMin()
            );

            String currentlyTime = searchAirWeatherObject.unixTimeToCurrentlytime();
            String dailyTime = searchAirWeatherObject.unixTimeToDailytime();
            searchAirWeatherObject.faherenheitToCelcius();

            Type type = new Type(searchAirWeatherObject);
            System.out.println(type.WeatherType());
            parsingResult = type.WeatherType() + "\n";

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

        MainActivity.textView_weatherPasredData.setText(this.parsingResult);
    }
}
