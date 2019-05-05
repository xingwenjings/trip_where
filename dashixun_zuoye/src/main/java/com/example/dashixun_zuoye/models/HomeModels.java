package com.example.dashixun_zuoye.models;

import android.util.Log;

import com.example.dashixun_zuoye.MySeriver;
import com.example.dashixun_zuoye.beans.HomeBean;
import com.example.dashixun_zuoye.callbacks.HomeCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModels implements HomeModel{
    @Override
    public void setData(final HomeCallBack homeCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MySeriver.getHomeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MySeriver mySeriver = retrofit.create(MySeriver.class);
        Observable<HomeBean> home = mySeriver.getHome();
        home.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("tag","onSubscribe");
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        Log.i("tag","onNext"+homeBean.getData().getDatas());
                        homeCallBack.getSuccessData(homeBean.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("tag","onError");
                        homeCallBack.getFieldData(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("tag","onComplete");
                    }
                });
    }
}
