package com.mohamed265.movieapp.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mohamed265.movieapp.R;
import com.mohamed265.movieapp.util.OnMovieEventListener;
import com.mohamed265.movieapp.adapter.MovieAdapter;
import com.mohamed265.movieapp.entity.Movie;
import com.mohamed265.movieapp.entity.Review;
import com.mohamed265.movieapp.entity.Trailer;
import com.mohamed265.movieapp.util.MessageUtil;
import com.mohamed265.movieapp.util.fetcher.FetchMovieData;
import com.mohamed265.movieapp.util.fetcher.FetchReviewData;
import com.mohamed265.movieapp.util.fetcher.FetchTrailerData;
import com.mohamed265.movieapp.util.parser.MovieJsonHandler;
import com.mohamed265.movieapp.util.parser.PersistanceUtil;
import com.mohamed265.movieapp.util.parser.ReviewJsonHandler;
import com.mohamed265.movieapp.util.parser.TrailerJsonHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFragment extends Fragment {

    private GridView mainGridView;

    private FetchMovieData fmd;
    private FetchTrailerData ftd;
    private FetchReviewData frd;

    private ArrayList<Movie> movieList = null;
    private MovieAdapter adapter;

    private int page = 1;
    private boolean flag;
    private boolean once;
    private HashMap<Integer, Movie> favs = null;
    private ProgressDialog progress;

    private Activity activity;
    private boolean favPreview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            movieList = (ArrayList<Movie>) savedInstanceState.getSerializable("movies");
            page = savedInstanceState.getInt("page");
            flag = savedInstanceState.getBoolean("flag");
            adapter = new MovieAdapter(getActivity(), favs, movieList);
            mainGridView.setAdapter(adapter);
        }

        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();
        favPreview = false;

        activity = getActivity();

        fmd = FetchMovieData.getInstance();
        ftd = FetchTrailerData.getInstance();
        frd = FetchReviewData.getInstance();

        movieList = new ArrayList<>();
        loadFavs();
        //AddFavs();
        adapter = new MovieAdapter(getActivity(), favs, movieList);
        once = true;
        flag = true;

        if (savedInstanceState == null) {
            new LoadData().execute(0);
        } else {
            movieList = (ArrayList<Movie>) savedInstanceState.getSerializable("movies");
            page = savedInstanceState.getInt("page");
            flag = savedInstanceState.getBoolean("flag");
            adapter = new MovieAdapter(getActivity(), favs, movieList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            movieList = (ArrayList<Movie>) savedInstanceState.getSerializable("movies");
            page = savedInstanceState.getInt("page");
            flag = savedInstanceState.getBoolean("flag");
            adapter = new MovieAdapter(getActivity(), favs, movieList);
            mainGridView.setAdapter(adapter);
        }

        View view = inflater.inflate(R.layout.fragment_main, container, false);


        mainGridView = (GridView) view.findViewById(R.id.gridview);
        mainGridView.setAdapter(adapter);
        mainGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                try {

                    if (!favPreview && mainGridView.getLastVisiblePosition() == mainGridView.getAdapter().getCount() - 1 &&
                            mainGridView.getChildAt(mainGridView.getChildCount() - 1).getBottom() <= mainGridView.getHeight()) {
                        progress.show();
                        new LoadData().execute(0);
                    }
                } catch (Exception e) {
                }
            }

        });

        mainGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View v,
                                                                        int position, long id) {
                                                    progress.show();
                                                    new LoadMovieData().execute(movieList.get(position).getId(), position);
                                                }
                                            }
        );

        if (once) {
            new LoadData().execute(0);
        } else {
            adapter = new MovieAdapter(getActivity(), favs, movieList);
            mainGridView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            movieList = (ArrayList<Movie>) savedInstanceState.getSerializable("movies");
            page = savedInstanceState.getInt("page");
            flag = savedInstanceState.getBoolean("flag");
            adapter = new MovieAdapter(getActivity(), favs, movieList);
            mainGridView.setAdapter(adapter);
        }
    }

    public void loadFavs() {
        try {
            favs = PersistanceUtil.loadFavoriteMovieData(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddFavs() {
        for (Integer i : favs.keySet()) {
            movieList.add(favs.get(i));
        }

    }

    public void fetchMostPopular() {
        page = 1;
        movieList.clear();
        new LoadData().execute(0);
        flag = true;
        favPreview = false;
    }

    public void fetchHisestRate() {
        page = 1;
        movieList.clear();
        new LoadData().execute(0);
        flag = false;
        favPreview = false;
    }

    public void fetchFavs() {
        favPreview = true;
        movieList.clear();
        AddFavs();
        adapter.notifyDataSetChanged();
    }

    private class LoadData extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {

            try {
                String json;
                if (flag)
                    json = fmd.fetchByPopular(page++);
                else
                    json = fmd.fetchByTopRated(page++);
                ArrayList<Movie> te = MovieJsonHandler.parseJsonToMovieList(json);
                for (Movie mm : te) {
                    if (!favs.containsKey(mm.getId()))
                        movieList.add(mm);

                }
            } catch (Exception e) {
                MessageUtil.showNoInternet(activity);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.notifyDataSetChanged();
            if (once) {
                once = false;
                if (movieList.size() > 0) {
                    try {
                        new LoadMovieData().execute(movieList.get(0).getId(), -1);
                    } catch (Exception e) {
                    }
                }
                progress.dismiss();
            } else
                progress.dismiss();
        }
    }

    private class LoadMovieData extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... flag) {
            Movie movie;
            if (flag[1] == -1)
                movie = movieList.get(0);
            else
                movie = movieList.get(flag[1]);
            try {
                //if (movie.getReview() == null) {
                String json = ftd.fetch(flag[0] + "");
                movie.setTrailers(new ArrayList<Trailer>());
                movie.getTrailers().addAll(TrailerJsonHandler.parseJsonToTrailerList(json));
                String json1 = frd.fetch(flag[0] + "", 1);
                movie.setReviews(new ArrayList<Review>());
                movie.getReview().addAll(ReviewJsonHandler.parseJsonToReviewList(json1));
                // }else
                if (movie.isFavorite()) {
                    PersistanceUtil.updateMovieFromFavorite(movie, activity);
                }
            } catch (Exception e) {
                if (!movie.isFavorite())
                    MessageUtil.showNoInternet(activity);
                e.printStackTrace();
            }
            return flag[1];
        }

        @Override
        protected void onPostExecute(Integer position) {
            progress.dismiss();
            try {
                if (position != -1) {
                    OnMovieEventListener listener = (OnMovieEventListener) getActivity();
                    listener.OnSelectionChanged(movieList.get(position));
                } else {
                    OnMovieEventListener listener = (OnMovieEventListener) getActivity();
                    listener.setFirstMovie(movieList.get(0));
                }
            } catch (Exception e) {
            }
        }
    }


    public void favoriteHandler(Movie frommovie) {
        for (Movie movie : movieList) {
            if (movie.getId() == frommovie.getId()) {
                movie.setFavorite(frommovie.isFavorite());
                adapter.notifyDataSetChanged();
                if (frommovie.isFavorite())
                    favs.put(frommovie.getId(), movie);
                else
                    favs.remove(frommovie.getId());
                return;
            }
        }
        if (favPreview) {
            if (frommovie.isFavorite())
                movieList.add(frommovie);
            else
                movieList.remove(frommovie);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("movies", movieList);
        savedInstanceState.putInt("page", page);
        savedInstanceState.putBoolean("flag", flag);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        OnMovieEventListener listener = (OnMovieEventListener) getActivity();
        listener.mainViewRegistration(this);
    }
}

