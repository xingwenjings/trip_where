package com.example.dashixun_zuoye;

import com.example.dashixun_zuoye.beans.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MySeriver {
    public static final String getHomeUrl="https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Observable<HomeBean> getHome();
}
