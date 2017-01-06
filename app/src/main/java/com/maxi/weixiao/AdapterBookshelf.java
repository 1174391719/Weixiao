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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingzhi.yuan on 3/12/16.
 */
public class AdapterBookshelf extends RecyclerView.Adapter<AdapterBookshelf.BasicViewHolder> {
    public static final int DEFAULT_MODE = 0;
    public static final int DELETE_MODE = 1;
    Context fb = null;
    List<BookshelfData> mData = new ArrayList<>();
    private AddBook mCallBack = null;


    public AdapterBookshelf(AddBook cb, Context fb, List<BookshelfData> novels) {
        mCallBack = cb;
        this.fb = fb;
        this.mData = novels;

    }

    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BasicViewHolder holder;
        switch (viewType) {
            case DEFAULT_MODE:
                holder = new DefaultViewHolder(LayoutInflater.from(
                        fb).inflate(R.layout.recycleview_item_bookshelt, parent,
                        false));
                break;
            case DELETE_MODE:
                holder = new DeleteViewHolder(LayoutInflater.from(
                        fb).inflate(R.layout.recycleview_item_bookshelt, parent,
                        false));
                break;
            default:
                holder = new BasicViewHolder(LayoutInflater.from(
                        fb).inflate(R.layout.recycleview_item_bookshelt, parent,
                        false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BasicViewHolder holder, int position) {
        holder.tv.setText(mData.get(position).getNovel().getTitle());
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getModel();
    }

    class DefaultViewHolder extends AdapterBookshelf.BasicViewHolder {
        public DefaultViewHolder(View view) {
            super(view);
            delete.setVisibility(View.GONE);
        }

    }

    class DeleteViewHolder extends AdapterBookshelf.BasicViewHolder {
        public DeleteViewHolder(View view) {
            super(view);
            delete.setVisibility(View.VISIBLE);
        }

    }

    class BasicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tv;
        ImageView delete;

        public BasicViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_recycleitem_bookshelf_title);
            delete = (ImageView) view.findViewById(R.id.iv_recycleItem_delete);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            mCallBack.addBook(getAdapterPosition(), mData.get(getAdapterPosition()).getNovel());
        }

        @Override
        public boolean onLongClick(View v) {
            FragmentBookshelf.setViewMode(mData, DELETE_MODE);

            notifyDataSetChanged();
            return true;
        }
    }


    public interface AddBook {
        void addBook(int position, Novel novel);
    }

    public static class BookshelfData {


        private int model = DEFAULT_MODE;
        private Novel novel = null;

        public BookshelfData(Novel no, int model) {
            this.novel = no;
            this.model = model;
        }

        public int getModel() {
            return model;
        }

        public void setModel(int mode) {
            model = mode;
        }

        public Novel getNovel() {
            return novel;
        }

        public void setNovel(Novel novel) {
            this.novel = novel;
        }


    }
}
