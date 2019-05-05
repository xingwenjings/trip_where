package com.example.dashixun_zuoye;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface MyApi {
    public static final String url="http://cdn.banmi.com/banmiapp/";
    @GET("apk/banmi_330.apk")
    Observable<ResponseBody> getData();
}
