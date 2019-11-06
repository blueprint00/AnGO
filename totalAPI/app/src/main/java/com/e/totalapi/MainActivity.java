package com.e.totalapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.e.R;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private static final String URL_ADDRESS = "http://172.16.15.152:8080/ango/test.jsp";  //주소 변경

    Button button1, button2, button3, buttonS1, buttonS2, buttonS3;
    public static TextView data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        buttonS1 = (Button) findViewById(R.id.buttonS1);
        buttonS2 = (Button) findViewById(R.id.buttonS2);
        buttonS3 = (Button) findViewById(R.id.buttonS3);

        data = (TextView) findViewById(R.id.txt);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.e.air.HttpAsyncTask().execute();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.e.weather.HttpAsyncTask().execute();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.e.play.HttpAsyncTask().execute();
            }
        });

        buttonS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.e.air.HttpAsyncTask().execute();
            }
        });
        buttonS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.e.weather.HttpAsyncTask().execute();
            }
        });
        buttonS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.e.play.HttpAsyncTask().execute();
            }
        });
    }
}
