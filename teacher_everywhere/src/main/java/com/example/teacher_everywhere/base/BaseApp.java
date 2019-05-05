package com.example.teacher_everywhere.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class BaseApp extends Application {

    private static BaseApp sBaseApp;
    public static int mWidthPixels;
    public static int mHeightPixels;

    @Override
    public void onCreate() {
        super.onCreate();
        sBaseApp = this;
        getScreenWH();
    }

    //计算屏幕宽高
    private void getScreenWH() {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
    }

    public static BaseApp getInstance(){
        return sBaseApp;
    }

    public static Resources getRes() {
        return sBaseApp.getResources();
    }
}
