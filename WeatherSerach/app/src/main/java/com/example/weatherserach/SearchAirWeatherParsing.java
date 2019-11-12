package com.example.weatherserach;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.weatherserach.MainActivity.districtResult;

public class SearchAirWeatherParsing extends AsyncTask<String, Void, String> {
    OkHttpClient client = new OkHttpClient();
    String parsingResult = null;
    String data = "";
    @Override
    protected String doInBackground(String... params) {
        String result = null;
        String strUrlWeather =params[0];
        String strUrlAir =params[1];
        try{
            Request requestWeather = new Request.Builder()
                                        .url(strUrlWeather)
                                        .build();
            Request requestAir = new Request.Builder()
                    .url(strUrlAir)
                    .build();

            Response responseWeather = client.newCall(requestWeather).execute();
            Response responseAir = client.newCall(requestAir).execute();

            Gson gson = new Gson();
            SearchWeatherJson searchWeatherJson = gson.fromJson(responseWeather.body().string(), SearchWeatherJson.class);
            ArrayList<Datum> datums = searchWeatherJson.daily.data;
            Datum d = datums.get(0);

            SearchWeatherObject ob = new SearchWeatherObject(searchWeatherJson.currently.time, searchWeatherJson.currently.icon, searchWeatherJson.currently.temperature, searchWeatherJson.currently.humidity,
                                                                                d.time, d.icon, d.humidity, d.apparentTemperatureMax, d.apparentTemperatureMin);

            SearchAirJson searchAirJson = gson.fromJson(responseAir.body().string(), SearchAirJson.class);
            ArrayList<AirList> lists = searchAirJson.list;

            SearchAirWeatherObject searchAirWeatherObject = new SearchAirWeatherObject();

            for(int i = 0; i < lists.size(); i ++){
                AirList l = lists.get(i);

                if(l.cityName.equals(districtResult)){
                    System.out.println(l.cityName);
                    searchAirWeatherObject = new SearchAirWeatherObject(
                            l.cityName, l.cityNameEng, l.pm25Value, l.dataTime, l.pm10Value,l.sidoName,
                            ob.getCurrentlyTime(),ob.getCurrentlyIcon(),ob.getCurrentlyTemperature(),ob.getCurrentlyHumidity(),
                            ob.getDailyTime(),ob.getDailyIcon(),ob.getDailyHumidity(),ob.getDailyApparentTemperatureMax(), ob.getDailyApparentTemperatureMin()
                    );
                }
            }

            String currentlyTime = searchAirWeatherObject.unixTimeToCurrentlytime();
            String dailyTime = searchAirWeatherObject.unixTimeToDailytime();
            searchAirWeatherObject.faherenheitToCelcius();

            Type type = new Type(searchAirWeatherObject);
            System.out.println(type.WeatherType());
            parsingResult =  type.WeatherType() + "\n";

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
