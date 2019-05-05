package com.example.dashixun_zuoye;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.dashixun_zuoye.adapters.HomeAdapter;
import com.example.dashixun_zuoye.fragments.HomeFragment;
import com.example.dashixun_zuoye.fragments.UploadFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView titles;
    private Toolbar toolbar;
    private TabLayout tab;
    private ViewPager vp;
    private String[] title={"首页","下载"};
    private ArrayList<Fragment> fragments;
    public static UploadFragment uploadFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolbar();
        initData();
    }

    private void initData() {
        uploadFragment=new UploadFragment();
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(uploadFragment);


        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.selector_color));
        tab.addTab(tab.newTab().setText("下载").setIcon(R.drawable.selector_color));
        HomeAdapter homeAdapter = new HomeAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(homeAdapter);


        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
                titles.setText(title[tab.getPosition()]);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

    }

    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        titles.setText(title[0]);
    }

    private void initView() {
        titles = (TextView) findViewById(R.id.titles);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }
}
