package com.maxi.weixiao;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import maxi_160514.custom.RecyclePageView;

/**
 * Created by mingzhi.yuan on 11/23/16.
 */

public class ReadController implements NovelHelper.GetParts, NovelHelper.GetSections,
        NovelHelper.GetSection, RecyclePageView.SlidePageCallBack {

    static String TAG = "ReadController";
    static boolean debug = true;
    private int[] currenReadLocation = new int[2];
    private Novel mNovel = null;
    public final static int UPDATE_CONTENT = 0;
    public final static int ADD_CONTENT = 1;
    private RecyclePageView mPageViwe = null;
    private ReadView tempView = null;
    private List<String> mData = null;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case UPDATE_CONTENT:
                    Section section = (Section) msg.obj;
                    currenReadLocation[0] = section.getPartPosition();
                    currenReadLocation[1] = section.getSectionPosition();
                    tempView.setText(section.getContent());
                    Log.d("kk" + TAG, "Handler--getPartPosition:" + section.getPartPosition() +
                            " getSectionPosition:" + section.getSectionPosition());

                    List<String> list = string2List(section.getContent(), tempView);
                    for (int i = 0; i < list.size(); i++) {
                        mData.add(list.get(i));
                    }
                    mPageViwe.updatePage();

                    break;
                case ADD_CONTENT:
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public ReadController(Context context, ReadView temp, int novelId, int width, int height) {
        mNovel = new Novel();
        mNovel.setId(novelId);
        mData = new ArrayList<>();
        tempView = temp;
        mPageViwe = (RecyclePageView) ((ActivityReading) context).findViewById(R.id.pv_reading);
        mPageViwe.init(context, this, width, height);
        mPageViwe.setBackgroundColor(Color.parseColor("#EED5D2"));
        mPageViwe.setData(mData, 0);


        NovelHelper.getInstance().getParts1(this, novelId);
    }

    public List<String> string2List(String str, ReadView temp) {
        //  Log.d("kk", "string2List--1---str:" + str.length());
        BufferedReader bufferedReader;
        CharBuffer charBuffer = CharBuffer.allocate(11000);
        try {
            InputStream in = new ByteArrayInputStream(str.getBytes());
            Charset charset = CharsetDetector.detect(in);
            if (charset != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(in, charset));
                bufferedReader.read(charBuffer);
                //      Log.d("kk", "string2List--2---charBuffer.length():" + charBuffer.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (charBuffer != null) {
            List<String> list = new ArrayList<>();
            int len = str.length();
            int position = 0;
            int charCount = 0;

            while (position < len) {
                charBuffer.position(position);
                temp.setText(charBuffer);
                charCount = temp.getCharNum();
                //     Log.d("kk", "string2List--3---position:" + position + " charCount:" + charCount);
                list.add(charBuffer.subSequence(0, charCount).toString());
                position = position + charCount;

            }

            return list;
        }
        return null;
    }

    @Override
    public void getPartsSuccess(List<Part> parts) {
        Log.d("kk" + TAG, "getPartsSuccess-----parts.size:" + parts.size());
        mNovel.setParts(parts);
        NovelHelper.getInstance().getSections(this, parts.get(0).getNovelId(),
                parts.get(0).getPartPosition());
    }

    @Override
    public void getSectionsSuccess(List<Section> sections) {
        Log.d("kk" + TAG, "getSectionsSuccess-----sections.size:" + sections.size());
        mNovel.getParts().get(0).setSections(sections);
        NovelHelper.getInstance().getSection(this, sections.get(0).getNovelId(),
                sections.get(0).getPartPosition(), sections.get(0).getSectionPosition());

    }

    @Override
    public void getSectionSuccess(Section section) {
        Log.d("kk" + TAG, "getSectionsSuccess-----getPartPosition:" + section.getPartPosition() +
                " getSectionPosition:" + section.getSectionPosition());
        mNovel.getParts().get(section.getPartPosition()).getSections().
                get(section.getSectionPosition()).setContent(section.getContent());
        Message msg = new Message();
        msg.arg1 = UPDATE_CONTENT;
        msg.obj = section;
        mHandler.sendMessage(msg);

    }

    @Override
    public void pageEnd(int len, int curren) {
        Log.d("kk" + TAG, "pageEnd-----spageEnd:" + len + " curren:" + curren);
        if (curren >= len - 2) {
            //Try to get next section
            if (currenReadLocation[1] + 1 < mNovel.getParts().get(currenReadLocation[0]).getSections().size()) {
                NovelHelper.getInstance().getSection(this, mNovel.getId(), currenReadLocation[0], currenReadLocation[1] + 1);
            }
        }
    }
}
