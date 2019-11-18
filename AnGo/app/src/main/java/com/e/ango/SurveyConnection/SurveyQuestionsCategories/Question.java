package com.e.ango.SurveyConnection.SurveyQuestionsCategories;
//설문조사할때 질문,카테고리 뿌리기
//서버
//        "question" :
//          "response_msg"
//          "total"
//          "question_list" :
//              "weather_type"
//              "text"
//        "category" :
//          "response_msg"
//          "total"
//          "category_list" :
//              "category_id"
//              "category_name"

import java.util.ArrayList;

public class Question {
    public String response_msg;
    int total;
    public ArrayList<Question_List> question_list;
}
