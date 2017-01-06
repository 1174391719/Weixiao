package com.maxi.weixiao;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;


import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.maxi.weixiao.R.color.mainScreen_button_text_default;
import static com.maxi.weixiao.R.color.mainScreen_button_text_select;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    static final String TAG = "MainActivity";
    private ViewPager viewPager = null;

    private ViewpagedAdapter adapter = null;
    private TextView tvDiscovery = null;
    private TextView tvBookshelf = null;
    private TextView tvMe = null;
    private LinearLayout discoveryLi = null;
    private LinearLayout bookshelfLi = null;
    private LinearLayout meLi = null;

    private VectorDrawable vdDiscovery;
    private VectorDrawable vdBookshelf;
    private VectorDrawable vdMe;
    private ImageView ivDiscovery;
    private ImageView ivBookshelf;
    private ImageView ivMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        ActivityLogin.kk++;

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

        Point p = new Point();

        wm.getDefaultDisplay().getSize(p);
        Constants.scrrenSize = p;
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_main);

        tvDiscovery = (TextView) findViewById(R.id.tv_main_discovery);
        ivDiscovery = (ImageView) findViewById(R.id.iv_main_discovery);
        discoveryLi = (LinearLayout) findViewById(R.id.ll_main_discovery);
        vdDiscovery = (VectorDrawable) getResources().getDrawable(R.drawable.discovery);
        setDiscoveryMode(false);

        tvBookshelf = (TextView) findViewById(R.id.tv_main_bookshelf);
        ivBookshelf = (ImageView) findViewById(R.id.iv_main_bookshelf);
        bookshelfLi = (LinearLayout) findViewById(R.id.ll_main_bookshelf);
        vdBookshelf = (VectorDrawable) getResources().getDrawable(R.drawable.bookshef);
        setBookshelfMode(true);

        tvMe = (TextView) findViewById(R.id.tv_main_me);
        ivMe = (ImageView) findViewById(R.id.iv_main_me);
        meLi = (LinearLayout) findViewById(R.id.ll_main_me);
        vdMe = (VectorDrawable) getResources().getDrawable(R.drawable.me);
        setMeMode(true);
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
                setDiscoveryMode(false);
                setBookshelfMode(true);
                setMeMode(true);
                break;
            case 1:
                setDiscoveryMode(true);
                setBookshelfMode(false);
                setMeMode(true);
                break;
            case 2:
                setDiscoveryMode(true);
                setBookshelfMode(true);
                setMeMode(false);
                break;
            default:
                break;
        }
    }

    private void setDiscoveryMode(boolean isDefault) {
        if (isDefault) {
            tvDiscovery.setTextColor(this.getResources().getColor(mainScreen_button_text_default));
            vdDiscovery.setTint(getResources().getColor(R.color.mainScreen_button_text_default));
        } else {
            tvDiscovery.setTextColor(this.getResources().getColor(mainScreen_button_text_select));
            vdDiscovery.setTint(getResources().getColor(R.color.mainScreen_button_text_select));
        }
        ivDiscovery.setImageDrawable(vdDiscovery);
    }

    private void setBookshelfMode(boolean isDefault) {
        if (isDefault) {
            tvBookshelf.setTextColor(this.getResources().getColor(mainScreen_button_text_default));
            vdBookshelf.setTint(getResources().getColor(R.color.mainScreen_button_text_default));
        } else {
            tvBookshelf.setTextColor(this.getResources().getColor(mainScreen_button_text_select));
            vdBookshelf.setTint(getResources().getColor(mainScreen_button_text_select));
        }
        ivBookshelf.setImageDrawable(vdBookshelf);
    }

    private void setMeMode(boolean isDefault) {
        if (isDefault) {
            tvMe.setTextColor(this.getResources().getColor(mainScreen_button_text_default));
            vdMe.setTint(getResources().getColor(R.color.mainScreen_button_text_default));
        } else {
            tvMe.setTextColor(this.getResources().getColor(mainScreen_button_text_select));
            vdMe.setTint(getResources().getColor(R.color.mainScreen_button_text_select));
        }
        ivMe.setImageDrawable(vdMe);
    }

    @Override
    public void onBackPressed() {

    }
}
