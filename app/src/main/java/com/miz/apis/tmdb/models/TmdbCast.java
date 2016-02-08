package com.miz.apis.tmdb.models;

/**
 * Created by miche on 08-02-2016.
 */
public class TmdbCast {

    int cast_id, id, order;
    String character, credit_id, name, profile_path;

    public int getCastId() {
        return cast_id;
    }

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public String getCharacter() {
        return character;
    }

    public String getCreditId() {
        return credit_id;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return profile_path;
    }

}
