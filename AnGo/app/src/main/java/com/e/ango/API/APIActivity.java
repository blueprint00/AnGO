package com.e.ango.API;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.ango.R;

public class APIActivity extends AppCompatActivity {

    public static TextView tv_3, tv_4, tv_5, tv_6;

    public Type type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

//        tv_3 = (TextView)findViewById(R.id.tv_humidity);
//        tv_4 = (TextView)findViewById(R.id.tv_temperature);
//        tv_5 = (TextView)findViewById(R.id.tv_weather);
//        tv_6 = (TextView)findViewById(R.id.tv_air);

        //new HttpAsyncTaskAir().execute();
        //new HttpAsyncTaskAirWeather().execute();
        //new HttpAsyncTaskPlay().execute();

    }
}
