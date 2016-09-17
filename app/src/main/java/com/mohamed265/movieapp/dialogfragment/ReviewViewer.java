package com.mohamed265.movieapp.dialogfragment;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohamed265.movieapp.R;
import com.mohamed265.movieapp.entity.Review;

public class ReviewViewer extends DialogFragment {

    private Review review;

    private TextView tv;

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_view, container, false);
        tv = (TextView) view.findViewById(R.id.review_content_textView_view);
        try {
            tv.setText(review.getContent());
            getDialog().setTitle(review.getAuthor() + " Review");
        } catch (Exception e) {
            tv.setText("No Content");
            getDialog().setTitle("No Author");
        }
        return view;
    }
}
