package com.maxi.weixiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
public class FragmentBookshelf extends Fragment implements NetInterface.Bookshelf{

    static final String TAG="FragmentBookshelf";
    private RecyclerView rv = null;
    private LinearLayout library = null;
    private List<Novel> novelList=null;
    AdapterBookshelf adapterBookshelf;
    private TextView none=null;

    public static FragmentBookshelf instants=null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bookshelf, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);


        rv = (RecyclerView) getActivity().findViewById(R.id.rv_bookshelf);
        none=(TextView)getActivity().findViewById(R.id.tv_bookshell_none);




        // rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rv.setLayoutManager((new GridLayoutManager(this.getActivity(), 4)));

        adapterBookshelf=new AdapterBookshelf(this.getActivity(), novelList);
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
        instants=this;


     //   HttpHelp httpHelp=new HttpHelp();
     //   httpHelp.getBookShelf(FragmentBookshelf.this);
    }

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            rv.setAdapter(new AdapterBookshelf(FragmentBookshelf.this.getActivity(), novelList));

        //    synchronized (adapterBookshelf) {

                adapterBookshelf.notifyDataSetChanged();

         //   }

        }
    };

    public void onResume() {
        super.onResume();
        if(novelList==null){
            none.setVisibility(View.VISIBLE);
        }else {
            none.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
        adapterBookshelf.novels=this.novelList;
        adapterBookshelf.notifyDataSetChanged();

    }

    @Override
    public void callBack(List<Novel> list){
        novelList=list;
        Message msg=new Message();
        handler.sendMessage(msg);

    }
    public void addBook(Novel no){
        Log.d("kk"+TAG,"addBook-------------");
        if(novelList==null){
            novelList=new ArrayList<Novel>();
        }
        novelList.add(no);
    }
    public List<Novel> getNovelList(){
        return novelList;
    }

}
