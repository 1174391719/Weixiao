package com.maxi.weixiao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingzhi.yuan on 3/12/16.
 */
public class AdapterBookshelf extends RecyclerView.Adapter<AdapterBookshelf.MyViewHolder> {
    Context fb = null;
    List<Novel> novels = new ArrayList<>();


    public AdapterBookshelf(Context fb, List<Novel> novels) {
        this.fb = fb;
        this.novels = novels;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                fb).inflate(R.layout.recycleview_item_bookshelt, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(novels.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(novels==null)
            return 0;
        return novels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_recycleitem_bookshelf_title);
        }
    }
}
