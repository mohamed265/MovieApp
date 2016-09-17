package com.mohamed265.movieapp.util.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mohamed265 on 14-Sep-16.
 */
public class BaseFetcher {

    private HttpURLConnection connection;

    private BufferedReader reader;

    protected final String baseUrl = "http://api.themoviedb.org/3/movie/";

    protected final String KEY = "dfbab2787904f201099b6f432d8600a7";

    protected String perform(String link) throws MalformedURLException, IOException {
        String result = "";

        URL url = new URL(link);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream is = connection.getInputStream();

        if (is == null) {
            throw new IOException("InputStream is Null");
        }

        StringBuffer buffer = new StringBuffer();
        reader = new BufferedReader(new InputStreamReader(is));

        String line = "";
        while ((line = reader.readLine()) != null) {
            result += line;
        }

        connection.disconnect();
        reader.close();
        is.close();

        return result;
    }

}
