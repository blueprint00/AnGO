package com.example.weatherserach;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static String date;//날짜 받아온 결과
    static String time;//시간 받아온 결과
    String xCoordinate;
    String yCoordinate;
    static String districtResult;//사용자가 선택한 지역(api 파싱할때 지역 비교를 위해 필요)
    Spinner spinner_District;//지역 설정
    Button button_search;
    TextView textView_districtResult;//지역 설정 결과
    TextView textView_selectDate;//날짜(년,월,일) 설정
    TextView textView_selectTime;//시간(시,분) 설정
    static TextView textView_weatherPasredData;//날씨 파싱 데이터
    DatePickerDialog.OnDateSetListener setDateListener;
    TimePickerDialog.OnTimeSetListener setTimeListner;
    int inputHour;//사용자가 지정한 시간
    int inputYear;//사용자가 지정한 년도
    int inputMonth;//사용자가 지정한 월
    int inputDay;//사용자가 지정한 일
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //지역 -> 좌표로 받아오기 (array.xml 사용)
        Resources res = getResources();
        final String[] districToXcoordinate = getResources().getStringArray(R.array.districtXCoordinate);
        final String[] districToYcoordinate = getResources().getStringArray(R.array.districtYcoordinate);


        //날짜(년,월,일) + 시간(시,분,초)
        textView_selectDate = findViewById(R.id.textView_selectDate);
        textView_selectTime = findViewById(R.id.textView_selectTime);
        Calendar calendar =  Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);//안드로이드 에뮬레이터의 현재 시간
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR);
        final int minute = calendar.get(Calendar.MINUTE);
        //final int second = calendar.get(Calendar.SECOND);


        //spinner 선언 //(지역 설정 드롭박스)
        spinner_District = findViewById(R.id.spinner_districtName);
        textView_districtResult = findViewById(R.id.textView_districtResult);

        spinner_District.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView_districtResult.setText(parent.getItemAtPosition(position).toString());
                districtResult = parent.getItemAtPosition(position).toString();//사용자가 지정한 지역의 지역명
                xCoordinate = districToXcoordinate[position];//사용자가 지정한 지역의 x좌표
                yCoordinate = districToYcoordinate[position];//사용자가 지정한 지역의 y좌표
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //날짜 선택
         textView_selectDate.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.N)
             @Override
             public void onClick(View v) {
                 DatePickerDialog datePickerDialog = new DatePickerDialog(
                           MainActivity.this
                         //,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                                                                        ,setDateListener,year,month,day);
                 datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                 datePickerDialog.show();
             }
         });

         setDateListener = new DatePickerDialog.OnDateSetListener() {
             @Override
             public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                 month = month + 1;
                 if(month < 10 && dayOfMonth < 10) {//파싱하기 위한 조건과 맞게 변경한 날짜 (ex)2019-11-10
                     date = year + "-0" + month + "-0" + dayOfMonth;
                     inputYear = year;//사용자가 지정한 년,월,일 삽입
                     inputMonth = month;
                     inputDay = dayOfMonth;
                     textView_selectDate.setText(date);
                 }
                 else if(dayOfMonth < 10) {
                     date = year + "-" + month + "-0" + dayOfMonth;
                     inputYear = year;//사용자가 지정한 년,월,일 삽입
                     inputMonth = month;
                     inputDay = dayOfMonth;
                     textView_selectDate.setText(date);
                 }
                 else if(month < 10) {
                     date = year + "-0" + month + "-" + dayOfMonth;
                     inputYear = year;//사용자가 지정한 년,월,일 삽입
                     inputMonth = month;
                     inputDay = dayOfMonth;
                     textView_selectDate.setText(date);
                 }
                 else {
                     date = year + "-" + month + "-" + dayOfMonth;
                     inputYear = year;//사용자가 지정한 년,월,일 삽입
                     inputMonth = month;
                     inputDay = dayOfMonth;
                     textView_selectDate.setText(date);
                 }
             }
         };

         //시간 선택
        textView_selectTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                            MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                                                                      ,setTimeListner, hour, minute, true);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });

        setTimeListner = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 10 && minute < 10) {//파싱하기 위한 조건과 맞게 변경한 시간 (ex)22:10:00
                    time = "0" + hourOfDay + ":0" + minute + ":" + "00";
                    textView_selectTime.setText(time);
                    inputHour = hourOfDay;//사용자가 지정한 시간 삽입
                }
                else if(hourOfDay < 10) {
                    time = "0" + hourOfDay + ":" + minute + ":" + "00";
                    textView_selectTime.setText(time);
                    inputHour = hourOfDay;//사용자가 지정한 시간 삽입
                }
                else if(minute < 10) {
                    time =  hourOfDay + ":0" + minute + ":" + "00";
                    textView_selectTime.setText(time);
                    inputHour = hourOfDay;//사용자가 지정한 시간 삽입
                }
                else {
                    time = hourOfDay + ":" + minute + ":" + "00";
                    textView_selectTime.setText(time);
                    inputHour = hourOfDay;//사용자가 지정한 시간 삽입
                }

            }
        };
        System.out.println("///////////////////////////////////////////////////////////////////////");
        System.out.println(xCoordinate + "," + yCoordinate + "," + date + "T"+ time + "?exclude=hourly,flags");
        //텍스트뷰 선언(날씨 파싱 받아오기)
        textView_weatherPasredData = findViewById(R.id.textView_weatherParsedData);
        button_search = findViewById(R.id.button_search);

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputHour == hour && inputDay == day && inputMonth == month && inputYear == year) {//사용자가 현재시각에서 다른 장소를 검색할때
                    new SearchAirWeatherParsing().execute("https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/"
                                    + xCoordinate + "," + yCoordinate + ","
                                    + date + "T" + time + "?exclude=hourly,flags",
                            "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?" +
                                    "sidoName=%EC%84%9C%EC%9A%B8&searchCondition=DAILY&numOfRows=25&ServiceKey=w%2BvIOXxlMW05eJcl2Fw894grerR3LUGL1LepRRDEjPN1ntgk2i2%2FV00sSzbn7QZAnF5iqz2WG%2BiDxWnf2tdy4A%3D%3D&_returnType=json");
                }
                else {//나머지(다른지역 다른시간, 현재지역 다른시간)
                    new SearchWeatherParsing().execute("https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/"
                            + xCoordinate + "," + yCoordinate + ","
                            + date + "T" + time + "?exclude=hourly,flags");

                }
            }
        });
    }
}


