package com.example.kaoshi_a.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kaoshi_a.R;
import com.example.kaoshi_a.beans.ArticleBean;
import com.example.kaoshi_a.beans.DatasBean;
import com.example.kaoshi_a.dbUtils.DbUtils;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private ArrayList<DatasBean> list;
    private Context context;

    public ArticleAdapter(ArrayList<DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<DatasBean> list) {
        this.list = list;
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
        viewHolder.article_adapter_name.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getEnvelopePic()).into(viewHolder.article_adapter_img);
        ImageView xing = viewHolder.itemView.findViewById(R.id.xing);
        xing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setItem.setOnClick(list.get(i),i);
                DbUtils.insert(list.get(i));
                Log.i("adapter","插入成功");
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItem.setOnClick(list.get(i),i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView article_adapter_img;
        private TextView article_adapter_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            article_adapter_img=itemView.findViewById(R.id.article_adapter_img);
            article_adapter_name=itemView.findViewById(R.id.article_adapter_name);
        }
    }
    private setItem setItem;

    public void setSetItem(ArticleAdapter.setItem setItem) {
        this.setItem = setItem;
    }

    public interface setItem{
        void setOnClick(DatasBean list,int position);
    }
}
