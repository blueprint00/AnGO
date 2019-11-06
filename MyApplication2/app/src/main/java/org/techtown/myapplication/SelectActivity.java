package org.techtown.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.techtown.myapplication.R;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }
    public void currentClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
