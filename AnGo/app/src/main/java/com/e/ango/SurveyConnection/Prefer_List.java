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

public class Prefer_List {
    String category_id;
    String user_score;

    public Prefer_List() {
    }

    public Prefer_List(String category_id, String user_score) {
        this.category_id = category_id;
        this.user_score = user_score;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }
}
