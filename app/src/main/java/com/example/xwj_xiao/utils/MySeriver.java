package com.example.xwj_xiao.utils;

import com.example.xwj_xiao.beans.ArtcileBean;
import com.example.xwj_xiao.beans.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MySeriver {
    public static final String getUrlTab="https://www.wanandroid.com/";
    @GET("project/tree/json")
    Observable<TabBean> getUrl();


    public static final String getArticle="https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Observable<ArtcileBean> getUrlArticel();
}
