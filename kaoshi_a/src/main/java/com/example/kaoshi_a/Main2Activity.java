package com.example.kaoshi_a;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.kaoshi_a.adapters.Main2Adapter;
import com.example.kaoshi_a.fragments.CollectFragment;
import com.example.kaoshi_a.fragments.HomeFragment;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private TabLayout main2_tab;
    private ViewPager main2_vp;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        main2_tab = (TabLayout) findViewById(R.id.main2_tab);
        main2_vp = (ViewPager) findViewById(R.id.main2_vp);

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CollectFragment());

        main2_tab.addTab(main2_tab.newTab().setText("首页"));
        main2_tab.addTab(main2_tab.newTab().setText("收藏"));
        Main2Adapter main2Adapter = new Main2Adapter(getSupportFragmentManager(), fragments);
        main2_vp.setAdapter(main2Adapter);

        main2_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                main2_vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        main2_vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(main2_tab));
    }
}
