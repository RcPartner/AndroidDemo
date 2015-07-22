package com.rc.androiddemo.resources.drawable.bitmapfile;


import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.rc.androiddemo.R;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-06-05 09:51
 */
public class BitmapFileDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_file_demo);
    }

    private Drawable getDrawable() {
        return getResources().getDrawable(R.drawable.star_c);
//        api 21
//        return getResources().getDrawable(R.drawable.star_c,null);
//        return getDrawable(R.drawable.star_c);
    }

    private BitmapDrawable getBitmapDrawable() {
        return (BitmapDrawable) getDrawable();

    }

}
