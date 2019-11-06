package com.e.air;
import com.e.*;

import android.os.AsyncTask;
import android.util.Log;

import com.e.totalapi.Type;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = com.e.totalapi.MainActivity.class.getSimpleName();
    OkHttpClient client = new OkHttpClient();
    String singleParsed = "", dataParsed = "";

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?sidoName=%EC%84%9C%EC%9A%B8&searchCondition=DAILY&pageNo=1&numOfRows=10&ServiceKey=w%2BvIOXxlMW05eJcl2Fw894grerR3LUGL1LepRRDEjPN1ntgk2i2%2FV00sSzbn7QZAnF5iqz2WG%2BiDxWnf2tdy4A%3D%3D&_returnType=json";
        String str = "광진구";
        try{
            Request requestA = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(requestA).execute();

            Gson gson = new Gson();
            AirGuVO airGuVO = gson.fromJson(response.body().string(), AirGuVO.class);

            ArrayList<List> lists = airGuVO.list;
            /*ArrayList<AirGuObject> aob = new ArrayList<>();// = new AirGuObject();
            for(int i = 0; i < lists.size(); i ++){
                List l = lists.get(i);
                aob.add(new AirGuObject(l.cityName, l.sidoName, l.dataTime, l.pm10Value, l.pm25Value));
            }*/

            AirGuObject aob = new AirGuObject();
            for(int i = 0; i < lists.size(); i ++){
                List l = lists.get(i);

                if(l.cityName.equals(str)) {
                     aob = new AirGuObject(l.cityName, l.sidoName, l.dataTime, Long.parseLong(l.pm10Value), Long.parseLong(l.pm25Value));
                }
            }

            System.out.println("cityname : " + aob.getCityName() +"\n" +
                    "sidoname: " + aob.getSidoName() +"\n" +
                    "dataTime : " + aob.getDataTime() +"\n" +
                    "pm10Value : " + aob.getPm10Value() +"\n" +
                    "pm25Value : " + aob.getPm25Value() +"\n" +
                    "10condition : " + aob.getPm10condition() +
                    "\n25condition : " + aob.getPm25condition());


            aob.setPm10condition("좋음");
            aob.setPm25condition("좋음");
            if(80 < aob.getPm10Value() && aob.getPm10Value() <= 600) {
                    aob.setPm10condition("나쁨");
            }
            if(35 < aob.getPm25Value() && aob.getPm25Value() <= 500) {
                    aob.setPm25condition("나쁨");
            }

            singleParsed = "cityname : " + aob.getCityName() +"\n" +
                    "sidoname: " + aob.getSidoName() +"\n" +
                    "dataTime : " + aob.getDataTime() +"\n" +
                    "pm10Value : " + aob.getPm10Value() +"\n" +
                    "pm25Value : " + aob.getPm25Value() +"\n" +
                    "10condition : " + aob.getPm10condition() +
                    "\n25condition : " + aob.getPm25condition();

            dataParsed = dataParsed + singleParsed;




        } catch(Exception e){
            System.out.println("?");
        }
        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        com.e.totalapi.MainActivity.data.setText(this.dataParsed);
        if (s != null) {
            Log.d(TAG, s);
        }

    }
}