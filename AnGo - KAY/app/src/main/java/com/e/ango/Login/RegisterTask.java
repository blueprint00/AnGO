package com.e.ango.Login;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RegisterTask extends AsyncTask<Void, Void, Boolean> {

    public static String ip = "172.16.15.152"; //자신의 IP번호
    String serverip = "http://" + ip + ":8080/ango/Dispacher"; // 연결할 jsp주소
    String response_msg;
    Boolean flag = true;

    UserDto userDto = new UserDto();
    //Join
    public RegisterTask(String id, String pass, String name, String request_msg){
        userDto = new UserDto(id, pass, name, request_msg);
    }
    //Check
    public RegisterTask(String id, String request_msg){
        userDto = new UserDto(id, request_msg);
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
            osw.write(gson.toJson(userDto));
            osw.flush();
            System.out.println(gson.toJson(userDto));

            if(conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                ServerResponse serverResponse = gson.fromJson(buffer.toString(), ServerResponse.class);

                System.out.println(serverResponse.response_msg);
                //아이디 중복
                if(serverResponse.response_msg.equals("CheckAccount_fail")){
                    flag = false;
                }
                //회원가입 실패
                if(serverResponse.response_msg.equals("JoinAccount_fail")) {
                    return null;
                }


            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
                Log.d("gson", "sJsonText1: " + osw);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    protected void onPostExecute(Boolean flag) {
        super.onPostExecute(flag);
    }
}
