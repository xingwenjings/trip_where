package com.example.lianxi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    private TabLayout tab;
    private ImageView imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("首页").setIcon(R.mipmap.comment_icon));
        tab.addTab(tab.newTab().setText("问答").setIcon(R.mipmap.collection_icons));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.mipmap.comment_icons));
        imgs = (ImageView) findViewById(R.id.imgs);

    }
}
