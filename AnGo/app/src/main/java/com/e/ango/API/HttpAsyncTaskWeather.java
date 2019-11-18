package com.e.ango.API;

import android.os.AsyncTask;
import android.widget.TextView;

import com.e.ango.API.Weather.CurWeatherDto;
import com.e.ango.API.Weather.CurWeatherObject;
import com.e.ango.API.Weather.Currently;
import com.e.ango.MainActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAsyncTaskWeather extends AsyncTask<Void, Void, Void> {
    OkHttpClient client = new OkHttpClient();
    String dataParsed = "";

    public static CurWeatherObject wob;


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/37.6,127?exclude=hourly,daily");

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            CurWeatherDto curWeatherDto = new CurWeatherDto();
            Gson gson = new Gson();
            curWeatherDto = gson.fromJson(response.body().string(), CurWeatherDto.class);

            Currently c = curWeatherDto.currently;

            c.temperature = (c.temperature - 32) * 1.8; // 화씨 -> 섭씨
            c.humidity = c.humidity * 100; // 습도 퍼센트
            wob = new CurWeatherObject(c.time, c.summary, c.icon, c.temperature, c.humidity);

            dataParsed = "time : " + wob.getTime() + "\nsummary : " + wob.getSummary() +
                    "\nicon : " + wob.getIcon() + "\ntemperature : " + wob.getTemperature() +
                    "\nhumidity : " + wob.getHumidity();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //MainActivity.tv_5.setText(this.dataParsed);
    }
}
