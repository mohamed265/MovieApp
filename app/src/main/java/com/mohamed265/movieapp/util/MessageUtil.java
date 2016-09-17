package com.mohamed265.movieapp.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Mohamed265 on 15-Sep-16.
 */
public class MessageUtil {

    public static void showNoInternet(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showSomethingwentWrong(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showMovieAddedToFavorite(final Activity activity, final String name) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), name + " Added To Favorite", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showMovieRemovedFromFavorite(final Activity activity, final String name) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), name + " Removed From Favorite", Toast.LENGTH_LONG).show();
            }
        });
    }


}
