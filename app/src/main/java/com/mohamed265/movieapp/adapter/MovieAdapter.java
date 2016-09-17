package com.mohamed265.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mohamed265.movieapp.R;
import com.mohamed265.movieapp.entity.Movie;
import com.mohamed265.movieapp.util.PicassoHandler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mohamed265 on 8/13/2016.
 */
public class MovieAdapter extends BaseAdapter {

    private ArrayList<Movie> movieList;

    private LayoutInflater mInflater;

    private Context context;

    // private HashMap<Integer, Movie> favs;

    public MovieAdapter(Context context, HashMap<Integer, Movie> f, ArrayList<Movie> movieList) {
        //this.movieList = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.movieList = movieList;
        // favs = new HashMap<>();
    }


    public int getCount() {
        return movieList.size();
    }

    public Object getItem(int position) {
        return movieList.get(position);
    }

    public long getItemId(int position) {
        return movieList.get(position).getId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = movieList.get(position);
        // if (!favs.containsKey(movie.getId()) || (favs.containsKey(movie.getId()) && !movie.isFavorite())) {
        convertView = mInflater.inflate(R.layout.movie_view, null);

        ImageView imageview = (ImageView) convertView.findViewById(R.id.moviePoster);
        ImageView favIcon = (ImageView) convertView.findViewById(R.id.fav_icon_imageview);

        PicassoHandler.loadImageWithPicassoFromTMDB(context, movie.getPosterPath(), imageview);
        if (!movie.isFavorite())
            favIcon.setVisibility(View.GONE);
        return convertView;
        //  } else
        //      return null;
    }
}
