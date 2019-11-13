package com.e.ango.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.e.ango.API.APIActivity;
import com.e.ango.R;

public class SelectActivity extends AppCompatActivity {

    static Button button1, button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        button1 = (Button) findViewById(R.id.gpsLocationBT);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, APIActivity.class);
                startActivity(intent);
            }
        });

    }



}
