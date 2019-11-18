package com.e.ango.SurveyConnection;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import static com.e.ango.Login.RegisterTask.loginResponse;

//유저 선호도 점수 전송

public class SurveyUserPreferenceScoreTask extends AsyncTask<Void, Void, Boolean> {

    String url = "http://172.16.10.37"; //서버 (와이파이 바뀔 시 변경)

    private String URL_ADDRESS = url + ":8080/ango/Dispacher";  //주소 변경
    String request_msg = "InsertUserPreference";
    String token = loginResponse.token;
    String weather_type;
    ArrayList<Prefer_List> prefer_lists;
    String str;
    Boolean flag;

    public SurveyUserPreferenceScoreTask(SurveyUserPreferenceScoreDTO surveyUserPreferenceScoreDTO) {
        this.weather_type = surveyUserPreferenceScoreDTO.weather_type;
        this.prefer_lists = surveyUserPreferenceScoreDTO.prefer_list;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            URL Url = new URL(URL_ADDRESS); // URL화 한다.
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); // get방식 통신


            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            SurveyUserPreferenceScoreDTO surveyUserPreferenceScoreDTO = new SurveyUserPreferenceScoreDTO(request_msg, token, weather_type
                                    ,prefer_lists); /////////////////////////////////////////////////// token 처리도 해야함
            Gson gson = new Gson();

            osw.write(gson.toJson(surveyUserPreferenceScoreDTO));
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                    System.out.println(str);
                }
                System.out.println(buffer);
                SurveyUserPreferenceScoreResponse surveyUserPreferenceScoreResponse = gson.fromJson(buffer.toString(), SurveyUserPreferenceScoreResponse.class);
                System.out.println(surveyUserPreferenceScoreResponse);
                System.out.println(surveyUserPreferenceScoreResponse.response_msg);

                //유저 선호도 점수 전송에 성공했을때 flag를 true로 바꾸고 전송
                if(surveyUserPreferenceScoreResponse.response_msg.equals("InsertUserPreference_success")) {
                    System.out.println(surveyUserPreferenceScoreResponse.response_msg);
                    flag = true;
                }
                else {//유저 선호도 점수 전송에 실패했을때 flag를 true로 바꾸고 전송
                    System.out.println(surveyUserPreferenceScoreResponse.response_msg);
                    flag = false;
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
        }

        return flag;
    }


        @Override
        protected void onPostExecute (Boolean aVoid){
            super.onPostExecute(aVoid);
        }


}

