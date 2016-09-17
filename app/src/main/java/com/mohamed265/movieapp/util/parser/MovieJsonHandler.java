package com.mohamed265.movieapp.util.parser;

import com.mohamed265.movieapp.entity.Movie;
import com.mohamed265.movieapp.entity.Review;
import com.mohamed265.movieapp.entity.Trailer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mohamed265 on 8/13/2016.
 */
public class MovieJsonHandler {

    final private static String id = "id";
    final private static String title = "title";
    final private static String overview = "overview";
    final private static String release_date = "release_date";


    final private static String poster_path = "poster_path";
    final private static String backdrop_path = "backdrop_path";

    final private static String popularity = "popularity";
    final private static String vote_count = "vote_count";
    final private static String vote_average = "vote_average";

    final private static String reviews = "reviews";
    final private static String trailers = "trailers";

    final private static String favorite = "favorite";

    public static ArrayList<Movie> parseJsonToMovieList(String json) throws Exception {
        ArrayList<Movie> list = new ArrayList<>();
        JSONObject reader = new JSONObject(json);
        JSONArray results = reader.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject object = results.getJSONObject(i);
            Movie movie = pasreJsonObjectToMovie(object);
            list.add(movie);
        }
        return list;
    }

    private static Movie pasreJsonObjectToMovie(JSONObject object) throws Exception {
        Movie movie = new Movie();

        movie.setId(object.getInt(id));
        movie.setTitle(object.getString(title));
        movie.setOverview(object.getString(overview));
        movie.setReleaseDate(object.getString(release_date));


        movie.setBackdropPath(object.getString(backdrop_path));
        movie.setPosterPath(object.getString(poster_path));

        movie.setPopularity(object.getDouble(popularity));
        movie.setVoteAverage(object.getDouble(vote_average));

        movie.setVoteCount(object.getInt(vote_count));
        if (object.has(favorite))
            movie.setFavorite(object.getBoolean(favorite));

        return movie;
    }

    public static Movie pasreJsonStringToMovie(String json) throws Exception {
        JSONObject object = new JSONObject(json);
        Movie movie = new Movie();

        movie.setId(object.getInt(id));
        movie.setTitle(object.getString(title));
        movie.setOverview(object.getString(overview));
        movie.setReleaseDate(object.getString(release_date));


        movie.setBackdropPath(object.getString(backdrop_path));
        movie.setPosterPath(object.getString(poster_path));

        movie.setPopularity(object.getDouble(popularity));
        movie.setVoteAverage(object.getDouble(vote_average));

        movie.setVoteCount(object.getInt(vote_count));

        movie.setReviews(new ArrayList<Review>());
        JSONArray ja = object.getJSONArray(reviews);
        for (int i = 0; i < ja.length(); i++) {
            JSONObject ob = ja.getJSONObject(i);
            movie.getReview().add(ReviewJsonHandler.pasreJsonStringToReview(ob.toString()));
        }

        movie.setTrailers(new ArrayList<Trailer>());
        JSONArray jaq = object.getJSONArray(trailers);
        for (int i = 0; i < jaq.length(); i++) {
            JSONObject ob = jaq.getJSONObject(i);
            movie.getTrailers().add(TrailerJsonHandler.pasreJsonStringToTrailer(ob.toString()));
        }
        movie.setFavorite(object.getBoolean(favorite));
        return movie;
    }

    public static String pasreMovieToJsonString(Movie movie) throws Exception {
        JSONObject object = new JSONObject();

        object.put(id, movie.getId());
        object.put(title, movie.getTitle());
        object.put(overview, movie.getOverview());
        object.put(release_date, movie.getReleaseDate());

        object.put(backdrop_path, movie.getBackdropPath());
        object.put(poster_path, movie.getPosterPath());

        object.put(popularity, movie.getPopularity());
        object.put(vote_average, movie.getVoteAverage());
        object.put(vote_count, movie.getVoteCount());


        JSONArray ja = new JSONArray();
        for (Review review : movie.getReview()) {
            ja.put(ReviewJsonHandler.pasreReviewToJsonObject(review));
        }
        object.put(reviews, ja);


        JSONArray ja1 = new JSONArray();
        for (Trailer trailer : movie.getTrailers()) {
            ja1.put(TrailerJsonHandler.pasreTrailerToJsonObjst(trailer));
        }
        object.put(trailers, ja1);

        object.put(favorite, movie.isFavorite());
        return object.toString();
    }


}
