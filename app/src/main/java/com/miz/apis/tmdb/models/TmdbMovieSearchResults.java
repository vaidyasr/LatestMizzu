package com.miz.apis.tmdb.models;

import java.util.List;

/**
 * Created by miche on 06-02-2016.
 */
public class TmdbMovieSearchResults {

    int page, total_results, total_pages;
    List<TmdbMovieResult> results;

    public List<TmdbMovieResult> getResults() {
        return results;
    }

}
