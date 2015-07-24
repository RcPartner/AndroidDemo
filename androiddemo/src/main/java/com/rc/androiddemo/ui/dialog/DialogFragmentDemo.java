package com.rc.androiddemo.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.rc.androiddemo.R;


public class DialogFragmentDemo extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
    }

    public void openDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), MyDialogFragment.class.getSimpleName());
    }

}
