package com.rc.androiddemo.components.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TabHost;

import com.rc.androiddemo.R;
import com.rc.androiddemo.components.fragment.Fragment1;
import com.rc.androiddemo.components.fragment.Fragment2;
import com.rc.androiddemo.components.fragment.Fragment3;
import com.rc.androiddemo.components.fragment.Fragment4;

/**
 * Description:
 * Author: Caizemingg(Email:Caizemingg@163.com)
 * Date: 2015-07-27 09:23
 */
public class FragmentTabHostDemo extends FragmentActivity {

    private FragmentTabHost fragmentTabHost;
    private static final Class FRAGMENT_CLASS[] = {Fragment1.class, Fragment2.class,
            Fragment3.class, Fragment4.class};
    private Switch sDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host_demo);
        fragmentTabHost = (FragmentTabHost) findViewById(R.id.fthDemo);
        sDivider = (Switch) findViewById(R.id.sDivider);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.flDemo);

        for (int i = 0; i < FRAGMENT_CLASS.length; i++) {
            TabHost.TabSpec tab = fragmentTabHost.newTabSpec("Tab" + i).setIndicator("Tab" + i);
            fragmentTabHost.addTab(tab, FRAGMENT_CLASS[i], null);
        }
        sDivider.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showDivider(isChecked);
            }
        });

    }

    /**
     * 是否显示分割线
     * @param enable
     */
    private void showDivider(boolean enable) {
        if (enable) {
            fragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        } else {
            fragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);

        }
    }
}
