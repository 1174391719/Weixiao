package com.maxi.weixiao;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private AddBook mCallBack = null;


    public AdapterBookshelf(AddBook cb, Context fb, List<Novel> novels) {
        mCallBack = cb;
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
        if (novels == null)
            return 0;
        return novels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_recycleitem_bookshelf_title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCallBack.addBook(getAdapterPosition(), novels.get(getAdapterPosition()));
        }
    }


    public interface AddBook {
        void addBook(int position, Novel novel);
    }
}
