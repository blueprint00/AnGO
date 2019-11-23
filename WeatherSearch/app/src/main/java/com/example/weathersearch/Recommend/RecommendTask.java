package com.example.weathersearch.Recommend;

import android.drm.DrmStore;
import android.os.AsyncTask;


import com.example.weathersearch.HttpAsyncTaskPlay;
import com.example.weathersearch.Play.PlayObject;
import com.example.weathersearch.Request.PreferDto;
import com.example.weathersearch.Request.RequestDto;
import com.example.weathersearch.Response.ResponseDto;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;



//서버
//"response_msg" : “RecommendCategory_fail”
//"category_list" :
//   "category_id"
//   "category_name"
//"total"

public class RecommendTask extends AsyncTask<Void, Void, ArrayList<PlayObject>> {

    //OkHttpClient client = new OkHttpClient();
    //public static String ip = "172.16.10.37"; //자신의 IP번호
    //http://172.30.1.19:8090/final_ango/Dispacher
    String ip = "172.30.1.24";
    String serverip = "http://" + ip + ":8090/final_ango/Dispacher2"; // 연결할 jsp주소
    //public static String token;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRlIjoxNTc0Mzk2MTMzNDQ5LCJzdWIiOiJ1c2VyX3Rva2VuIiwiYXVkIjoidXNlcklEXzEiLCJpc3MiOiJhbmdvX3NlcnZlciIsImV4cCI6MTU3NDQ4MjUzMzQ0OX0.iuUtw206uL5xi7bct8_Qg8HgOWdIMn_h1mkh1PCW_iY";

    String weather_type;
    ArrayList<PreferDto> preferDtos;
    RequestDto requestDto;
    ArrayList<PlayObject> userPreferencePlayObjects = new ArrayList<PlayObject>();
    ArrayList<PlayObject> originalPlayObjects;

    public RecommendTask(String weather_type, ArrayList<PlayObject> originalPlayObjects){//String request_msg, String weather_type, String token) {
        this.weather_type = weather_type;
        this.originalPlayObjects = originalPlayObjects;
    }

    @Override
    protected ArrayList<PlayObject> doInBackground(Void... voids) {
        try {
            String str;
            URL url = new URL(serverip);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // URL을 연결한 객체 생성.
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); // get방식 통신
            conn.setDoOutput(true); // 쓰기모드 지정
            conn.setDoInput(true); // 읽기모드 지정
            conn.setUseCaches(false); // 캐싱데이터를 받을지 안받을지
            conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());


            Gson gson = new Gson();
            requestDto = new RequestDto("RecommendCategory",weather_type,token);
            osw.write(gson.toJson(requestDto));
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }


                ResponseDto responseDto = gson.fromJson(buffer.toString(), ResponseDto.class);

                if (responseDto.getResponse_msg().equals("RecommendCategory_success")) {
                    preferDtos = responseDto.getPrefer_list();

                    for (int i = 0; i < originalPlayObjects.size(); i++) {
                        for (int j = 0; j < preferDtos.size(); j ++) {

                            if (originalPlayObjects.get(i).getCat2().equals(preferDtos.get(j).getCg_id()) ||
                                    originalPlayObjects.get(i).getCat3().equals(preferDtos.get(j).getCg_id())) {

                                userPreferencePlayObjects.add(originalPlayObjects.get(i));
                            }  else {
                                //잘 작동하는지 확인해보기 위한 것 //나중에 지우기
                                System.out.println(j);
                            }
                        }
                    }

                }

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("RECOMMEND NULL");
        }
        return userPreferencePlayObjects;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}