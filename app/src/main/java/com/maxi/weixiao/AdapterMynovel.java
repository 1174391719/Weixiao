package com.maxi.weixiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mingzhi.yuan on 2/4/16.
 */
public class AdapterMynovel extends BaseAdapter{
    private Context c = null;
    private LayoutInflater myInflater = null;

    public AdapterMynovel(Context myContext) {
        c = myContext;

    }

    public int getCount() {
        return 2;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();


        if (convertView == null) {
            myInflater = LayoutInflater.from(c);
            convertView = myInflater.inflate(R.layout.listviewitem_mynovel, null);

            holder.title = (TextView) convertView.findViewById(R.id.tv_mynovel_title);
            holder.brief = (TextView) convertView.findViewById(R.id.tv_mynovel_brief);
            holder.iv=(ImageView)convertView.findViewById(R.id.iv_listitem_mynovel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == 0) {
            holder.title.setText("天下兄弟");
            holder.brief.setText("该剧讲述了一对双胞胎兄弟一出生便被分开，一个在" +
                    "农村土生土长，一个进入高级军官家庭。两人阴差阳错成为对手，甚至...");

        }
        if (position == 1) {
            holder.title.setText("幸福像花样灿烂");
            holder.brief.setText("该剧讲述了杜娟与林彬的一段初恋情感没有" +
                    "经受住风雨的考验，最终杜娟嫁给了高干子弟白杨，两人不成" +
                    "熟的婚姻经受困惑，杜娟在平淡的婚姻生活中始" +
                    "终如一地坚守心中的原则");
        }


        return convertView;
    }
    private class ViewHolder {
        ImageView iv;
        TextView title;
        TextView brief;
    }
}
