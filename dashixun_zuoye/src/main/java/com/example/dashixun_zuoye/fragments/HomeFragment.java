package com.example.dashixun_zuoye.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dashixun_zuoye.R;
import com.example.dashixun_zuoye.adapters.HomeAdapter;
import com.example.dashixun_zuoye.adapters.RvHomeAdapter;
import com.example.dashixun_zuoye.beans.HomeBean;
import com.example.dashixun_zuoye.presenters.HomePresneters;
import com.example.dashixun_zuoye.views.HomeViews;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeViews {


    private RecyclerView home_rv;
    private ArrayList<HomeBean.DataBean.DatasBean> homeBean;
    private RvHomeAdapter rvHomeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initPresenter();
        return inflate;
    }

    private void initPresenter() {
        HomePresneters homePresneters = new HomePresneters(this);
        homePresneters.getData();
    }

    private void initView(View inflate) {
        home_rv = (RecyclerView) inflate.findViewById(R.id.home_rv);

        homeBean = new ArrayList<>();
        rvHomeAdapter = new RvHomeAdapter(homeBean, getContext());
        home_rv.setAdapter(rvHomeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        home_rv.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void getFieldData(String error) {
        Log.i("tag","getFieldData"+error);
    }

    @Override
    public void getSuccessData(List<HomeBean.DataBean.DatasBean> homeBeans) {
        homeBean.addAll(homeBeans);
        rvHomeAdapter.setHomeBean(homeBean);
        rvHomeAdapter.notifyDataSetChanged();
    }
}
