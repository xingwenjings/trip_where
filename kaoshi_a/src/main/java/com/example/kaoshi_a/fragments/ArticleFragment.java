package com.example.kaoshi_a.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kaoshi_a.R;
import com.example.kaoshi_a.WebActivity;
import com.example.kaoshi_a.adapters.ArticleAdapter;
import com.example.kaoshi_a.beans.ArticleBean;
import com.example.kaoshi_a.beans.DatasBean;
import com.example.kaoshi_a.dbUtils.DbUtils;
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
@SuppressLint("ValidFragment")
public class ArticleFragment extends Fragment {


    private RecyclerView article_rv;
    private ArrayList<DatasBean> list;
    private ArticleAdapter articleAdapter;
    private int page;

    public ArticleFragment(int id) {
        Log.i("id--------","id"+id);
        // Required empty public constructor
        this.page=id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_article, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MySeriver.getArticleUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MySeriver mySeriver = retrofit.create(MySeriver.class);
        Observable<ArticleBean> article = mySeriver.getArticle(page);
        article.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("tag","onSubscribe");
                    }

                    @Override
                    public void onNext(ArticleBean articleBean) {
                        Log.i("tag","onNext");
                        list.addAll(articleBean.getData().getDatas());
                        articleAdapter.setList(list);
                        articleAdapter.notifyDataSetChanged();
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

    private void initView(final View inflate) {
        article_rv = (RecyclerView) inflate.findViewById(R.id.article_rv);

        list = new ArrayList<>();
        articleAdapter = new ArticleAdapter(list, getContext());
        article_rv.setAdapter(articleAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        article_rv.setLayoutManager(linearLayoutManager);
        articleAdapter.setSetItem(new ArticleAdapter.setItem() {
            @Override
            public void setOnClick(DatasBean list, int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("name",list.getLink());
                startActivity(intent);
            }
        });

    }
}
