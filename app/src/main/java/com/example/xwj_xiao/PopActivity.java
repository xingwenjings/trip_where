package com.example.xwj_xiao;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class PopActivity extends AppCompatActivity {

    private TabLayout pop_tab;
    private ViewPager pop_vp;
    private ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        initView();
    }

    private void initView() {
        pop_tab = (TabLayout) findViewById(R.id.pop_tab);

        pop_vp = (ViewPager) findViewById(R.id.pop_vp);
        fragments = new ArrayList<>();

    }
}
