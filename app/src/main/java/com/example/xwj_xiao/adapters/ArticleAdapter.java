package com.example.xwj_xiao.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xwj_xiao.R;
import com.example.xwj_xiao.beans.ArtcileBean;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private ArrayList<ArtcileBean.DataBean.DatasBean> datasBeans;
    private Context context;

    public ArticleAdapter(ArrayList<ArtcileBean.DataBean.DatasBean> datasBeans, Context context) {
        this.datasBeans = datasBeans;
        this.context = context;
    }

    public void setDatasBeans(ArrayList<ArtcileBean.DataBean.DatasBean> datasBeans) {
        this.datasBeans = datasBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_article_adapter, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.article_name.setText(datasBeans.get(i).getTitle());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClick.setOnItem(datasBeans.get(i),i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datasBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView article_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            article_name=itemView.findViewById(R.id.article_name);
        }
    }
    private setClick setClick;

    public void setSetClick(ArticleAdapter.setClick setClick) {
        this.setClick = setClick;
    }

    public interface setClick{
        void setOnItem(ArtcileBean.DataBean.DatasBean artcileBean,int position);
    }
}
