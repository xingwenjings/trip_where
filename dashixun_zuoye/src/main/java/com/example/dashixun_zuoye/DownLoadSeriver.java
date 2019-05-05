package com.example.dashixun_zuoye;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class DownLoadSeriver extends Service {
    String URL = "http://cdn.banmi.com/banmiapp/apk/banmi_330.apk";
    private static final String TAG = "MyServer";

    /**
     * 绑定服务时才会调用
     * 必须要实现的方法
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new XiaZai();
    }

    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);


    }

    /**
     * 服务销毁时的回调
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class XiaZai extends Binder {
        String s = Environment.getExternalStorageDirectory() + File.separator;
        String substring = URL.substring(URL.lastIndexOf("/") + 1);
        File file = new File(s + substring);


        public void getDem(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MyApi.url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            MyApi myApi = retrofit.create(MyApi.class);
            Observable<ResponseBody> data = myApi.getData();
            data.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(final ResponseBody responseBody) {
                            new Thread(){
                                @Override
                                public void run() {
                                    InputStream inputStream = responseBody.byteStream();
                                    int length = (int) responseBody.contentLength();

                                    try {
                                        sss(inputStream, length);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }.start();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        private void sss(InputStream inputStream, int length) throws IOException {
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int readLength = 0;
            int currloadSize = 0;
            while ((readLength = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, readLength);
                currloadSize += readLength;
                int i = (currloadSize * 100) / length;
                Message message = new Message();
                message.obj = i;
                message.what = 1;
                MainActivity.uploadFragment.handler.sendMessage(message);
            }
            inputStream.close();
            outputStream.close();
        }

    }
}

