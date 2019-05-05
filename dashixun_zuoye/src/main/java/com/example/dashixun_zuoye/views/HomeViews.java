package com.example.dashixun_zuoye.views;

import com.example.dashixun_zuoye.beans.HomeBean;

import java.util.List;

public interface HomeViews {

    void getFieldData(String error);

    void getSuccessData(List<HomeBean.DataBean.DatasBean> homeBean);
}
