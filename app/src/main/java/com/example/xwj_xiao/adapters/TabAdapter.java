package com.example.xwj_xiao.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xwj_xiao.R;
import com.example.xwj_xiao.beans.TabBean;

import java.util.ArrayList;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.ViewHolder> {
    private ArrayList<TabBean.DataBean> tabBeans;
    private Context context;

    public TabAdapter(ArrayList<TabBean.DataBean> tabBeans, Context context) {
        this.tabBeans = tabBeans;
        this.context = context;
    }

    public void setTabBeans(ArrayList<TabBean.DataBean> tabBeans) {
        this.tabBeans = tabBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_tab, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tab_name.setText(tabBeans.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return tabBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tab_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tab_name=itemView.findViewById(R.id.tab_name);
        }
    }
}
