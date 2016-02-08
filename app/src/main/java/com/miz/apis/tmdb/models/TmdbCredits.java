package com.miz.apis.tmdb.models;

import java.util.List;

/**
 * Created by miche on 08-02-2016.
 */
public class TmdbCredits {

    List<TmdbCast> cast;
    List<TmdbCrew> crew;

    public List<TmdbCast> getCast() {
        return cast;
    }

    public List<TmdbCrew> getCrew() {
        return crew;
    }

}
