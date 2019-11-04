package com.example.futureweatherhourly;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FutureWeatherParsing extends AsyncTask<String, Void, String> {
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
            FutureWeather futureWeather = gson.fromJson(response.body().string(), FutureWeather.class);
            ArrayList<Datum> datums = futureWeather.daily.data;
            Datum d = datums.get(0);

            FutureWeatherObject ob = new FutureWeatherObject(futureWeather.currently.time, futureWeather.currently.icon, futureWeather.currently.temperature, futureWeather.currently.humidity,
                                                                                d.time, d.icon, d.humidity, d.apparentTemperatureMax, d.apparentTemperatureMin);

            parsingResult = "hourly time : " + ob.getCurrentlyTime() + "\n" +
                    "hourly weather summary : " + ob.getCurrentlyIcon() + "\n" +
                    "hourly temperature : " + ob.getCurrentlyTemperature() + "\n" +
                    "hourly humidity : " + ob.getCurrentlyHumidity() + "\n";
//                  ArrayList<Datum> datums = futureWeather.daily.data;
//                  Datum d = datums.get(0);
            parsingResult = parsingResult + "daily time : " + ob.getDailyTime()+ "\n" +
                    "daily weather summary : " + ob.getDailyIcon() + "\n" +
                    "daily apparentTemperatureMax : " + ob.getDailyApparentTemperatureMax() + "\n" +
                    "daily apparentTemperatureMin : " + ob.getDailyApparentTemperatureMIn() + "\n" +
                    "daily humidity : " + ob.getDailyHumidity() + "\n";


//            parsingResult = "hourly time : " + futureWeather.currently.time + "\n" +
//                    "hourly weather summary : " + futureWeather.currently.icon + "\n" +
//                    "hourly temperature : " + futureWeather.currently.temperature + "\n" +
//                    "hourly humidity : " + futureWeather.currently.humidity + "\n";
////            ArrayList<Datum> datums = futureWeather.daily.data;
////            Datum d = datums.get(0);
//            parsingResult = parsingResult + "daily time : " + d.time + "\n" +
//                    "daily weather summary : " + d.icon + "\n" +
//                    "daily apparentTemperatureMax : " + d.apparentTemperatureMax + "\n" +
//                    "daily apparentTemperatureMin : " + d.apparentTemperatureMin + "\n" +
//                    "daily humidity : " + d.humidity + "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        MainActivity.data.setText(this.parsingResult);
    }
}
