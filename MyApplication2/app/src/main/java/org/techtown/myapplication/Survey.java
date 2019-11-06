package org.techtown.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Survey extends AppCompatActivity {

    private ViewPager viewPager ;
    private SurveyPagerAdapter pagerAdapter ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        viewPager = (ViewPager) findViewById(R.id.viewPager) ;
        pagerAdapter = new SurveyPagerAdapter(this) ;
        viewPager.setAdapter(pagerAdapter) ;



    }


}