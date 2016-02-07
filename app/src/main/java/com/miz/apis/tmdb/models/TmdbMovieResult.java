package com.miz.apis.tmdb.models;

import java.util.List;

/**
 * Created by miche on 06-02-2016.
 */
public class TmdbMovieResult {

    boolean adult, video;
    String backdrop_path, original_language, original_title, overview, release_date, poster_path, title;
    List<Integer> genre_ids;
    int id, vote_count;
    double popularity, vote_average;

    public int getId() {
        return id;
    }

}
