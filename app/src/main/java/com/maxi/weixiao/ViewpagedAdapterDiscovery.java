package com.maxi.weixiao;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mingzhi.yuan on 1/23/16.
 */
public class ViewpagedAdapterDiscovery extends PagerAdapter {

    private List<View> mListViews;
    static final String TAG = "ViewpagedAdapter";


    public ViewpagedAdapterDiscovery(List<View> mListViews) {
        this.mListViews = mListViews;//构造方法，参数是我们的页卡，这样比较方便。
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Log.d("kk" + TAG, "destroyItem position:" + position);
        //container.removeView(mListViews.get(position));//删除页卡
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        View v = container.getChildAt(position);
        // Log.d("kk" + TAG, "instantiateItem position:" + position + " v:" + v);
        if (v == null) {
            container.addView(mListViews.get(position), position);//添加页卡
        }

        return mListViews.get(position);
    }

    @Override
    public int getCount() {
        return mListViews.size();//返回页卡的数量
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;//官方提示这样写
    }

}
