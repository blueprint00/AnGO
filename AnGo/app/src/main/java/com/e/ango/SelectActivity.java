package com.e.ango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.e.ango.API.HttpAsyncTaskAir;
import com.e.ango.API.HttpAsyncTaskAirWeather;
import com.e.ango.API.HttpAsyncTaskPlay;

public class SelectActivity extends AppCompatActivity {

    static Button button1, button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        button1 = (Button) findViewById(R.id.gpsLocationBT);
        button2 = (Button) findViewById(R.id.selectLocationBT);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, Survey.class);
                startActivity(intent);
            }
        });

    }

}
