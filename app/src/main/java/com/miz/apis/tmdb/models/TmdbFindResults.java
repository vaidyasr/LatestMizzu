package com.miz.apis.tmdb.models;

import java.util.List;

/**
 * Created by miche on 06-02-2016.
 */
public class TmdbFindResults {

    List<TmdbMovieResult> movie_results;

    /**
     * TODO Implement these:
     * person_results, tv_results, tv_episode_results, tv_season_results
     */

    public List<TmdbMovieResult> getMovieResults() {
        return movie_results;
    }

}
