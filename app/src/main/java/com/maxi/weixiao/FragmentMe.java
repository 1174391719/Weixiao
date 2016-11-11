package com.maxi.weixiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogRecord;

/**
 * Created by mingzhi.yuan on 1/23/16.
 */
public class FragmentMe extends Fragment {

 //   private LinearLayout myNovel = null;
    private ImageView iv = null;
    private TextView title = null;
    private Handler handler = null;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.me, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      //  myNovel = (LinearLayout) getActivity().findViewById(R.id.ll_me_mynovel);
        iv = (ImageView) getActivity().findViewById(R.id.iv_me);
        title = (TextView) getActivity().findViewById(R.id.tv_me_title);
       /* myNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityMyNovel.class));
            }
        });*/
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



             //   HttpHelp httpHelp = new HttpHelp();
          //      httpHelp.login(FragmentMe.this);
                startActivity(new Intent(FragmentMe.this.getActivity(),ActivityLogin.class));
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg){
                title.setText(msg.obj.toString());

            }

        };
    }


    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

  /*  @Override
    public void loginCallBack(String nick) {
        Message msg = new Message();
        msg.obj = nick;
        handler.sendMessage(msg);
        Log.d("kk", "nick44:" + nick);
        title.setText(nick);
        Log.d("kk", "nick333333333333:" + nick);


    }*/


}
