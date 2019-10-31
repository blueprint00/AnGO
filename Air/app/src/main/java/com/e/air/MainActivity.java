package com.e.air;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new HttpAsyncTask().execute("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?serviceKey=w%2BvIOXxlMW05eJcl2Fw894grerR3LUGL1LepRRDEjPN1ntgk2i2%2FV00sSzbn7QZAnF5iqz2WG%2BiDxWnf2tdy4A%3D%3D&numOfRows=10&pageNo=1&sidoName=%EC%84%9C%EC%9A%B8&searchCondition=DAILY&_returnType=json");


    }
    protected static class HttpAsyncTask extends AsyncTask<String, Void, String> {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            String url = strings[0];

            try{
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();

                Gson gson = new Gson();
                AirGuVO airGuVO = gson.fromJson(response.body().string(), AirGuVO.class);
                ArrayList<AirGuObject> aob = new ArrayList<>();// = new AirGuObject();


                ArrayList<List> lists = airGuVO.list;
                for(int i = 0; i < lists.size(); i ++){
                    List l = lists.get(i);

                    aob.add(
                            new AirGuObject(l.cityName, l.coValue, l.dataTime, l.no2Value, l.o3Value,
                                    l.pm10Value, l.pm25Value, l.sidoName, l.so2Value)
                    );
                }

                for(int i = 0; i < aob.size(); i ++){
                    System.out.println("cityname : " + aob.get(i).cityName +"\n" +
                                        "coValue : " + aob.get(i).coValue +"\n" +
                                        "dataTime : " + aob.get(i).dataTime +"\n" +
                                        "no2Value : " + aob.get(i).no2Value +"\n" +
                                        "o3Value : " + aob.get(i).o3Value +"\n" +
                                        "pm10Value : " + aob.get(i).pm10Value +"\n" +
                                        "pm25Value : " + aob.get(i).pm25Value +"\n" +
                                        "sidoname: " + aob.get(i).sidoName +"\n" +
                                        "so2Value : " + aob.get(i).so2Value +"\n\n"
                    );
                }

            } catch(Exception e){

            }



            return result;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

    }

}

