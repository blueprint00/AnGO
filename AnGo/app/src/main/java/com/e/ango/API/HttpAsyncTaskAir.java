package com.e.ango.API;

import android.os.AsyncTask;

import com.e.ango.API.Air.AirDto;
import com.e.ango.API.Air.List;
import com.e.ango.MainActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAsyncTaskAir extends AsyncTask<Void, Void, String> {
    OkHttpClient client = new OkHttpClient();
    public static String str_Air = "";
    String cityname = "광진구";

    Double pm10Value;
    public static AirWeatherObject aob = new AirWeatherObject();

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

            for(int i = 0; i < lists.size(); i ++){
                List l = lists.get(i);

                if(l.cityName.equals(cityname)){
                    System.out.println(l.cityName);
                    pm10Value = Double.parseDouble(l.pm10Value);
                    aob = new AirWeatherObject(
                            l.sidoName, l.cityName, l.cityNameEng, l.dataTime, pm10Value
                    );
                    if(80 <= pm10Value && pm10Value < 600) {
                        str_Air = "나쁨";
                    } else { str_Air = "좋음"; }
                }
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
        MainActivity.tv_Air.setText("미세먼지 : " + this.str_Air);

    }
}

