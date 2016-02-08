package com.miz.apis.tmdb.models;

import java.util.List;

/**
 * Created by miche on 08-02-2016.
 */
public class TmdbReleaseDateInfo {

    String iso_3166_1;
    List<TmdbReleaseDate> release_dates;

    public String getIso3166_1() {
        return iso_3166_1;
    }

    public List<TmdbReleaseDate> getReleaseDates() {
        return release_dates;
    }

}
