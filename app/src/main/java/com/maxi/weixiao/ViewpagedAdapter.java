package com.maxi.weixiao;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mingzhi.yuan on 1/23/16.
 */
public class ViewpagedAdapter extends FragmentPagerAdapter {

    public ViewpagedAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        Log.d("kk","ViewpagedAdapter/Fragment------position:"+position);

        switch (position) {
            case 0:
                return new FragmentDiscovery();

            case 1:
                return new FragmentBookshelf();
            case 2:
                return new FragmentMe();
            default:
                break;
//
        }
        return null;


    }

    public int getCount() {
        return 3;
    }

}
