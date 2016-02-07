package com.miz.apis.tmdb.models;

import java.util.List;

/**
 * Created by miche on 06-02-2016.
 */
public class TmdbImagesConfiguration {

    String base_url, secure_base_url;
    List<String> backdrop_sizes, logo_sizes, poster_sizes, profile_sizes, still_sizes;

    public String getBaseUrl() {
        return base_url;
    }

    public String getSecureBaseUrl() {
        return secure_base_url;
    }

    public List<String> getBackdropSizes() {
        return backdrop_sizes;
    }

    public String getAppropriateBackdropSize() {
        return "w1280"; // TODO Hardcoded for now - should be specific for each device
    }

    public String getBackdropUrl() {
        return getBaseUrl() + "/" + getAppropriateBackdropSize();
    }

    public List<String> getLogoSizes() {
        return logo_sizes;
    }

    public List<String> getPosterSizes() {
        return poster_sizes;
    }

    public String getAppropriatePosterSize() {
        return "w500"; // TODO Hardcoded for now - should be specific for each device
    }

    public String getPosterUrl() {
        return getBaseUrl() + "/" + getAppropriatePosterSize();
    }

    public List<String> getProfileSizes() {
        return profile_sizes;
    }

    public List<String> getStillSizes() {
        return still_sizes;
    }

}
