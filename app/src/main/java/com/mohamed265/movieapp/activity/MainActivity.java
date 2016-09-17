package com.mohamed265.movieapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mohamed265.movieapp.R;
import com.mohamed265.movieapp.entity.Movie;
import com.mohamed265.movieapp.fragment.MainFragment;
import com.mohamed265.movieapp.fragment.MovieDeatailsFragment;
import com.mohamed265.movieapp.util.OnMovieEventListener;

public class MainActivity extends ActionBarActivity implements OnMovieEventListener {

    private MainFragment mainFragment;
    private MovieDeatailsFragment movieDeatailsFragment;
    public boolean two = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check whether the Activity is using the layout verison with the fragment_container
        // FrameLayout and if so we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However if we are being restored from a previous state, then we don't
            // need to do anything and should return or we could end up with overlapping Fragments
            if (savedInstanceState != null) {
               // mainFragment = (MainFragment) getSupportFragmentManager().getFragment(savedInstanceState, "mainfragment");
                //movieDeatailsFragment = (MovieDeatailsFragment) getSupportFragmentManager().getFragment(savedInstanceState, "movieDeatailsFragment");
                return;
            }
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mainFragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.most:
                mainFragment.fetchMostPopular();
                return true;
            case R.id.high:
                mainFragment.fetchHisestRate();
                return true;
            case R.id.favss:
                mainFragment.fetchFavs();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void OnSelectionChanged(Movie movie) {
        MovieDeatailsFragment descriptionFragment = (MovieDeatailsFragment) getSupportFragmentManager().findFragmentById(R.id.description_fragment);

        if (descriptionFragment != null) {
            two = false;
            descriptionFragment.setMovie(movie);
        } else {
            two = true;
            this.movieDeatailsFragment = new MovieDeatailsFragment();
            this.movieDeatailsFragment.setMovie(movie);
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the backStack so the User can navigate back
            fm.replace(R.id.fragment_container, this.movieDeatailsFragment);
            fm.addToBackStack(null);
            fm.commit();
        }
    }

    @Override
    public void favoriteHandler(Movie movie) {
        mainFragment.favoriteHandler(movie);
    }

    @Override
    public void setFirstMovie(Movie movie) {
     //   if (movieDeatailsFragment != null)
            movieDeatailsFragment.setMovie(movie);
    }

    @Override
    public void detailViewRegistration(MovieDeatailsFragment movieDeatailsFragment) {
        this.movieDeatailsFragment = movieDeatailsFragment;
    }

    @Override
    public void mainViewRegistration(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

  //      getSupportFragmentManager().putFragment(savedInstanceState, "mainfragment", mainFragment);
//        getSupportFragmentManager().putFragment(savedInstanceState, "movieDeatailsFragment", movieDeatailsFragment);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // mainFragment = (MainFragment) getSupportFragmentManager().getFragment(savedInstanceState, "mainfragment");
        // movieDeatailsFragment = (MovieDeatailsFragment) getSupportFragmentManager().getFragment(savedInstanceState, "movieDeatailsFragment");
        onCreate(savedInstanceState);
    }

    public boolean isTwoPaneMode() {
        return two;
    }


}
