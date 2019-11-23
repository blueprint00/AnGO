package com.e.ango;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.e.ango.SurveyConnection.*;
import com.e.ango.Request.PreferDto;
import com.e.ango.Request.RequestDto;
import com.e.ango.Response.ResponseDto;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;


public class SurveyActivity extends AppCompatActivity {

    int pos;
    String question[] = {"11", "22", "33", "44", "55", "66", "77", "88", "99", "100"};
    Float ratingNum[][] = new Float[10][6];///카테고리 점수
    String url = "172.30.1.24"; //서버 (와이파이 바뀔 시 변경)


    Boolean flag = false;
    Random r = new Random();//랜덤값을 주기 위한 변수
    int[] randomQ =  new int[10];//10개의 문제
    int[] randomC =  new int[6]; //6개의 놀거리 카테고리 보기(질문)
    Float rating_num[] = new Float[6];

    ResponseDto responseDto;
    Bitmap[] bitmaps = new Bitmap[randomC.length];

    String[] categoryID =  new String[randomC.length];
    RequestDto[] requestDtos= new RequestDto[randomQ.length];//서버에 유저의 선호도를 요청하는 Dto


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        try {
            responseDto = new SurveyTask().execute().get();//서버에서 놀거리 카테고리와 질문을 받아와야만 view에 날씨 질문과 놀거리를 보여준다.
            if(responseDto != null)
                flag = true;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (flag) {
            System.out.println(responseDto.getQuestion_list().size());
            System.out.println(responseDto.getPrefer_list().size());

            pos = 0;

            final TextView textView = (TextView) findViewById(R.id.question);

            RatingBar rb1 = (RatingBar) findViewById(R.id.ratingBar1);
            RatingBar rb2 = (RatingBar) findViewById(R.id.ratingBar2);
            RatingBar rb3 = (RatingBar) findViewById(R.id.ratingBar3);
            RatingBar rb4 = (RatingBar) findViewById(R.id.ratingBar4);
            RatingBar rb5 = (RatingBar) findViewById(R.id.ratingBar5);
            RatingBar rb6 = (RatingBar) findViewById(R.id.ratingBar6);

            ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
            ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
            ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
            ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
            ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);

//            for(int i=0; i<ratingNum.length; i++) {//ratingnum 의 값을 0.0 으로 초기화
//                for(int j=0;j<ratingNum[i].length;j++) {
//                    ratingNum[i][j]= Float.parseFloat("0.0");
//                }
//            }
            for(int i=0; i<rating_num.length; i++) {
                rating_num[i] = Float.parseFloat("0.0");
            }
            for(int i = 0; i<randomQ.length;i++) {
                requestDtos[i]= new RequestDto();

            }

            for (int i = 0; i < randomQ.length; i++) {//날씨 질문 순서 난수 발생
                  randomQ[i] = r.nextInt(responseDto.getQuestion_list().size()); //(0~16) 사이의 10개 숫자 랜덤으로 뽑기
                   for (int j = 0; j < i; j++) {
                       if (randomQ[i] == randomQ[j]) {
                           i--;
                       }
                   }
            }

            for(int i = 0; i < randomQ.length; i++) {//서버로 보낼 객체에 날씨 타입 넣기 //날씨 질문 10개 생성
                requestDtos[i].setWeather_type(responseDto.getQuestion_list().get(randomQ[i]).getWeather_type());
                System.out.println( (i+1) +"번째 날씨 질문의 날씨 타입 : " + requestDtos[i].getWeather_type());
                question[i] = responseDto.getQuestion_list().get(randomQ[i]).getQuestion_text();
                System.out.println((i+1)+ "번째 날씨 질문" + question[i]);
            }


            setQuestion();
            NewImage();

            rb1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                    //ratingNum[pos][0] = Float.valueOf(rating);//별점 준것을 저장
                    rating_num[0] = Float.valueOf(rating);
                }
            }); //레이팅 값이 변경되면 위에서 rating 점수를 실수로 알 수 있다. 바뀌면 바로 저장해주는 것이 좋을 듯 하다.

            rb2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                    //ratingNum[pos][1] = Float.valueOf(rating);//별점 준것을 저장
                    rating_num[1] = Float.valueOf(rating);
                }
            }); //레이팅 값이 변경되면 위에서 rating 점수를 실수로 알 수 있다. 바뀌면 바로 저장해주는 것이 좋을 듯 하다.

        rb3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                    //ratingNum[pos][2] = Float.valueOf(rating);//별점 준것을 저장
                    rating_num[2] = Float.valueOf(rating);
                }
            }); //레이팅 값이 변경되면 위에서 rating 점수를 실수로 알 수 있다. 바뀌면 바로 저장해주는 것이 좋을 듯 하다.

        rb4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                    //ratingNum[pos][3] = Float.valueOf(rating);//별점 준것을 저장
                    rating_num[3] = Float.valueOf(rating);
                }
            }); //레이팅 값이 변경되면 위에서 rating 점수를 실수로 알 수 있다. 바뀌면 바로 저장해주는 것이 좋을 듯 하다. */

        rb5.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                    //ratingNum[pos][4] = Float.valueOf(rating);//별점 준것을 저장
                    rating_num[4] = Float.valueOf(rating);
                }
            });

        rb6.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                    //ratingNum[pos][5] = Float.valueOf(rating);//별점 준것을 저장
                    rating_num[5] = Float.valueOf(rating);
                }
            });

        System.out.println("//////////////////////////////////////////////if문끝");
        } //if문 끝

    }


    public void nextClick(View view) {
        if(pos==9) {
            Toast toast = Toast.makeText(this, "마지막 질문입니다. 완료 버튼을 누르십시오.", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        System.out.println("nextclick시작");

        Boolean flag_UserPreferenceScore = false;
        ArrayList<PreferDto> preferDtos = new ArrayList<PreferDto>();//////

//        for(int i=0; i < 6; i++) {
//            preferDtos.add(new PreferDto(categoryID[i],ratingNum[pos][i]));
//        }
        for(int i=0; i < 6; i++) {
            preferDtos.add(new PreferDto(categoryID[i],rating_num[i]));
        }
        for(int i=0;i < 6;i++) {
            System.out.println("유저 스코어 : " + preferDtos.get(i).getUser_score());
            System.out.println("놀거리 아이디 : " + preferDtos.get(i).getCg_id());
            System.out.println();
        }
        System.out.println(pos);
        System.out.println();

        requestDtos[pos].setPreference_list(preferDtos);

        for(int i=0; i<6; i++) {
            System.out.println("서버 통신용 : 날씨 타입 : " + requestDtos[pos].getWeather_type());
            System.out.println("서버 통신용 : 유저 스코어 : " + requestDtos[pos].getPreference_list().get(i).getUser_score());
            System.out.println("서버 통신용 : 놀거리 아이디 : " + requestDtos[pos].getPreference_list().get(i).getCg_id());
            System.out.println();
        }
        try {
            flag_UserPreferenceScore = new SurveyUserPreferenceScoreTask(requestDtos[pos]).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("///////////////////////////////////////////////////////////");

        pos++;
        System.out.println("pos의 값은 이것입니다." + pos);
        System.out.println(flag_UserPreferenceScore);
        if(flag_UserPreferenceScore) {
            System.out.println("resetRating 시작");
            resetRatingbar();
            System.out.println("resetRating 끝");

            System.out.println("NewImage시작");
            NewImage();
            System.out.println("NewImage끝");

            System.out.println("setQuestion시작");
            setQuestion();
            System.out.println("setQuestion 끝");

            System.out.println("nextClick끝");
        }
    }

    public void completeClick(View view) {
        if (pos == 9) { //설문조사 10개를 했을때 완료 버튼을 누르면 실행

            System.out.println(" Complete Click 시작 ");

            ArrayList<PreferDto> preferDtos = new ArrayList<PreferDto>();//////

            for(int i=0; i < 6; i++) {
                preferDtos.add(new PreferDto(categoryID[i],rating_num[i]));
            }
            for(int i=0;i < 6;i++) {
                System.out.println("유저 스코어 : " + preferDtos.get(i).getUser_score());
                System.out.println("놀거리 아이디 : " + preferDtos.get(i).getCg_id());
                System.out.println();
            }
            System.out.println(pos);
            System.out.println();
            requestDtos[pos].setPreference_list(preferDtos);

            for(int i=0; i<6; i++) {
                System.out.println("서버 통신용 : 날씨 타입 : " + requestDtos[pos].getWeather_type());
                System.out.println("서버 통신용 : 유저 스코어 : " + requestDtos[pos].getPreference_list().get(i).getUser_score());
                System.out.println("서버 통신용 : 놀거리 아이디 : " + requestDtos[pos].getPreference_list().get(i).getCg_id());
                System.out.println();
            }

            new SurveyUserPreferenceScoreTask(requestDtos[pos]).execute();

            Toast toast = Toast.makeText(this, "설문조사 완료", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(SurveyActivity.this, SelectActivity.class);
            startActivity(intent);

            finish();
        }

        else {
            Toast toast = Toast.makeText(this, "아직 " + (pos+1) + " 페이지 입니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void setQuestion()
    {
        final TextView textView = (TextView) findViewById(R.id.question);
        System.out.println("pos 값 : " + pos);
        textView.setText(question[pos]);
    }

    public void resetRatingbar() {
        RatingBar rb1 = (RatingBar) findViewById(R.id.ratingBar1);
        RatingBar rb2 = (RatingBar) findViewById(R.id.ratingBar2);
        RatingBar rb3 = (RatingBar) findViewById(R.id.ratingBar3);
        RatingBar rb4 = (RatingBar) findViewById(R.id.ratingBar4);
        RatingBar rb5 = (RatingBar) findViewById(R.id.ratingBar5);
        RatingBar rb6 = (RatingBar) findViewById(R.id.ratingBar6);

        rb1.setRating(0.0f);
        rb2.setRating(0.0f);
        rb3.setRating(0.0f);
        rb4.setRating(0.0f);
        rb5.setRating(0.0f);
        rb6.setRating(0.0f);
    }

    public void NewImage() {
        System.out.println("NewImage시작");
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);

        for (int i = 0; i < randomC.length; i++) {//카테고리 순서 난수 발생//6개
            randomC[i] = r.nextInt(responseDto.getPrefer_list().size());//
            for (int j = 0; j < i; j++) {
                if (randomC[i] == randomC[j]) {
                    i--;
                }
            }
        }

        String[] url_CatrgoryID = new String[responseDto.getPrefer_list().size()];

        for(int i=0;i < randomC.length;i++) {//서버에 보낼 카테고리 아이디 저장
            categoryID[i]= responseDto.getPrefer_list().get(randomC[i]).getCg_id();//A001101 //6개
            url_CatrgoryID[i] = "http://" + url + ":8090/final_ango/images/" + categoryID[i] + ".jpg";//http~~~~
        }

        //url을 이용하여 비트맵으로 이미지 저장
        for (int i = 0; i < randomC.length; i++) {
            try {
                bitmaps[i]= new LoadImage().execute(url_CatrgoryID[i]).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        imageView1.setImageBitmap(bitmaps[0]); //카테고리 아이디에 해당하는 이미지 뷰 뿌리기
        imageView2.setImageBitmap(bitmaps[1]);
        imageView3.setImageBitmap(bitmaps[2]);
        imageView4.setImageBitmap(bitmaps[3]);
        imageView5.setImageBitmap(bitmaps[4]);
        imageView6.setImageBitmap(bitmaps[5]);
        System.out.println("NewImage끝");
    }


}