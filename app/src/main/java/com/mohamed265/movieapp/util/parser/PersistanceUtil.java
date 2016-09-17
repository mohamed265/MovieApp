package com.mohamed265.movieapp.util.parser;

import android.app.Activity;
import android.content.SharedPreferences;

import com.mohamed265.movieapp.entity.Movie;
import com.mohamed265.movieapp.util.MessageUtil;

import java.util.HashMap;

/**
 * Created by Mohamed265 on 15-Sep-16.
 */
public class PersistanceUtil {
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public static void addMovieToFavorite(Movie movie, Activity activity) throws Exception {
        SharedPreferences prefs = activity.getSharedPreferences(MY_PREFS_NAME, activity.MODE_PRIVATE);
        int len = prefs.getInt("len", 0);
        SharedPreferences.Editor editor = activity.getSharedPreferences(MY_PREFS_NAME, activity.MODE_PRIVATE).edit();
        editor.putInt("len", len + 1);
        editor.putInt("id" + len, movie.getId());
        movie.setFavorite(true);
        editor.putString("movie" + len, MovieJsonHandler.pasreMovieToJsonString(movie));

        editor.commit();

        MessageUtil.showMovieAddedToFavorite(activity, movie.getTitle());
    }

    public static void updateMovieFromFavorite(Movie movie, Activity activity) throws Exception {
        SharedPreferences prefs = activity.getSharedPreferences(MY_PREFS_NAME, activity.MODE_PRIVATE);
        int len = prefs.getInt("len", 0);
        SharedPreferences.Editor editor = activity.getSharedPreferences(MY_PREFS_NAME, activity.MODE_PRIVATE).edit();
        for (int i = 0; i < len; i++) {
            int id = prefs.getInt("id" + i, -1);
            if (id == movie.getId()) {
                editor.remove("movie" + i);
                movie.setFavorite(true);
                editor.putString("movie" + i, MovieJsonHandler.pasreMovieToJsonString(movie));
                editor.commit();
                return;
            }
        }
    }

    public static void removeMovieFromFavorite(Movie movie, Activity activity) throws Exception {
        SharedPreferences prefs = activity.getSharedPreferences(MY_PREFS_NAME, activity.MODE_PRIVATE);
        int len = prefs.getInt("len", 0);
        SharedPreferences.Editor editor = activity.getSharedPreferences(MY_PREFS_NAME, activity.MODE_PRIVATE).edit();
        for (int i = 0; i < len; i++) {
            int id = prefs.getInt("id" + i, -1);
            if (id == movie.getId()) {
                editor.remove("id" + i);
                editor.remove("movie" + i);
                movie.setFavorite(false);
                editor.commit();
                MessageUtil.showMovieRemovedFromFavorite(activity, movie.getTitle());
                return;
            }
        }
    }

    public static Boolean isFavorite(Movie movie, Activity activity) throws Exception {
        SharedPreferences prefs = activity.getSharedPreferences(MY_PREFS_NAME, activity.MODE_PRIVATE);
        int len = prefs.getInt("len", 0);
        for (int i = 0; i < len; i++) {
            int id = prefs.getInt("id" + i, -1);
            if (id == movie.getId()) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<Integer, Movie> loadFavoriteMovieData(Activity activity) throws Exception {
        HashMap<Integer, Movie> res = new HashMap<>();
        SharedPreferences prefs = activity.getSharedPreferences(MY_PREFS_NAME, activity.MODE_PRIVATE);
        int len = prefs.getInt("len", 0);
        for (int i = 0; i < len; i++) {
            int id = prefs.getInt("id" + i, -1);
            if (id != -1) {
                String str = prefs.getString("movie" + i, "");
                Movie movie = MovieJsonHandler.pasreJsonStringToMovie(str);
                res.put(id, movie);
            }
        }
        return res;
    }
}
