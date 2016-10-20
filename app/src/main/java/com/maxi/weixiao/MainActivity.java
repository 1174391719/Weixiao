package com.maxi.weixiao;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;


import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    static final String TAG = "MainActivity";
    private ViewPager viewPager = null;

    private ViewpagedAdapter adapter = null;
    private TextView discovery = null;
    private TextView bookshelf = null;
    private TextView me = null;
    private LinearLayout discoveryLi = null;
    private LinearLayout bookshelfLi = null;
    private LinearLayout meLi = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("kkMainActivity", "onCreate11---kk:" + ActivityLogin.kk);
        ActivityLogin.kk++;
        Log.d("kkMainActivity", "onCreate22---kk:" + ActivityLogin.kk);


        viewPager = (ViewPager) findViewById(R.id.vp_main);
        discovery = (TextView) findViewById(R.id.tv_main_discovery);
        bookshelf = (TextView) findViewById(R.id.tv_main_bookshelf);
        me = (TextView) findViewById(R.id.tv_main_me);
        discoveryLi = (LinearLayout) findViewById(R.id.ll_main_discovery);
        bookshelfLi = (LinearLayout) findViewById(R.id.ll_main_bookshelf);
        meLi = (LinearLayout) findViewById(R.id.ll_main_me);
        discoveryLi.setOnClickListener(this);
        bookshelfLi.setOnClickListener(this);
        meLi.setOnClickListener(this);

        adapter = new ViewpagedAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                //Log.d("kk", "MainActivity/onPageSelected-------position:" + position);
                changeMenuColor(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });







        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "236");
        // 是否可以有多个快捷方式的副本，参数如果是true就可以生成多个快捷方式，如果是false就不会重复添加
        intent.putExtra("duplicate", false);

        Intent intent2 = new Intent(Intent.ACTION_MAIN);
        intent2.addCategory(Intent.CATEGORY_LAUNCHER);
        // 删除的应用程序的ComponentName，即应用程序包名+activity的名字
        intent2.setComponent(new ComponentName(this.getPackageName(), this.getPackageName() + ".ActivityMyNovel"));

        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent2);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(this,
                R.mipmap.head));
        sendBroadcast(intent);


        WindowManager wm = this.getWindowManager();

        Point p=new Point();

     wm.getDefaultDisplay().getSize(p);
        Constants.scrrenSize=p;

        Log.d("kk" + TAG, "onCreate-------width:"+p.x);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_main_discovery:
                viewPager.setCurrentItem(0, true);
                changeMenuColor(0);
                break;
            case R.id.ll_main_bookshelf:
                viewPager.setCurrentItem(1, true);
                changeMenuColor(1);
                break;
            case R.id.ll_main_me:
                viewPager.setCurrentItem(2, true);
                changeMenuColor(2);
                break;
            default:
                break;
        }
    }

    private void changeMenuColor(int position) {
        switch (position) {
            case 0:
                discovery.setTextColor(Color.parseColor("#1E90FF"));
                bookshelf.setTextColor(Color.parseColor("#575757"));
                me.setTextColor(Color.parseColor("#575757"));
                break;
            case 1:
                discovery.setTextColor(Color.parseColor("#575757"));
                bookshelf.setTextColor(Color.parseColor("#1E90FF"));
                me.setTextColor(Color.parseColor("#575757"));
                break;
            case 2:
                discovery.setTextColor(Color.parseColor("#575757"));
                bookshelf.setTextColor(Color.parseColor("#575757"));
                me.setTextColor(Color.parseColor("#1E90FF"));
                break;
            default:
                break;
        }
    }
}
