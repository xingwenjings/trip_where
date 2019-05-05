package com.example.kaoshi_a.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaoshi_a.R;
import com.example.kaoshi_a.adapters.ArticleAdapter;
import com.example.kaoshi_a.beans.DatasBean;
import com.example.kaoshi_a.dbUtils.DbUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {


    private RecyclerView collect_rv;
    private ArrayList<DatasBean> datasBeans=new ArrayList<>();
    private ArticleAdapter articleAdapter;

    public CollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_collect, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            initData();
        }else {
            datasBeans.clear();
        }
    }

    private void initData() {
        List<DatasBean> datasBean = DbUtils.queryList();
        datasBeans.addAll(datasBean);
        articleAdapter.setList(datasBeans);
        articleAdapter.notifyDataSetChanged();
    }

    private void initView(View inflate) {
        collect_rv = (RecyclerView) inflate.findViewById(R.id.collect_rv);


        articleAdapter = new ArticleAdapter(datasBeans, getContext());
        collect_rv.setAdapter(articleAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        collect_rv.setLayoutManager(gridLayoutManager);
    }
}
