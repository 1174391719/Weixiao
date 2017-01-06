package com.maxi.weixiao;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import maxi_160514.custom.RecyclePageView;

public class ActivityReading extends Activity {

    static String TAG = "ActivityReading";

    private Bundle mBundle = null;

    private ReadController mReadController = null;
    WindowManager wm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reading);

        wm = this.getWindowManager();
        LinearLayout layout = (LinearLayout) findViewById(R.id.temp);
        ReadView temp = (ReadView) layout.findViewById(R.id.tv);
        mBundle = getIntent().getBundleExtra("ACTIVITY_READING_BUNDLE");

        if (mBundle != null) {
            mReadController = new ReadController(this, temp, mBundle.getInt("ACTIVITY_READING_NOVELID", 0),
                    wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getHeight());
        }

        //Set recyclePageView


    }
}
