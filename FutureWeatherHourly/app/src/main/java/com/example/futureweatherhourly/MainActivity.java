package com.example.futureweatherhourly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button click;
    public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button) findViewById(R.id.button);
        data = (TextView) findViewById(R.id.weatherParsedData);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FutureWeatherParsing().execute("https://api.darksky.net/forecast/2d32bcfe938dc43f9f32db76ebf8c449/37.6,127,2019-11-07T15:00:00?exclude=hourly,flags");
            }
        });
    }


}
