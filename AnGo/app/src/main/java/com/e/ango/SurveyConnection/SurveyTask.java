package com.e.ango.SurveyConnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.e.ango.SurveyConnection.SurveyQuestionsCategories.Category_List;
import com.e.ango.SurveyConnection.SurveyQuestionsCategories.Question_List;
import com.e.ango.SurveyConnection.SurveyQuestionsCategories.Category_List;
import com.e.ango.SurveyConnection.SurveyQuestionsCategories.Question_List;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import static com.e.ango.Login.RegisterTask.loginResponse;

public class SurveyTask extends AsyncTask<Void, Void, Boolean> {
    private ProgressDialog pDialog;
    String url = "http://172.16.10.37"; //서버 (와이파이 바뀔 시 변경)

    private String URL_ADDRESS = url + ":8080/ango/Dispacher";  //서버 주소
    String token = loginResponse.token;
    String request_msg ="GetQuestionAndCategory";
    String str;
    public static SurveyQuestion[] surveyQuestion;
    public static SurveyCategory[] surveyCategory;
    Boolean flag = true;//실행 제어를 위한 flag //SurveyServerConnection이 먼저 실행되어야 SurveyPagerAdapter를 실행시킬 수 있다


    @Override
    protected Boolean doInBackground(Void... voids) {
        try {

            URL Url = new URL(URL_ADDRESS); // URL화 한다.
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); // get방식 통신

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            SurveyDTO surveyDTO = new SurveyDTO(request_msg, token);//서버한테 questionlist 와 categorylist 요청
            Gson gson = new Gson();

            osw.write(gson.toJson(surveyDTO));
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                SurveyResponse surveyResponse = gson.fromJson(buffer.toString(), SurveyResponse.class);

                if (surveyResponse.question.response_msg.equals("GetQuestion_success") && surveyResponse.category.response_msg.equals("GetCategory_success"))
               {

                    ArrayList<Question_List> question_list = surveyResponse.question.question_list;
                    ArrayList<Category_List> category_list = surveyResponse.category.category_list;

                   System.out.println(question_list.size());
                   System.out.println(category_list.size());

                    surveyQuestion = new SurveyQuestion[question_list.size()];//질문 개수 17
                    surveyCategory = new SurveyCategory[category_list.size()];//놀거리 카테고리 개수 24


                   for (int i = 0; i < question_list.size(); i++) {//17개의 날씨에 대한 질문들을 객체로 선언
                        Question_List ql = question_list.get(i);
                        surveyQuestion[i] = new SurveyQuestion(ql.weather_type,ql.text);
                    }
                    for (int i = 0; i < category_list.size(); i++) {//24개의 놀거리 카테고리들을 객체로 선언
                        Category_List cl = category_list.get(i);
                        surveyCategory[i] = new SurveyCategory(cl.category_id, cl.category_name);

                    }
                   System.out.println(surveyQuestion.length);
                   System.out.println(surveyCategory.length);


                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            }

        } catch (MalformedURLException | ProtocolException exception) {
            exception.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    protected void onPostExecute(Boolean aVoid) {
        super.onPostExecute(aVoid);

    }
}
