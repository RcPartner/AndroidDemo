package com.rc.androiddemo.components.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rc.androiddemo.R;


/**
 * Description:
 * User: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2014-12-11 17:39
 */
public class Fragment3 extends Fragment{

    TextView tvFragment;
    int a;
    View vRoot;

    String TAG = "Fragment3";

    Boolean isFirst = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate---" + TAG + "---被实例化了");
        a = 1;
        System.out.println("onCreate---" + TAG + "---a = 3");
        isFirst = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (isFirst) {
            System.out.println("onCreateView---" + TAG + "---被实例化了");
            isFirst = false;
            vRoot = inflater.inflate(R.layout.fragment_3, container, false);
            tvFragment = (TextView) vRoot.findViewById(R.id.tvFragment);
            tvFragment.setText(TAG + "的控件被设置了");
        } else {
            System.out.println("onCreateView---再次进入---" + TAG);
            if (a == 3)
            {
                System.out.println("onCreateView---再次进入---" + TAG + "---保存了a的值，仍然为3" );
            }
            if (tvFragment != null && tvFragment.getText().equals(TAG + "的控件被设置了"))
            {
                System.out.println("onCreateView---再次进入---" + TAG + "---还是原来那个控件" );
            }
        }
        return vRoot;
    }
}
