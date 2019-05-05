package com.example.xwj_xiao.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xwj_xiao.Main3Activity;
import com.example.xwj_xiao.MainActivity;
import com.example.xwj_xiao.R;
import com.example.xwj_xiao.adapters.ArticleAdapter;
import com.example.xwj_xiao.adapters.VpAdapter;
import com.example.xwj_xiao.beans.ArtcileBean;
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

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ArticleFragment extends Fragment {


    private RecyclerView article_rv;
    private ArrayList<ArtcileBean.DataBean.DatasBean> datasBeans;
    private ArticleAdapter articleAdapter;
    private ViewPager article_vp;
    private ArrayList<View> views;
    private View inflate;

    public ArticleFragment(int id) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_article, container, false);
        initView(inflate);
        initData();
        initVp();
        return inflate;
    }

    private void initVp() {
        views = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_article_vp, null);
        }
        views.add(inflate);
        VpAdapter vpAdapter = new VpAdapter(views, getContext());
        article_vp.setAdapter(vpAdapter);



    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MySeriver.getArticle)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MySeriver mySeriver = retrofit.create(MySeriver.class);
        Observable<ArtcileBean> urlArticel = mySeriver.getUrlArticel();
        urlArticel.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ArtcileBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("tag", "onSubscribe");
                    }

                    @Override
                    public void onNext(ArtcileBean artcileBean) {
                        datasBeans.addAll(artcileBean.getData().getDatas());
                        articleAdapter.setDatasBeans(datasBeans);
                        articleAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("tag", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("tag", "onComplete");
                    }
                });
    }

    private void initView(View inflate) {
        article_rv = (RecyclerView) inflate.findViewById(R.id.article_rv);

        datasBeans = new ArrayList<>();
        articleAdapter = new ArticleAdapter(datasBeans, getContext());
        article_rv.setAdapter(articleAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        article_rv.setLayoutManager(linearLayoutManager);
        article_vp = (ViewPager) inflate.findViewById(R.id.article_vp);


        articleAdapter.setSetClick(new ArticleAdapter.setClick() {
            @Override
            public void setOnItem(ArtcileBean.DataBean.DatasBean artcileBean, int position) {
                Intent intent = new Intent(getContext(), Main3Activity.class);
                intent.putExtra("name",artcileBean.getLink());
                startActivity(intent);
            }
        });

    }
}
