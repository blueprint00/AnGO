package com.example.weathersearch.ListView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.weathersearch.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private ArrayList<ListData> listData = null;
    private int list_cnt = 0;

    public ListAdapter(ArrayList<ListData> listData) {
        this.listData = listData;
        list_cnt = listData.size();
    }

    @Override
    public int getCount() {
        Log.i("TAG", "getCount");
        return list_cnt;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            final Context context = parent.getContext();
            if(inflater == null){
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.listview_item, parent,false);
        }

        TextView textTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView textSummary = (TextView) convertView.findViewById(R.id.textSummary);

        textTitle.setText(listData.get(position).title);
        textSummary.setText(listData.get(position).summary);

        return convertView;
    }
}
