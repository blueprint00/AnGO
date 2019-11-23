package com.example.weathersearch;

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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.weathersearch.ListView.ListAdapter;
import com.example.weathersearch.ListView.ListData;
import com.example.weathersearch.Play.PlayObject;
import com.example.weathersearch.Recommend.RecommendTask;
import com.example.weathersearch.SearchArea.SearchAirWeatherObject;
import com.example.weathersearch.SearchArea.SearchAirWeatherTask;
import com.example.weathersearch.SearchArea.SearchWeatherTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class SearchAreaActivity extends AppCompatActivity {
    String date;//날짜 받아온 결과
    String time;//시간 받아온 결과
    String xCoordinate;
    String yCoordinate;
    String districtResult;//사용자가 선택한 지역(api 파싱할때 지역 비교를 위해 필요)
    Spinner spinner_District;//지역 설정
    Button button_search;
    TextView textView_selectDate;//날짜(년,월,일) 설정
    TextView textView_selectTime;//시간(시,분) 설정
    TextView textView_air;
    TextView textView_humidity;
    TextView textView_icon;
    TextView textView_temperature;
    TextView textView_temperatureMax;
    TextView textView_temperatureMin;
    TextView textView_title;
    TextView textView_summary;
    int temperature = 0;
    int humidity = 0;
    int temperatureMax = 0;
    int temperatureMin = 0;
    String weatherType;

    DatePickerDialog.OnDateSetListener setDateListener;
    TimePickerDialog.OnTimeSetListener setTimeListner;
    int inputHour;//사용자가 지정한 시간
    int inputYear;//사용자가 지정한 년도
    int inputMonth;//사용자가 지정한 월
    int inputDay;//사용자가 지정한 일

    Boolean flag_userPreferencePlayObjects;
    Boolean flag = false;
    SearchAirWeatherObject searchAirWeatherObject;
    ArrayList<PlayObject> originalPlayObjects;
    ArrayList<PlayObject> userPreferencePlayObjects;
    ArrayList<ListData> arrayListData;
    ListView printList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_area);

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



        //spinner 선언 //(지역 설정 드롭박스)
        spinner_District = findViewById(R.id.spinner_districtName);

        spinner_District.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                           SearchAreaActivity.this
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
                            SearchAreaActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
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

        //텍스트뷰 선언(날씨 파싱 받아오기)
        //textView_weatherPasredData = findViewById(R.id.textView_weatherParsedData);
        textView_humidity = findViewById(R.id.tv_humidity);
        textView_icon= findViewById(R.id.tv_weather);
        textView_temperature = findViewById(R.id.tv_temperature);
        textView_air = findViewById(R.id.tv_air);
        textView_temperatureMax = findViewById(R.id.tv_temperatureMax);
        textView_temperatureMin = findViewById(R.id.tv_temperatureMin);
        textView_title = findViewById(R.id.textTitle);
        textView_summary = findViewById(R.id.textSummary);

        button_search = findViewById(R.id.button_search);


        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("검색 기능 시작 ! ");
                if(inputHour == hour && inputDay == day && inputMonth == month && inputYear == year) {//사용자가 현재시각에서 다른 장소를 검색할때
                    try {
                        searchAirWeatherObject = new SearchAirWeatherTask(date, time, xCoordinate, yCoordinate, districtResult).execute().get();
                        originalPlayObjects = new HttpAsyncTaskPlay(xCoordinate, yCoordinate).execute().get();

                        if(searchAirWeatherObject.getWeatherType() == null || originalPlayObjects.get(0).getCat2() == null) {
                            System.out.println("파싱에 실패하였습니다.");
                            flag = false;
                        }
                        else {
                            flag = true;
                            System.out.println("파싱에 성공하였습니다.");
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(flag) {
                        System.out.println("//////////////////////////////////////////////");//입력 값들이 잘들어갔는지 확인하기 위한 print
                        System.out.println(xCoordinate + "," + yCoordinate + "," + date + "T" + time + "?exclude=hourly,flags");
                        System.out.println("Mainactivity.SearchAirWeatherTask" + searchAirWeatherObject.getWeatherType());

                        humidity = (int)searchAirWeatherObject.getCurrentlyHumidity();
                        temperature = (int)searchAirWeatherObject.getCurrentlyTemperature();
                        temperatureMax = (int)searchAirWeatherObject.getDailyApparentTemperatureMax();
                        temperatureMin = (int)searchAirWeatherObject.getDailyApparentTemperatureMin();

                        textView_air.setText(searchAirWeatherObject.getAirPm10Value());
                        textView_humidity.setText(Integer.toString(humidity));
                        textView_icon.setText(searchAirWeatherObject.getCurrentlyIcon());
                        textView_temperature.setText(Integer.toString(temperature));
                        textView_temperatureMax.setText(Integer.toString(temperatureMax));
                        textView_temperatureMin.setText(Integer.toString(temperatureMin));

                        System.out.println(searchAirWeatherObject.getAirPm10Value());

                        try {
                            System.out.println(searchAirWeatherObject.getWeatherType());
                            weatherType = searchAirWeatherObject.getWeatherType();
                            System.out.println(weatherType);
                            userPreferencePlayObjects = new RecommendTask(weatherType, originalPlayObjects).execute().get();

                            if(userPreferencePlayObjects == null) {
                                flag_userPreferencePlayObjects = false;
                            }
                            else {
                                flag_userPreferencePlayObjects = true;
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(flag_userPreferencePlayObjects) {

                            arrayListData = new ArrayList<ListData>();

                            for (int i = 0; i < userPreferencePlayObjects.size(); i++) {
                                ListData listData = new ListData();
                                listData.title = userPreferencePlayObjects.get(i).title;
                                listData.summary = userPreferencePlayObjects.get(i).getAddr1() + " " + userPreferencePlayObjects.get(i).getAddr2();
                                arrayListData.add(listData);
                            }

                            printList = (ListView) findViewById(R.id.listView);
                            ListAdapter listAdapter = new ListAdapter(arrayListData);
                            printList.setAdapter(listAdapter);

                        }//if(flag_userPreferencePlayObjects)문 끝
                    }
                }


                else {//나머지(다른지역 다른시간, 현재지역 다른시간)
                    try {
                        searchAirWeatherObject = new SearchWeatherTask(date, time, xCoordinate, yCoordinate, districtResult).execute().get();
                        originalPlayObjects = new HttpAsyncTaskPlay(xCoordinate, yCoordinate).execute().get();

                        if(searchAirWeatherObject.getWeatherType() == null || originalPlayObjects.get(0).getCat2() == null) {
                            flag = false;
                            System.out.println("파싱에 실패하였습니다.");
                        }
                        else {
                            flag = true;
                            System.out.println("파싱에 성공하였습니다.");
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(flag) {
                        System.out.println("SearchAreaActivity.SearchWeatherTask : 날씨 타입 : " + searchAirWeatherObject.getWeatherType());

                        if(searchAirWeatherObject.getAirPm10Value() == null || searchAirWeatherObject.getAirPm10Value() == "0.0") {
                            textView_air.setText("미세먼지 정보가 없습니다.");
                            System.out.println("미세먼지 정보가 없습니다.");
                        }
                        else {
                            textView_air.setText(searchAirWeatherObject.getAirPm10Value());
                            System.out.println("미세먼지 정보가 있습니다.");
                        }

                        humidity = (int)searchAirWeatherObject.getCurrentlyHumidity();
                        temperature = (int)searchAirWeatherObject.getCurrentlyTemperature();
                        temperatureMax = (int)searchAirWeatherObject.getDailyApparentTemperatureMax();
                        temperatureMin = (int)searchAirWeatherObject.getDailyApparentTemperatureMin();

                        textView_humidity.setText(Integer.toString(humidity));
                        textView_icon.setText(searchAirWeatherObject.getCurrentlyIcon());
                        textView_temperature.setText(Integer.toString(temperature));
                        textView_temperatureMax.setText(Integer.toString(temperatureMax));
                        textView_temperatureMin.setText(Integer.toString(temperatureMin));

                        try {
                            System.out.println(searchAirWeatherObject.getWeatherType());
                            weatherType = searchAirWeatherObject.getWeatherType();
                            System.out.println(weatherType);
                            userPreferencePlayObjects = new RecommendTask(weatherType,  originalPlayObjects).execute().get();

                            if(userPreferencePlayObjects == null) {
                                flag_userPreferencePlayObjects = false;
                            }
                            else {
                                flag_userPreferencePlayObjects = true;
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(flag_userPreferencePlayObjects) {

                            arrayListData = new ArrayList<ListData>();

                            for (int i = 0; i < userPreferencePlayObjects.size(); i++) {
                                ListData listData = new ListData();
                                listData.title = userPreferencePlayObjects.get(i).title;
                                listData.summary = userPreferencePlayObjects.get(i).getAddr1() + " " + userPreferencePlayObjects.get(i).getAddr2();
                                arrayListData.add(listData);
                            }

                            printList = (ListView) findViewById(R.id.listView);
                            ListAdapter listAdapter = new ListAdapter(arrayListData);
                            printList.setAdapter(listAdapter);

                        }//if(flag_userPreferencePlayObjects)문 끝

                    }//if(flag)문 끝
                }
            }
        });
    }
}


