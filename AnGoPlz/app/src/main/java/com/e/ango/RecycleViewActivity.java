package com.e.ango;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.ango.RecycleView.CurrentLocationPlay;
import com.e.ango.RecycleView.CurrentLocationPlayAdapter;
import com.e.ango.Request.PreferDto;
import com.e.ango.Response.ResponseDto;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);

        RecyclerView recyclerView = findViewById(R.id.recycleView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CurrentLocationPlayAdapter adapter = new CurrentLocationPlayAdapter();
//        CurrentLocationPlay play = new CurrentLocationPlay("ADDRESS", "NAME" , "TITLE");
//        adapter.addItem(play);
//
//        recyclerView.setAdapter(adapter);

        ResponseDto responseDto = new ResponseDto();









    }


}
