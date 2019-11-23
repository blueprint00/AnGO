package com.e.ango.Login;

import android.os.AsyncTask;
import android.util.Log;

import com.e.ango.Request.RequestDto;
import com.e.ango.Request.UserDto;
import com.e.ango.Response.ResponseDto;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//String request_msg;
//	String weather_type;
//	String token;
//	UserDto user;
//	ArrayList<PreferDto> preference_list = new ArrayList<>();
//	ArrayList<ReviewDto> review_list = new ArrayList<>();

public class LoginTask extends AsyncTask<Void, Void, Boolean> {

    public static String ip = "172.30.1.24"; //자신의 IP번호
    public static String serverip = "http://" + ip + ":8090/final_ango/Dispacher2"; // 연결할 jsp주소
    Boolean flag = null; // 선호도 조사 했는지 안했는지
    //null = 로그인 실패
    //false = 설문조사 안함

    public String token;

    private RequestDto requestDto = new RequestDto();
    private ResponseDto loginResponse;
    //static public String token;
    private com.e.ango.Request.UserDto userDto;

    public LoginTask(String userId, String userPass, String request_msg) {
        userDto = new UserDto(userId, userPass);
        requestDto.setUser(userDto);
        requestDto.setRequest_msg(request_msg);
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
            osw.write(gson.toJson(requestDto));
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                loginResponse = gson.fromJson(buffer.toString(), ResponseDto.class);
                //System.out.println(serverResponse.response_msg + " / " + serverResponse.token + " / " + serverResponse.availability);
                token = loginResponse.getToken();
                System.out.println("LOGIN TOKEN : " + loginResponse.getToken() + " " + token);

                //로그인 성공 시
                if(loginResponse.getResponse_msg().equals("LoginAccount_success")) { return true; }

                //설문조사 안 했으면
                if (loginResponse.getAvailability() == 1) { flag = false; }

//            } else {
//                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
    }

    public String getToken(){ return token; }
}