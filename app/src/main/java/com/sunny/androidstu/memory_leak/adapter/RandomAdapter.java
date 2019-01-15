package com.sunny.androidstu.memory_leak.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sunny.androidstu.R;
import com.sunny.baselib.MContextUtils;

import java.util.List;

/**
 * Created by zhangxin17 on 2019/1/15
 */
public class RandomAdapter extends BaseAdapter {

    private List<String> mList;

    public void setList(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        if (position < 0 || mList == null || mList.isEmpty()) {
            return null;
        }
        if (position < mList.size()) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /**
         * 没有复用convertView会造成内存泄漏
         */
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(MContextUtils.getAppContext()).inflate(R.layout.item_memory_leak_item, null);

            viewHolder.nameView = convertView.findViewById(R.id.item_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String showStr = getItem(position);
        if (showStr == null) {
            showStr = "NULL";
        }
        viewHolder.nameView.setText(showStr);
        viewHolder.nameView.setTextColor(Color.BLACK);
        return convertView;
    }

    public static class ViewHolder {
        public TextView nameView;
    }
}
