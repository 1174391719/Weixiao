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
public class AdapterLibrary extends RecyclerView.Adapter<AdapterLibrary.MyViewHolder> implements View.OnClickListener {
    static final String TAG = "AdapterLibrary";
    Context fb = null;
    List<Novel> novelList = null;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
    }


    public AdapterLibrary(Context fb, List<Novel> nl) {
        Log.d("kk" + TAG, "AdapterLibrary----------");
        this.fb = fb;
        this.novelList = nl;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(
                fb).inflate(R.layout.recycleview_item_library, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
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

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_recycleitem_library);
            iv=(ImageView)view.findViewById(R.id.iv_recycleitem_library);
        }
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v);
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
