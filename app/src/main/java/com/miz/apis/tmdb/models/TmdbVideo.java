package com.miz.apis.tmdb.models;

/**
 * Created by miche on 08-02-2016.
 */
public class TmdbVideo {

    String id, iso_639_1, key, name, site, type;
    int size;

    public String getId() {
        return id;
    }

    public String getIso639_1() {
        return iso_639_1;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

}
