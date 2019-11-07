package com.e.airweather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.airweather.AirWeather.Type;
import com.e.airweather.R;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private static final String URL_ADDRESS = "http://172.16.15.152:8080/ango/test.jsp";  //주소 변경

    Button button1, button2, button3, buttonS1, buttonS2, buttonS3, buttonType;
    public static TextView data;

    public Type type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        buttonType = (Button) findViewById(R.id.buttonType);

        buttonS1 = (Button) findViewById(R.id.buttonS1);
        buttonS2 = (Button) findViewById(R.id.buttonS2);
        buttonS3 = (Button) findViewById(R.id.buttonS3);

        data = (TextView) findViewById(R.id.txt);


        buttonType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                new com.e.airweather.AirWeather.HttpAsyncTaskTotal().execute();
            }
        });
    }
}
