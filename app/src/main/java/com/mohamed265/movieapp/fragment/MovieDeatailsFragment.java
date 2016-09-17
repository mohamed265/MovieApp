package com.mohamed265.movieapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mohamed265.movieapp.R;
import com.mohamed265.movieapp.util.OnMovieEventListener;
import com.mohamed265.movieapp.adapter.ReviewAdapter;
import com.mohamed265.movieapp.adapter.TrailerAdapter;
import com.mohamed265.movieapp.dialogfragment.ImageViewer;
import com.mohamed265.movieapp.dialogfragment.ReviewViewer;
import com.mohamed265.movieapp.entity.Movie;
import com.mohamed265.movieapp.util.ListUtils;
import com.mohamed265.movieapp.util.MessageUtil;
import com.mohamed265.movieapp.util.PicassoHandler;
import com.mohamed265.movieapp.util.parser.PersistanceUtil;

public class MovieDeatailsFragment extends Fragment {

    private Movie movie;

    private Button fav;
    private ImageView poster;
    private TextView title;
    private TextView overview;
    private TextView releaseDate;
    private TextView populrity;
    private ScrollView scrollView;

    private TrailerAdapter trailerAdapter;
    private ListView trailerListView;

    private ReviewAdapter reviewsAdapter;
    private ListView reviewsListView;

    private TextView noreviews;
    private TextView notrailers;

    private Activity activity;

    private LinearLayout frameLayout;

    private FrameLayout line11;
    private FrameLayout line22;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
        }

        View view = inflater.inflate(R.layout.activity_movie_deatails, container, false);

        activity = getActivity();
        noreviews = (TextView) view.findViewById(R.id.noreview_textView);
        notrailers = (TextView) view.findViewById(R.id.notrailers_textView);
        poster = (ImageView) view.findViewById(R.id.movie_poster_imageView);
        title = (TextView) view.findViewById(R.id.movie_title_textview);
        overview = (TextView) view.findViewById(R.id.overview_textView);
        releaseDate = (TextView) view.findViewById(R.id.release_date_textView);
        populrity = (TextView) view.findViewById(R.id.popularty_textView);
        trailerListView = (ListView) view.findViewById(R.id.trailers_listview);
        reviewsListView = (ListView) view.findViewById(R.id.reviews_listview);
        fav = (Button) view.findViewById(R.id.favorite_button);
        frameLayout = (LinearLayout) view.findViewById(R.id.frameLayout);
        line22 = (FrameLayout) view.findViewById(R.id.line22);
        line11 = (FrameLayout) view.findViewById(R.id.line11);

        line11.setVisibility(View.INVISIBLE);
        line22.setVisibility(View.INVISIBLE);
        frameLayout.setVisibility(View.INVISIBLE);
        noreviews.setVisibility(View.INVISIBLE);
        notrailers.setVisibility(View.INVISIBLE);
        poster.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
        overview.setVisibility(View.INVISIBLE);
        releaseDate.setVisibility(View.INVISIBLE);
        populrity.setVisibility(View.INVISIBLE);
        trailerListView.setVisibility(View.INVISIBLE);
        reviewsListView.setVisibility(View.INVISIBLE);
        fav.setVisibility(View.INVISIBLE);

        if (movie != null)
            init();

        //scrollView = (ScrollView) findViewById(R.id.scroll);
        // scrollView.scrollTo(0, scrollView.getBottom());
        // poster.requestFocus();
        return view;
    }

    private void init() {
        noreviews.setVisibility(View.GONE);
        notrailers.setVisibility(View.GONE);

        line11.setVisibility(View.VISIBLE);
        line22.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.VISIBLE);
        poster.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        overview.setVisibility(View.VISIBLE);
        releaseDate.setVisibility(View.VISIBLE);
        populrity.setVisibility(View.VISIBLE);
        trailerListView.setVisibility(View.VISIBLE);
        reviewsListView.setVisibility(View.VISIBLE);
        fav.setVisibility(View.VISIBLE);

        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate().substring(0, 4));
        populrity.setText(movie.getVoteAverage() + " / 10");
        PicassoHandler.loadImageWithPicassoFromTMDB(getActivity(), movie.getPosterPath(), poster);
        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageViewer im = new ImageViewer();
                im.setImage(movie.getPosterPath());
                FragmentManager fm = getActivity().getSupportFragmentManager();
                im.show(fm, movie.getTitle() + " Poster");
            }
        });

        if (movie.getTrailers() != null) {

            trailerAdapter = new TrailerAdapter(getActivity(), movie.getTrailers());
            trailerListView.setAdapter(trailerAdapter);

            trailerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + movie.getTrailers().get(position).getKey()));
                    startActivity(intent);
                }
            });
            trailerAdapter.notifyDataSetChanged();
            ListUtils.setDynamicHeight(trailerListView);
        } else
            notrailers.setVisibility(View.VISIBLE);

        if (movie.getReview() != null) {

            reviewsAdapter = new ReviewAdapter(getActivity(), movie.getReview());
            reviewsListView.setAdapter(reviewsAdapter);

            reviewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    //Toast.makeText(MovieDeatailsActivity.this, movie.getReview().get(position).getContent(), Toast.LENGTH_SHORT).show();
                    ReviewViewer rv = new ReviewViewer();
                    rv.setReview(movie.getReview().get(position));
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    rv.show(fm, movie.getTitle() + " Reviews");
                }
            });
            reviewsAdapter.notifyDataSetChanged();
            ListUtils.setDynamicHeight(reviewsListView);
        } else
            noreviews.setVisibility(View.VISIBLE);


        if (movie.isFavorite()) {
            fav.setBackgroundResource((R.drawable.stari));
        } else {
            fav.setBackgroundResource(R.drawable.starii);
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (movie.isFavorite())
                        PersistanceUtil.removeMovieFromFavorite(movie, activity);
                    else
                        PersistanceUtil.addMovieToFavorite(movie, activity);
                } catch (Exception e) {
                    MessageUtil.showSomethingwentWrong(activity);
                    e.printStackTrace();
                } finally {
                    if (movie.isFavorite()) {
                        fav.setBackgroundResource((R.drawable.stari));
                    } else {
                        fav.setBackgroundResource(R.drawable.starii);
                    }
                    OnMovieEventListener listener = (OnMovieEventListener) getActivity();
                    listener.favoriteHandler(movie);
                }
            }
        });
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        try {
            init();
        }catch (Exception f){}
    }

    @Override
    public void onStart() {
        super.onStart();
        OnMovieEventListener listener = (OnMovieEventListener) getActivity();
        listener.detailViewRegistration(this);
    }
}