package com.mohamed265.movieapp.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mohamed265 on 8/13/2016.
 */
public class Movie implements Serializable {

    private int id;

    private String title;

    private String overview;

    private String releaseDate;

    private String posterPath;

    private String backdropPath;

    private double popularity;

    private int voteCount;

    private double voteAverage;

    private int maxReviewsPages;

    private boolean isFavorite;

    private ArrayList<Review> reviews;

    private ArrayList<Trailer> trailers;


    public Movie() {
        trailers = null;
        reviews = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public ArrayList<Review> getReview() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<Trailer> trailers) {
        this.trailers = trailers;
    }

    public int getMaxReviewsPages() {
        return maxReviewsPages;
    }

    public void setMaxReviewsPages(int maxReviewsPages) {
        this.maxReviewsPages = maxReviewsPages;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
