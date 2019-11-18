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

public class Categories {
    public String response_msg;
    public int total;
    public ArrayList<Category_List> category_list;
}
