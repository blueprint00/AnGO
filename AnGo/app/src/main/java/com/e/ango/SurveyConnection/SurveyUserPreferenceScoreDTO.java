package com.e.ango.SurveyConnection;
//유저 선호도 점수 전송
//
//        안드로이드
//        "request_msg" : "InsertUserPreference"
//        "id" : 유저아이디
//        "weather_type"
//        "prefer_list" :
//          "category_id"
//          "user_score"
//
//        서버
//        "response_msg" : “InsertUserPreference_fail”

import java.util.ArrayList;

public class SurveyUserPreferenceScoreDTO { //안드로이드가 서버에게 유저의 놀거리에 대한 선호도 점수를 전송하는 클래스
    String request_msg;
    String token;
    String weather_type;
    ArrayList<Prefer_List> prefer_list;

    public SurveyUserPreferenceScoreDTO() {
    }

    public SurveyUserPreferenceScoreDTO(String request_msg, String token, String weather_type, ArrayList<Prefer_List> prefer_list) {
        this.request_msg = request_msg;
        this.token = token;
        this.weather_type = weather_type;
        this.prefer_list = prefer_list;
    }

    public String getRequest_msg() {
        return request_msg;
    }

    public void setRequest_msg(String request_msg) {
        this.request_msg = request_msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWeather_type() {
        return weather_type;
    }

    public void setWeather_type(String weather_type) {
        this.weather_type = weather_type;
    }

    public ArrayList<Prefer_List> getPrefer_list() {
        return prefer_list;
    }

    public void setPrefer_list(ArrayList<Prefer_List> prefer_list) {
        this.prefer_list = prefer_list;
    }
}
