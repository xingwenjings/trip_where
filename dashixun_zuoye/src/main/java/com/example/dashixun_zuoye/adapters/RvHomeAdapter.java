package com.example.dashixun_zuoye.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.dashixun_zuoye.R;
import com.example.dashixun_zuoye.beans.HomeBean;

import java.util.ArrayList;

public class RvHomeAdapter extends RecyclerView.Adapter<RvHomeAdapter.ViewHolder> {
    private ArrayList<HomeBean.DataBean.DatasBean> homeBean;
    private Context context;

    public RvHomeAdapter(ArrayList<HomeBean.DataBean.DatasBean> homeBean, Context context) {
        this.homeBean = homeBean;
        this.context = context;
    }

    public void setHomeBean(ArrayList<HomeBean.DataBean.DatasBean> homeBean) {
        this.homeBean = homeBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_home_adapter, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.home_name.setText(homeBean.get(i).getTitle());
        RoundedCorners roundedCorners = new RoundedCorners(8);
        Glide.with(context).load(homeBean.get(i).getEnvelopePic()).apply( RequestOptions.bitmapTransform(roundedCorners)).into(viewHolder.home_img);
    }

    @Override
    public int getItemCount() {
        return homeBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView home_img;
        private TextView home_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_img=itemView.findViewById(R.id.home_img);
            home_name=itemView.findViewById(R.id.home_name);
        }
    }
}
