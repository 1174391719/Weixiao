package com.maxi.weixiao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingzhi.yuan on 1/23/16.
 */
public class FragmentDiscovery extends Fragment {
    private ViewPager viewPager = null;
    private ViewpagedAdapterDiscovery adapter = null;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //Log.d("kk", "FragmentDiscovery/onActivityCreated-------:");
        super.onActivityCreated(savedInstanceState);

        List<View> viewList = initView();

        viewPager = (ViewPager) getActivity().findViewById(R.id.vp_discovery);
        viewPager.setPageMargin(20);
        adapter = new ViewpagedAdapterDiscovery(viewList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Log.d("kk", "FragmentDiscovery/onPageSelected-------position:" + position);
                //changeMenuColor(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getActivity().findViewById(R.id.ll_discovery).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.discovery, null);
        return view;
    }

    public void onResume() {
        super.onResume();


    }

    private List<View> initView() {
        List<View> listView = new ArrayList<View>();
        LayoutInflater lf = getActivity().getLayoutInflater().from(getActivity());
        for (int i = 0; i < 5; i++) {
            View layout = lf.inflate(R.layout.discovery_view, null);
            TextView textView = (TextView) layout.findViewById(R.id.tv_discovery_author);
            textView.setText("fdg" + i);
            listView.add(layout);

        }
        return listView;
    }


}
