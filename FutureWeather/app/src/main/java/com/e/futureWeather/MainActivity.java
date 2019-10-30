package com.e.futureWeather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = com.e.futureWeather.MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        new HttpAsyncTask().execute("https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/37.6,127,2019-10-30T20:00:00?exclude=currently,flags");
    }

    private static class HttpAsyncTask extends AsyncTask<String, Void, String > {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            String strUrl = params[0];

            try {
                Request request = new Request.Builder()
                        .url(strUrl)
                        .build();

                Response response = client.newCall(request).execute();

                Gson gson = new Gson();
                FutureWeatherVO futureWeatherVO = gson.fromJson(response.body().string(), FutureWeatherVO.class);
                ArrayList<FutureWeatherObject> fob = new ArrayList<>();
                String parsingResult = null;

                //Hourly hs = futureWeatherVO.hourly;
                //Daily ds = futureWeatherVO.daily;

                ArrayList<HourlyData> hds = futureWeatherVO.hourly.data;
                ArrayList<DailyData> dds = futureWeatherVO.daily.data;
                //System.out.println(hds.toString());

                for (int i = 0; i < hds.size(); i++) {
                    HourlyData h = hds.get(i);
                    parsingResult = "time : " + h.time + "\n" +
                            "summary : " + h.summary +
                            "icon : " + h.icon + "\n" +
                            "temperature : " + h.temperature + "\n" +
                            "humidity : " + h.humidity + "\n";

                    //Log.d("Json Parsing", parsingResult);

                    fob.add(new FutureWeatherObject(h.time, h.summary, h.icon, h.temperature, h.humidity));
                }


                for(int i = 0; i < dds.size(); i ++){
                    DailyData d = dds.get(i);

                    fob.add(new FutureWeatherObject(d.time, d.summary, d.icon, d.temperatureMax, d.temperatureMin));
                }

                for(int i = 0; i < hds.size() + dds.size(); i ++) {
                    System.out.println("hourly : {" +
                            "\ntime : " + fob.get(i).getTimeH() +
                            "\nsummary : " + fob.get(i).getSummaryH() +
                            "\nicon : " + fob.get(i).getIconH() +
                            "\ntemperature : " + fob.get(i).getTemperatureH() +
                            "\nhumidity : " + fob.get(i).getHumidityH() + "\n},\n");
                }

                 System.out.println("daily : {" +
                        "\ntime : " + fob.get(0).getTimeD() +
                        "\nsummary : " + fob.get(0).getSummaryD() +
                        "\nicon : " + fob.get(0).getIconD() +
                        "\ntemperatureMax : " + fob.get(0).getTemperatureMaxD() +
                        "\ntemperatureMin : " + fob.get(0).getTemperatureMinD() + "}\n\n");

                //Log.d("Json Parsing", parsingResult);
                //Log.d(TAG, "doInBackground: " + weathers.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null) {
                Log.d(TAG, s);
            }

        }
    }
}