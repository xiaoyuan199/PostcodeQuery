package com.example.postcodequery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.postcodequery.R;
import com.example.postcodequery.model.AreaName;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class AreaNameAdapter extends ArrayAdapter<AreaName> {

    private int resourceid;
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AreaName areaName=getItem(position);
        //提高了运行效率
        View view;
        if(convertView==null) {
             view = LayoutInflater.from(getContext()).inflate(resourceid, parent, false);
        }else{
            view=convertView;
        }
        TextView textView= (TextView) view.findViewById(R.id.text_item);
        textView.setText(areaName.getAreaName());

        return view;
    }

    public AreaNameAdapter(Context context, int resource, List<AreaName> objects) {
        super(context, resource, objects);
        resourceid=resource;



    }
}
