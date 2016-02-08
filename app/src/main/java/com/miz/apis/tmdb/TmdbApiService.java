package com.miz.apis.tmdb;

import com.miz.apis.tmdb.models.TmdbConfiguration;
import com.miz.apis.tmdb.models.TmdbFindResults;
import com.miz.apis.tmdb.models.TmdbMovie;
import com.miz.apis.tmdb.models.TmdbMovieImages;
import com.miz.apis.tmdb.models.TmdbMovieSearchResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by miche on 06-02-2016.
 */
public interface TmdbApiService {

    /**
     * Search for movies by title.
     *
     * @param apiKey TMDb API key.
     * @param query CGI escaped string.
     * @param page Minimum 1, maximum 1000.
     * @param language 	ISO 639-1 code.
     * @param includeAdult Toggle the inclusion of adult titles. Expected value is: true or false.
     * @param year 	Filter the results release dates to matches that include this value.
     * @param primaryReleaseYear Filter the results so that only the primary release dates have this value.
     * @return
     */
    @GET("search/movie")
    Call<TmdbMovieSearchResults> search(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("include_adult") Boolean includeAdult,
            @Query("year") Integer year,
            @Query("primary_release_year") Integer primaryReleaseYear);

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId Id of the movie to retrieve information for.
     * @param apiKey TMDb API key.
     * @param language ISO 639-1 code.
     * @param appendToResponse Comma separated, any movie method.
     * @return
     */
    @GET("movie/{id}")
    Call<TmdbMovie> getMovie(
            @Path("id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("append_to_response") String appendToResponse);

    /**
     * Get the full movie information for a specific movie id,
     * including alternative titles, credits, images, release
     * dates, videos (trailers) and similar movies.
     *
     * @param movieId Id of the movie to retrieve information for.
     * @param apiKey TMDb API key.
     * @param language ISO 639-1 code.
     * @return
     */
    @GET("movie/{id}?append_to_response=alternative_titles,credits,images,release_dates,videos,similar")
    Call<TmdbMovie> getFullMovie(
            @Path("id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language);

    /**
     * Get the images (posters and backdrops) for a specific movie id.
     *
     * @param movieId Id of the movie to retrieve images for.
     * @param apiKey TMDb API key.
     * @param language ISO 639-1 code.
     * @param appendToResponse Comma separated, any movie method.
     * @param imageLanguagesToInclude Comma separated, a valid ISO 69-1. Maximum 5 per request.
     * @return
     */
    @GET("movie/{id}/images")
    Call<List<TmdbMovieImages>> getMovieImages(
            @Path("id") String movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("append_to_response") String appendToResponse,
            @Query("include_image_language") String imageLanguagesToInclude);

    /**
     * The find method makes it easy to search for objects in our database by an external id.
     * For instance, an IMDB ID. This will search all objects (movies, TV shows and people) and
     * return the results in a single response. The supported external sources for each object
     * are as follows:
     *
     * Movies: imdb_id
     * People: imdb_id, freebase_mid, freebase_id, tvrage_id
     * TV Series: imdb_id, freebase_mid, freebase_id, tvdb_id, tvrage_id
     * TV Seasons: freebase_mid, freebase_id, tvdb_id, tvrage_id
     * TV Episodes: imdb_id, freebase_mid, freebase_id, tvdb_id, tvrage_id
     *
     * @param externalId External ID of the object you're looking for.
     * @param apiKey TMDb API key.
     * @param externalSource External source for the object.
     * @param language ISO 639-1 code.
     * @return
     */
    @GET("find/{id}")
    Call<TmdbFindResults> find(
            @Path("id") String externalId,
            @Query("api_key") String apiKey,
            @Query("external_source") String externalSource,
            @Query("language") String language);

    /**
     * Get the system wide configuration information.
     *
     * @param apiKey TMDb API key.
     * @return
     */
    @GET("configuration")
    Call<TmdbConfiguration> getConfiguration(
            @Query("api_key") String apiKey);

}
