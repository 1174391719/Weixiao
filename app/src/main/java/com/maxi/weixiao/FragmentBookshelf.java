package com.maxi.weixiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingzhi.yuan on 1/23/16.
 */
public class FragmentBookshelf extends Fragment implements AdapterBookshelf.AddBook {

    static final String TAG = "FragmentBookshelf";
    private RecyclerView rv = null;
    private LinearLayout library = null;
    private List<Novel> novelList = null;
    AdapterBookshelf adapterBookshelf;
    private TextView none = null;
    public static FragmentBookshelf instants = null;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            none.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
            rv.setAdapter(new AdapterBookshelf(FragmentBookshelf.this, FragmentBookshelf.this.getActivity(), novelList));
            adapterBookshelf.notifyDataSetChanged();
            Log.d("kk" + TAG, "mHandler-------------:");
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookshelf, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);
        rv = (RecyclerView) getActivity().findViewById(R.id.rv_bookshelf);
        none = (TextView) getActivity().findViewById(R.id.tv_bookshell_none);
        rv.setLayoutManager((new GridLayoutManager(this.getActivity(), 4)));
        adapterBookshelf = new AdapterBookshelf(this, this.getActivity(), novelList);
        synchronized (adapterBookshelf) {
            adapterBookshelf.notify();
        }

        rv.setAdapter(adapterBookshelf);

        library = (LinearLayout) getActivity().findViewById(R.id.ll_bookshelf_library);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FragmentBookshelf.this.getActivity(), ActivityLibrary.class));
            }
        });
        instants = this;
    }


    public void onResume() {
        super.onResume();
        adapterBookshelf.novels = this.novelList;
        adapterBookshelf.notifyDataSetChanged();
        Log.d("kk" + TAG, "onResume-------------");

    }

    public void addBook(Novel no) {
        Log.d("kk" + TAG, "addBook-------------no:"+no);
        if (novelList == null) {
            novelList = new ArrayList<Novel>();
        }
        novelList.add(no);
        Message msg = new Message();
        mHandler.sendMessage(msg);


    }

    public List<Novel> getNovelList() {
        return novelList;
    }

    @Override
    public void addBook(int position, Novel novel) {
        if (none.getVisibility() == View.VISIBLE) {
            none.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
        getActivity().startActivity(new Intent(getActivity(), ActivityReading.class));
    }
}
