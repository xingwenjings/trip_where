package com.example.xwj_xiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.xwj_xiao.adapters.Main2Adapter;
import com.example.xwj_xiao.beans.TabBean;
import com.example.xwj_xiao.fragments.ArticleFragment;
import com.example.xwj_xiao.utils.MySeriver;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> fragments;
    private ArrayList<TabBean.DataBean> tabBeans;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initToolbar();
        initData();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ImageView img = toolbar.findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = LayoutInflater.from(Main2Activity.this).inflate(R.layout.layout_popwindow, null);
                PopupWindow popupWindow = new PopupWindow(inflate, 100, 100);
                Button pop = inflate.findViewById(R.id.pop);
                popupWindow.showAsDropDown(inflate);
                pop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Main2Activity.this, PopActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });

    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MySeriver.getUrlTab)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MySeriver mySeriver = retrofit.create(MySeriver.class);
        Observable<TabBean> url = mySeriver.getUrl();
        url.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("tag", "onSubscribe");
                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                        Log.i("tag", "onNext");
                        tabBeans.addAll(tabBean.getData());

                        for (int i = 0; i < tabBeans.size(); i++) {
                            fragments.add(new ArticleFragment(tabBeans.get(i).getId()));
                            tab.addTab(tab.newTab().setText(tabBeans.get(i).getName()));
                        }
                        Main2Adapter main2Adapter = new Main2Adapter(getSupportFragmentManager(), fragments);
                        vp.setAdapter(main2Adapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("tag", "onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("tag", "onComplete");
                    }
                });
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);

        fragments = new ArrayList<>();
        tabBeans = new ArrayList<>();



        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));


        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

}
