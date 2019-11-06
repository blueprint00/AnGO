package org.techtown.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SurveyPagerAdapter extends PagerAdapter {
    private Context mContext = null;

    public SurveyPagerAdapter() {

    }



    // Context를 전달받아 mContext에 저장하는 생성자 추가.
    public SurveyPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;

        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.survay_page, container, false);

            final TextView textView = (TextView) view.findViewById(R.id.question);
            textView.setText("TEXT " + position);
/*
            RatingBar rb1 =(RatingBar)view.findViewById(R.id.ratingBar1);
            RatingBar rb2 =(RatingBar)view.findViewById(R.id.ratingBar2);
            RatingBar rb3 =(RatingBar)view.findViewById(R.id.ratingBar3);
            RatingBar rb4 =(RatingBar)view.findViewById(R.id.ratingBar4);

            rb1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {
                    textView.setText("rating : " + rating); //값 바뀌는지 실험
                }
            }); //레이팅 값이 변경되면 위에서 rating 점수를 실수로 알 수 있다. 바뀌면 바로 저장해주는 것이 좋을 듯 하다.

            rb2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {
                    textView.setText("rating : " + rating);
                }
            }); //레이팅 값이 변경되면 위에서 rating 점수를 실수로 알 수 있다. 바뀌면 바로 저장해주는 것이 좋을 듯 하다.

            rb3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {
                    textView.setText("rating : " + rating);
                }
            }); //레이팅 값이 변경되면 위에서 rating 점수를 실수로 알 수 있다. 바뀌면 바로 저장해주는 것이 좋을 듯 하다.

            rb4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {
                    textView.setText("rating : " + rating);
                }
            }); //레이팅 값이 변경되면 위에서 rating 점수를 실수로 알 수 있다. 바뀌면 바로 저장해주는 것이 좋을 듯 하다. */
 /*
            final ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
            final ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);
            final ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView3);
            final ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView4);

            imageView.setImageResource(R.drawable.untitled3);
            imageView2.setImageResource(R.drawable.untitled3);
            imageView3.setImageResource(R.drawable.untitled3);
            imageView4.setImageResource(R.drawable.untitled3);
            */ //이런 식으로 이미지 주소를 바꿔주면 됨. 포지션에 맞게 이프문 잘 활용

        }

        // 뷰페이저에 추가.
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 뷰페이저에서 삭제.
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // 전체 페이지 수는 10개로 고정.
        return 10;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View) object);
    }

}
