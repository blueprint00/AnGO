package com.e.weather;

import android.os.AsyncTask;

import com.e.totalapi.Type;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//*
public class HttpAsyncTask extends AsyncTask<Void, Void, String> {
    OkHttpClient client = new OkHttpClient();

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    String mapX = "37.4391";
    String mapY = "126.9032";

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        String strUrl = "https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/" + mapX + "," + mapY + "?exclude=hourly,daily";

        try {
            Request requestW = new Request.Builder()
                    .url(strUrl)
                    .build();

            Response response = client.newCall(requestW).execute();

            Gson gson = new Gson();
            CurWeatherVO curWeatherVO = gson.fromJson(response.body().string(), CurWeatherVO.class);

            Currently c = curWeatherVO.currently;

            CurWeatherObject cob = new CurWeatherObject(c.time, c.summary, c.icon, c.temperature, c.humidity);

            singleParsed = "time : " + cob.getTime() + "\n"
                    + "summary : " + cob.getSummary() + "\n"
                    + "icon : " + cob.getIcon() + "\n"
                    + "temperature : " + cob.getTime() + "\n"
                    + "humidity : " + cob.getHumidity() + "\n\n";

            dataParsed = dataParsed + singleParsed + "\n";


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("w");
        }
        //return result;
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        com.e.totalapi.MainActivity.data.setText(this.dataParsed);

    }
}