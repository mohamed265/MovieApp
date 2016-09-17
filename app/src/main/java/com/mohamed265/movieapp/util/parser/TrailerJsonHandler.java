package com.mohamed265.movieapp.util.parser;

import com.mohamed265.movieapp.entity.Trailer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mohamed265 on 14-Sep-16.
 */
public class TrailerJsonHandler {
    final private static String id = "id";
    final private static String key = "key";
    final private static String name = "name";
    final private static String site = "site";
    final private static String size = "size";
    final private static String type = "type";

    public static ArrayList<Trailer> parseJsonToTrailerList(String json) throws Exception {
        ArrayList<Trailer> list = new ArrayList<>();
        JSONObject reader = new JSONObject(json);
        JSONArray results = reader.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject object = results.getJSONObject(i);
            Trailer trailer = pasreJsonObjectToTrailer(object);
            list.add(trailer);
        }
        return list;
    }

    private static Trailer pasreJsonObjectToTrailer(JSONObject object) throws Exception {
        Trailer trailer = new Trailer();

        trailer.setId(object.getString(id));
        trailer.setKey(object.getString(key));
        trailer.setName(object.getString(name));
        trailer.setSite(object.getString(site));
        trailer.setSize(object.getInt(size));
        trailer.setType(object.getString(type));

        return trailer;
    }

    public static Trailer pasreJsonStringToTrailer(String json) throws Exception {

        JSONObject object = new JSONObject(json);
        Trailer trailer = new Trailer();

        trailer.setId(object.getString(id));
        trailer.setKey(object.getString(key));
        trailer.setName(object.getString(name));
        trailer.setSite(object.getString(site));
        trailer.setSize(object.getInt(size));
        trailer.setType(object.getString(type));

        return trailer;
    }

    public static String pasreTrailerToJsonString(Trailer trailer) throws Exception {
        return pasreTrailerToJsonObjst(trailer).toString();
    }

    public static JSONObject pasreTrailerToJsonObjst(Trailer trailer) throws Exception {
        JSONObject object = new JSONObject();

        object.put(id, trailer.getId());
        object.put(key, trailer.getKey());
        object.put(site, trailer.getSite());
        object.put(type, trailer.getType());
        object.put(name, trailer.getName());
        object.put(size, trailer.getSize());

        return object;
    }
}
