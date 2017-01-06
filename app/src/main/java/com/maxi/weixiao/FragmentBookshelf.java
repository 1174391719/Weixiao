package com.maxi.weixiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
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
    private RecyclerView mRecyclerView = null;
    private LinearLayout library = null;
    private List<Novel> novelList = null;
    private List<AdapterBookshelf.BookshelfData> mData = null;
    AdapterBookshelf adapterBookshelf;
    private TextView none = null;
    public static FragmentBookshelf instants = null;
    public static final int ENTER_DEFAULT_MODE = 0;
    public static final int UPDATA_VIEW = 1;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {
                case UPDATA_VIEW:
                    none.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    adapterBookshelf.notifyDataSetChanged();
                    Log.d("kk" + TAG, "mHandler-------------:");
                    break;
                case ENTER_DEFAULT_MODE:
                    Log.d("kk", "handleMessage ENTER_DEFAULT_MODE");
                    //    mRecyclerView.setVisibility(View.VISIBLE);
                    //    mRecyclerView.setAdapter(new AdapterBookshelf(FragmentBookshelf.this, FragmentBookshelf.this.getActivity(), novelList));

                    break;
                default:
                    break;
            }

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
        mData = new ArrayList<>();
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_bookshelf);
        none = (TextView) getActivity().findViewById(R.id.tv_bookshell_none);
        mRecyclerView.setLayoutManager((new GridLayoutManager(this.getActivity(), 4)));
        adapterBookshelf = new AdapterBookshelf(this, this.getActivity(), mData);
        synchronized (adapterBookshelf) {
            adapterBookshelf.notify();
        }

        mRecyclerView.setAdapter(adapterBookshelf);

        library = (LinearLayout) getActivity().findViewById(R.id.ll_bookshelf_library);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FragmentBookshelf.this.getActivity(), ActivityLibrary.class));
            }
        });
        instants = this;

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    setViewMode(mData, AdapterBookshelf.DEFAULT_MODE);
                    adapterBookshelf.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });


    }


    public void onResume() {
        super.onResume();

    }

    public void addBook(Novel no) {
        Log.d("kk" + TAG, "addBook-------------no:" + no);

        mData.add(new AdapterBookshelf.BookshelfData(no, AdapterBookshelf.DEFAULT_MODE));
        Message msg = new Message();
        msg.arg1 = UPDATA_VIEW;

        mHandler.sendMessage(msg);
    }

    public List<Novel> getNovelList() {
        return novelList;
    }

    @Override
    public void addBook(int position, Novel novel) {
        if (none.getVisibility() == View.VISIBLE) {
            none.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
        getActivity().startActivity(new Intent(getActivity(), ActivityReading.class));
    }


    public static void setViewMode(List<AdapterBookshelf.BookshelfData> data, int model) {
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setModel(model);
            }
        }
    }
}
