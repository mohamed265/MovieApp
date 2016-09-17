package com.mohamed265.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mohamed265.movieapp.R;
import com.mohamed265.movieapp.entity.Review;

import java.util.ArrayList;

/**
 * Created by Mohamed265 on 14-Sep-16.
 */
public class ReviewAdapter extends BaseAdapter {

    private ArrayList<Review> reviewsList;

    private LayoutInflater mInflater;

    private Context context;

    public ReviewAdapter(Context context, ArrayList<Review> reviewsList) {
        this.reviewsList = reviewsList;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return reviewsList.size();
    }

    public Object getItem(int position) {
        return reviewsList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.reviewlist_view, null);
        final Review review = reviewsList.get(position);

        TextView tva = (TextView) convertView.findViewById(R.id.review_author_textView);
        tva.setText(review.getAuthor());

        TextView tvc = (TextView) convertView.findViewById(R.id.review_content_textView);
        tvc.setText(review.getContent());

        return convertView;
    }
}
