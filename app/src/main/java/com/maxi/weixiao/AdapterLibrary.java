package com.maxi.weixiao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingzhi.yuan on 3/12/16.
 */
public class AdapterLibrary extends RecyclerView.Adapter<AdapterLibrary.MyViewHolder> {
    static final String TAG = "AdapterLibrary";
    Context fb = null;
    List<Novel> novelList = null;
    private LibraryInterface mCallBack = null;

    public AdapterLibrary(LibraryInterface li, Context fb, List<Novel> nl) {
        Log.d("kk" + TAG, "AdapterLibrary----------");
        this.mCallBack = li;

        this.fb = fb;
        this.novelList = nl;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(
                fb).inflate(R.layout.recycleview_item_library, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(novelList.get(position).getTitle());
        holder.itemView.setTag(novelList.get(position));


    }

    @Override
    public int getItemCount() {
        return novelList != null ? novelList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv;
        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_recycleitem_library);
            iv = (ImageView) view.findViewById(R.id.iv_recycleitem_library);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            mCallBack.selectItem(getAdapterPosition(), novelList.get(getAdapterPosition()));

        }
    }


    interface LibraryInterface {
        void selectItem(int position, Novel novel);
    }

}
