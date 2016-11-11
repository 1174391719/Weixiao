package com.maxi.weixiao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import maxi_160514.tools.Tools;

public class ActivityNovelDetail extends Activity {
    static final String TAG = "ActivityNovelDetail";

    private Novel mNovel = null;
    private TextView mTitle = null;
    private TextView mAuthor = null;
    private TextView mBrief = null;
    private TextView mAdd = null;
    private TextView mRead = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_detail);

        mNovel = (Novel) getIntent().getSerializableExtra("novel");
        mTitle = (TextView) findViewById(R.id.tv_novel_detail_title);
        mAuthor = (TextView) findViewById(R.id.tv_novel_detail_author);
        mBrief = (TextView) findViewById(R.id.tv_novel_detail_brief);
        mAdd = (TextView) findViewById(R.id.tv_novel_detail_add);
        mRead = (TextView) findViewById(R.id.tv_novel_detail_read);
        mTitle.setText(mNovel.getTitle());
        mAuthor.setText(mNovel.getAuthor());
        mBrief.setText(mNovel.getBrief());

        if (FragmentBookshelf.instants.getNovelList() != null && mNovel != null) {
            for (int i = FragmentBookshelf.instants.getNovelList().size() - 1; i >= 0; i--) {
                if (mNovel.getId().equals(FragmentBookshelf.instants.getNovelList().get(i).getId())) {
                    mAdd.setText("已加入书架");
                    mAdd.setTextColor(Color.parseColor("#501E90FF"));
                }
            }


        }

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentBookshelf.instants.addBook(mNovel);

            }
        });
        mRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityNovelDetail.this, ActivityReading.class));
            }
        });

    }


}
