package com.rc.androiddemo.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.rc.androiddemo.R;


/**
 * @Description:
 * @Author: WuRuiqiang
 * @CreateDate: 2015/4/28-0:38
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: [v1.0]
 */
public class MyDialogFragment extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        view.layout(500, 500, 1000, 1000);
        return view;
    }
}
