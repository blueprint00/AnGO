package com.e.ango.SurveyConnection;
//설문조사할때 질문,카테고리 뿌리기
/*안드로이드
"request_msg" : "GetQuestionAndCategory"
"id" : 유저아이디

서버
"question" :
   "response_msg"
   "total"
   "question_list" :
      "weather_type"
      "text"
"category" :
   "response_msg"
   "total"
   "category_list" :
      "category_id"
      "category_name"*/
public class SurveyDTO {//안드로이드가 서버한테 설문조사할때 질문,카테고리 뿌리기 요청
    String request_msg;
    String token;

    public SurveyDTO(String request_msg, String token) {
        this.request_msg = request_msg;
        this.token = token;
    }
}
