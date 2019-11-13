package com.e.login;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTask extends AsyncTask<Void, Void, Boolean> {

    public static String ip = "172.16.15.152"; //자신의 IP번호
    String serverip = "http://" + ip + ":8080/ango/Dispacher"; // 연결할 jsp주소
    Boolean flag = true; // 선호도 조사 했는지 안했는지

    LoginActivity loginActivity;

    User user;
    LoginTask(String userId, String userPass, String request_msg) {
        user = new User(userId, userPass, request_msg);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            String str;
            URL url = new URL(serverip);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            Gson gson = new Gson();
            osw.write(gson.toJson(user));
            osw.flush();
            System.out.println(gson.toJson(user));

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                ServerResponse serverResponse = gson.fromJson(buffer.toString(), ServerResponse.class);
                //System.out.println(serverResponse.response_msg + " / " + serverResponse.token + " / " + serverResponse.availability);

                //로그인 실패 시
                if(serverResponse.response_msg.equals("LoginAccount_fail")) { return null; }

                //설문조사 안 했으면
                if (serverResponse.availability == 0) {
                    flag = false;
                }

            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    protected void onProgressUpdate(Boolean... values){
        Boolean flag = values[0];

    }
    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
    }
}