package com.example.kaoshi_a.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaoshi_a.R;
import com.example.kaoshi_a.adapters.Main2Adapter;
import com.example.kaoshi_a.beans.TabBean;
import com.example.kaoshi_a.utils.MySeriver;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private TabLayout home_tab;
    private ViewPager home_vp;
    private ArrayList<Fragment> fragments;
    private ArrayList<TabBean.DataBean> dataBeans;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MySeriver.getUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MySeriver mySeriver = retrofit.create(MySeriver.class);
        Observable<TabBean> tab = mySeriver.getTab();
        tab.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("tag","onSubscribe");
                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                        Log.i("tag","onNext");
                        dataBeans.addAll(tabBean.getData());
                        for (int i = 0; i < dataBeans.size(); i++) {
                            fragments.add(new ArticleFragment(dataBeans.get(i).getId()));
                            home_tab.addTab(home_tab.newTab().setText(dataBeans.get(i).getName()));
                        }

                        Main2Adapter main2Adapter = new Main2Adapter(getChildFragmentManager(), fragments);
                        home_vp.setAdapter(main2Adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("tag","onError"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("tag","onComplete");
                    }
                });
    }

    private void initView(View inflate) {
        home_tab = (TabLayout) inflate.findViewById(R.id.home_tab);
        home_vp = (ViewPager) inflate.findViewById(R.id.home_vp);

        fragments = new ArrayList<>();
        dataBeans = new ArrayList<>();




        home_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                home_vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        home_vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(home_tab));
    }
}
