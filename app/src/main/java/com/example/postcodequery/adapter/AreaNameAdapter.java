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
        ViewHolder holder;
        AreaName areaName=getItem(position);
     //优化了Listview
        if(convertView==null) {
            holder=new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(resourceid, parent, false);
            holder.textView=(TextView)convertView.findViewById(R.id.text_item);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(areaName.getAreaName());

        return convertView;
    }

    public AreaNameAdapter(Context context, int resource, List<AreaName> objects) {
        super(context, resource, objects);
        resourceid=resource;
    }
    static class ViewHolder{
        TextView textView;
    }
}
