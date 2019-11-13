package com.e.ango.API;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.ango.R;

public class APIActivity extends AppCompatActivity {

    public static String ip = "192.168.112.35";//"172.16.15.152"; //자신의 IP번호
    String serverip = "http://" + ip + ":8080/ango/Dispacher"; // 연결할 jsp주소

    Button button1, button2, button3, buttonS1, buttonS2, buttonS3, buttonType, change;
    public static TextView data;

    public Type type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        button1 = (Button) findViewById(R.id.btn_air);
        button2 = (Button) findViewById(R.id.btn_weather);
        button3 = (Button) findViewById(R.id.btn_play);
        buttonType = (Button) findViewById(R.id.btn_Type);

//        change = (Button) findViewById(R.id.btn_change);

        data = (TextView) findViewById(R.id.txt);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpAsyncTaskAir().execute();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { new HttpAsyncTaskWeather().execute(); }});
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new HttpAsyncTaskPlay().execute();
            }
        });
        buttonType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                new HttpAsyncTaskAirWeather().execute();
            }
        });

        /*change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ntent intent = new Intent(MainActivity.this, GeoActivity.class);
                //startActivity(intent);
            }
        });*/


    }
}
