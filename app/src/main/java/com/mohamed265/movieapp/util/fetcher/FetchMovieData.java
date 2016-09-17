package com.mohamed265.movieapp.util.fetcher;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Mohamed265 on 7/30/2016.
 */
public class FetchMovieData extends BaseFetcher {

    private static FetchMovieData fwd = null;

    private String method;

    private FetchMovieData() {
        method = "popular";
    }

    public static FetchMovieData getInstance() {
        if (fwd == null)
            fwd = new FetchMovieData();
        return fwd;
    }

    public String fetchByPopular(int page) throws MalformedURLException, IOException {
        method = "popular";
        return perform(baseUrl + method + "?api_key=" + KEY + "&page=" + page);
    }

    public String fetchByTopRated(int page) throws MalformedURLException, IOException {
        method = "top_rated";
        return perform(baseUrl + method + "?api_key=" + KEY + "&page=" + page);
    }

    public String fetch(String method, int page) throws MalformedURLException, IOException {
        this.method = method;
        return perform(baseUrl + method + "?api_key=" + KEY + "&page=" + page);
    }
}
