package com.mohamed265.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohamed265.movieapp.R;
import com.mohamed265.movieapp.entity.Trailer;
import com.mohamed265.movieapp.util.PicassoHandler;

import java.util.ArrayList;

/**
 * Created by Mohamed265 on 14-Sep-16.
 */
public class TrailerAdapter extends BaseAdapter {

    private ArrayList<Trailer> trailerList;

    private LayoutInflater mInflater;

    private Context context;

    public TrailerAdapter(Context context, ArrayList<Trailer> trailerList) {
        this.trailerList = trailerList;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return trailerList.size();
    }

    public Object getItem(int position) {
        return trailerList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.trailerlist_view, null);
        final Trailer trailer = trailerList.get(position);

        ImageView imageview = (ImageView) convertView.findViewById(R.id.trailer_play_imageview);
        PicassoHandler.loadImageWithPicassoFromResource(context, R.mipmap.playbutton, imageview);

        TextView tv = (TextView) convertView.findViewById(R.id.trailer_name_textView);
        tv.setText(trailer.getName());

        return convertView;
    }
}
