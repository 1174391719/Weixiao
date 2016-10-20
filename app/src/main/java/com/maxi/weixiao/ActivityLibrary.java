package com.maxi.weixiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityLibrary extends FragmentActivity implements NetInterface.InterfaceGetLibrary{
    static final String TAG = "ActivityLibrary";
    private RecyclerView rv=null;
    private List<Novel> novelList=null;
    private AdapterLibrary mAdapter=null;


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Log.d("kk" + TAG, "handleMessage----------novelList.size:"+novelList.size());
            mAdapter.novelList=novelList;
            mAdapter.notifyDataSetChanged();
          //  mAdapter.notifyItemInserted(0);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);


        rv=(RecyclerView)findViewById(R.id.rv_library);
        mAdapter=new AdapterLibrary(this,novelList);
        // rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rv.setLayoutManager(new GridLayoutManager(this,4));
        rv.setAdapter(mAdapter);
    //    startActivity(new Intent(ActivityLibrary.this, turntest.class))
        mAdapter.setOnItemClickListener(new AdapterLibrary.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view ){
                Toast.makeText(ActivityLibrary.this, ((Novel)(view.getTag())).getTitle(), Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                bundle.putSerializable("novel",(Novel)(view.getTag()));
                Intent intent=new Intent(ActivityLibrary.this,ActivityNovelDetail.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });



        Map map = new HashMap();
        map.put("key", "2");
        HttpHelp hh = new HttpHelp();
        hh.getLibrary(this, map);


    }
    public void getLibraly(List<Novel> list){
        Log.d("kk" + TAG, "getLibraly-------novelList.size():" + list.size());
        novelList=list;
        Message msg=new Message();
        mHandler.sendMessage(msg);


    }

}
