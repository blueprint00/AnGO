package com.e.ango.API;

import android.os.AsyncTask;

import com.e.ango.API.Air.AirDto;
import com.e.ango.API.Air.List;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAsyncTaskAir extends AsyncTask<Void, Void, String> {
    OkHttpClient client = new OkHttpClient();
    String singleParsed = "", dataParsed = "";
    String data ="";
    String cityname = "광진구";

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?sidoName=%EC%84%9C%EC%9A%B8&searchCondition=DAILY&pageNo=1&numOfRows=25&ServiceKey=w%2BvIOXxlMW05eJcl2Fw894grerR3LUGL1LepRRDEjPN1ntgk2i2%2FV00sSzbn7QZAnF5iqz2WG%2BiDxWnf2tdy4A%3D%3D&_returnType=json";

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            okhttp3.Response response = client.newCall(request).execute();

            Gson gson = new Gson();
            AirDto airDto = gson.fromJson(response.body().string(), AirDto.class);

            ArrayList<List> lists = airDto.list;

            AirWeatherObject airWeatherObject = new AirWeatherObject();

            for(int i = 0; i < lists.size(); i ++){
                List l = lists.get(i);

                if(l.cityName.equals(cityname)){
                    System.out.println(l.cityName);
                    airWeatherObject = new AirWeatherObject(
                            l.sidoName, l.cityName, l.cityNameEng, l.dataTime, l.pm10Value
                    );
                }
                dataParsed = "sidoname : " + airWeatherObject.getSidoName() + "\ncityName : " + airWeatherObject.getCityName() +
                        "\ncityNameEng : " + airWeatherObject.getCityNameEng() + "\ndataTime : " + airWeatherObject.getDataTime() +
                        "\npm10Value : " + airWeatherObject.getPm10Value();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        APIActivity.data.setText(this.dataParsed);

    }
}

