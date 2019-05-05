package com.example.kaoshi_a.utils;

import com.example.kaoshi_a.beans.ArticleBean;
import com.example.kaoshi_a.beans.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MySeriver {
    public static final String getUrl="http://www.wanandroid.com/";
    @GET("tree/json")
    Observable<TabBean> getTab();


    public static final String getArticleUrl="https://www.wanandroid.com/project/list/1/";
    @GET("json?")
    Observable<ArticleBean>  getArticle(@Query("cid") int page);
}
