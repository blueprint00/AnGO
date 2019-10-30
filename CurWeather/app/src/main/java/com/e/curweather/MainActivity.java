package com.e.curweather;

import com.e.curweather.LocWeatherVO;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.e.curweather.LocWeatherObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = com.e.curweather.MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        new com.e.curweather.MainActivity.HttpAsyncTask().execute("https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/37.6,127");
    }

    private static class HttpAsyncTask extends AsyncTask<String, Void, String> {
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
                LocWeatherVO locWeatherVO = gson.fromJson(response.body().string(), LocWeatherVO.class);

                LocWeatherObject ob;// = new ArrayList<>();

                Currently c = locWeatherVO.currently;

                ob = new LocWeatherObject(c.time, c.summary, c.icon, c.temperature, c.humidity);

                System.out.println("time : " + ob.getTime() + "\n"
                        + "summary : " + ob.getSummary() + "\n"
                        + "icon : " + ob.getIcon() + "\n"
                        + "temperature : " + ob.getTime() + "\n"
                        + "humidity : " + ob.getHumidity() + "\n\n"
                );
                /*for(int i = 0; i < lists.size(); i ++) {
                    List l = lists.get(i);
                    parsingResult = "temp : " + l.main.temp + "\n" +
                            "tempMin : " + l.main.tempMin + "\n" +
                            "tempMax : " + l.main.tempMax + "\n" +
                            "humidity : " + l.main.humidity + "\n" +
                            //"weatherMain : " + weathers.main + "\n" +
                            "data Time : " + l.dtTxt + "\n";

                    ArrayList<Weather> weathers = l.weather;
                    //for(int j = 0; j < weathers.size(); j ++){
                    Weather w = weathers.get(0);

                    ob = new weatherObject(l.main.temp, l.main.tempMin, l.main.tempMax, l.main.humidity, l.dtTxt, w.main);

                    System.out.println("temp : " + ob.getTemp() +
                            "\ntemp_min : " + ob.getTemp_min() +
                            "\ntemp_max : " + ob.getTemp_max() +
                            "\nhumidity : " + ob.getHumidity() +
                            "\ndt_txt : " + ob.getDt_txt() +
                            "\nmain : " + ob.getwMain() + "\n\n");

                    //Log.d("Json Parsing", parsingResult);
                }*/
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
            if (s != null) {
                Log.d(TAG, s);
            }

        }
    }
}
