package com.mohamed265.movieapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Mohamed265 on 8/13/2016.
 */
public class PicassoHandler {

    public static void loadImageWithPicassoFromTMDB(Context context, String posterId, ImageView imageView) {
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185" + posterId).into(imageView);
    }

    public static void loadImageWithPicassoFromResource(Context context, int resourceId, ImageView imageView) {
        Picasso.with(context).load(resourceId).into(imageView);
    }
}
