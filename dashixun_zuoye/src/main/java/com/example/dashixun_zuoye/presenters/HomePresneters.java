package com.example.dashixun_zuoye.presenters;

import com.example.dashixun_zuoye.beans.HomeBean;
import com.example.dashixun_zuoye.callbacks.HomeCallBack;
import com.example.dashixun_zuoye.models.HomeModel;
import com.example.dashixun_zuoye.models.HomeModels;
import com.example.dashixun_zuoye.views.HomeViews;

import java.util.List;

public class HomePresneters implements HomePresenter{
    HomeModel homeModel;
    HomeViews homeViews;
    public HomePresneters(HomeViews homeViews) {
        homeModel=new HomeModels();
        this.homeViews=homeViews;
    }

    @Override
    public void getData() {
        homeModel.setData(new HomeCallBack() {
            @Override
            public void getSuccessData(List<HomeBean.DataBean.DatasBean> homeBean) {
                homeViews.getSuccessData(homeBean);
            }

            @Override
            public void getFieldData(String error) {
                homeViews.getFieldData(error);
            }
        });
    }
}
