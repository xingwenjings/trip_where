package com.example.dashixun_zuoye.callbacks;

import com.example.dashixun_zuoye.beans.HomeBean;

import java.util.List;

public interface HomeCallBack {
    void getSuccessData(List<HomeBean.DataBean.DatasBean> homeBean);
    void getFieldData(String error);



}
