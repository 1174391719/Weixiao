package com.maxi.weixiao;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityReading extends Activity implements NovelHelper.GetParts, NovelHelper.GetSections {

    public final static int UPDATE_CONTENT = 0;
    private TextView content = null;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case UPDATE_CONTENT:
                    List<Section> sections = (List<Section>) msg.obj;
                    content.setText(sections.get(0).getContent());
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        content = (TextView) findViewById(R.id.tv_reading_content);

        NovelHelper.getInstance().getParts(this);
    }


    public void getParts(List<Part> parts) {
        NovelHelper.getInstance().getSections(this, parts.get(0).getNovelId(), parts.get(0).getPartPosition());
    }

    public void getSections(List<Section> sections) {
        Log.d("kk", "getSections:" + sections.get(0).getContent());
        Message msg = new Message();
        msg.arg1 = UPDATE_CONTENT;
        msg.obj = sections;
        mHandler.sendMessage(msg);

    }

}
