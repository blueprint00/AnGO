package com.e.ango.SurveyConnection;
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

import com.e.ango.SurveyConnection.SurveyQuestionsCategories.Categories;
import com.e.ango.SurveyConnection.SurveyQuestionsCategories.Question;

public class SurveyResponse { //서버가 안드로이드에게 설문조사할때 질문,카테고리 뿌리기 응답
    Question question;
    Categories category;
}
