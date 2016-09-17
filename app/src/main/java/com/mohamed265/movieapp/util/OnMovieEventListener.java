package com.mohamed265.movieapp.util;

import com.mohamed265.movieapp.entity.Movie;
import com.mohamed265.movieapp.fragment.MainFragment;
import com.mohamed265.movieapp.fragment.MovieDeatailsFragment;

/**
 * Created by Mohamed265 on 17-Sep-16.
 */
public interface OnMovieEventListener {
    public void OnSelectionChanged(Movie movie);

    public void favoriteHandler(Movie movie);

    public void setFirstMovie(Movie movie);

    public void detailViewRegistration(MovieDeatailsFragment movieDeatailsFragment);

    public void mainViewRegistration(MainFragment mainFragment);
}
