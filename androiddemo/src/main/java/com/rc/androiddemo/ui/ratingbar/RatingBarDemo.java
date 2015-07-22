package com.rc.androiddemo.ui.ratingbar;


import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rc.androiddemo.R;

/**
 * Description:
 * Atuhor: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-02-02 18:53
 */
public class RatingBarDemo extends Activity {

    private RatingBar rbDemo;
    private TextView tvPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar_demo);

        rbDemo = (RatingBar)this.findViewById(R.id.rbDemo);
        tvPoint = (TextView)this.findViewById(R.id.tvPoint);

        rbDemo.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {

            @Override
            public void onRatingChanged(RatingBar rb, float point,
                                        boolean status)
            {
                // TODO Auto-generated method stub
                tvPoint.setText(String.format("(%d分)", (int) point));
            }
        });

    }
}
