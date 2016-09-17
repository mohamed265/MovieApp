package com.mohamed265.movieapp.util.parser;

import com.mohamed265.movieapp.entity.Review;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mohamed265 on 14-Sep-16.
 */
public class ReviewJsonHandler {
    final private static String id = "id";
    final private static String author = "author";
    final private static String content = "content";

    public static ArrayList<Review> parseJsonToReviewList(String json) throws Exception {
        ArrayList<Review> list = new ArrayList<>();
        JSONObject reader = new JSONObject(json);
        JSONArray results = reader.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject object = results.getJSONObject(i);
            Review review = pasreJsonObjectToReview(object);
            list.add(review);
        }
        return list;
    }

    private static Review pasreJsonObjectToReview(JSONObject object) throws Exception {
        Review review = new Review();

        review.setId(object.getString(id));
        review.setAuthor(object.getString(author));
        review.setContent(object.getString(content));

        return review;
    }


    public static Review pasreJsonStringToReview(String json) throws Exception {

        JSONObject object = new JSONObject(json);
        Review review = new Review();

        review.setId(object.getString(id));
        review.setContent(object.getString(content));
        review.setAuthor(object.getString(author));

        return review;
    }

    public static String pasreReviewToJsonString(Review review) throws Exception {
        return pasreReviewToJsonObject(review).toString();
    }

    public static JSONObject pasreReviewToJsonObject(Review review) throws Exception {
        JSONObject object = new JSONObject();

        object.put(id, review.getId());
        object.put(author, review.getAuthor());
        object.put(content, review.getContent());

        return object;
    }
}
