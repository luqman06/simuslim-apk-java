package com.example.sinergimuslim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sinergimuslim.R;
import com.example.sinergimuslim.menu.menu_data_relasi.DataRelasiFragment;

public class GridAdapter extends BaseAdapter {

    Context context;
    public Integer[] mThumbIds = {
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };

    public GridAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return mThumbIds[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView  = inflater.inflate(R.layout.item_grid,parent,false);
            ImageView imageView  = convertView.findViewById(R.id.itemgrid);
            viewHolder = new ViewHolder(imageView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.imageView.setImageResource(mThumbIds[position]);

        return convertView;

    }

    class ViewHolder{
        ImageView imageView;
        ViewHolder(ImageView imageView){
            this.imageView = imageView;
        }

    }
}
