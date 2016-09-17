package com.mohamed265.movieapp.util.fetcher;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Mohamed265 on 7/30/2016.
 */
public class FetchReviewData extends BaseFetcher {

    private static FetchReviewData fwd = null;

    private FetchReviewData() {
    }

    public static FetchReviewData getInstance() {
        if (fwd == null)
            fwd = new FetchReviewData();
        return fwd;
    }

    public String fetch(String id, int page) throws MalformedURLException, IOException {
        return perform(baseUrl + id + "/reviews?api_key=" + KEY + "&page=" + page);
    }
}
