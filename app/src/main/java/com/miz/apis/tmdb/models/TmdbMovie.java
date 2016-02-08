package com.miz.apis.tmdb.models;

import java.util.List;

/**
 * Created by miche on 06-02-2016.
 */
public class TmdbMovie {

    boolean adult, video;
    String backdrop_path, homepage, imdb_id, original_language, original_title,
    overview, poster_path, release_date, status, tagline, title;
    long budget, revenue;
    TmdbMovieCollection belongs_to_collection;
    List<TmdbGenre> genres;
    int id, runtime, vote_count;
    double popularity, vote_average;
    List<TmdbProductionCompany> production_companies;
    List<TmdbProductionCountry> production_countries;
    List<TmdbLanguage> spoken_languages;
    TmdbAlternativeTitles alternative_titles;
    TmdbCredits credits;
    TmdbMovieImages images;
    TmdbReleaseDates release_dates;
    TmdbVideos videos;
    TmdbSimilarMovies similar;

    public int getId() {
        return id;
    }

    public String getPoster() {
        return poster_path;
    }

    public String getBackdrop() {
        return backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getImdbId() {
        return imdb_id;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public int getRuntime() {
        return runtime;
    }

    public long getBudget() {
        return budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public boolean isAdult() {
        return adult;
    }

    public boolean isVideo() {
        return video;
    }

    public TmdbMovieCollection getCollection() {
        return belongs_to_collection;
    }

    public String getHomepage() {
        return homepage;
    }

    public List<TmdbGenre> getGenres() {
        return genres;
    }

    public List<TmdbProductionCompany> getProductionCompanies() {
        return production_companies;
    }

    public List<TmdbProductionCountry> getProductionCountries() {
        return production_countries;
    }

    public List<TmdbLanguage> getSpokenLanguages() {
        return spoken_languages;
    }

    public TmdbAlternativeTitles getAlternativeTitles() {
        return alternative_titles;
    }

    public TmdbCredits getCredits() {
        return credits;
    }

    public TmdbMovieImages getImages() {
        return images;
    }

    public TmdbReleaseDates getReleaseDates() {
        return release_dates;
    }

    public TmdbVideos getVideos() {
        return videos;
    }

    public TmdbSimilarMovies getSimilarMovies() {
        return similar;
    }

}
