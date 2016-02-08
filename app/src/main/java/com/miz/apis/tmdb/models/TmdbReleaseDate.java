package com.miz.apis.tmdb.models;

/**
 * Created by miche on 08-02-2016.
 */
public class TmdbReleaseDate {

    String certification, iso_639_1, note, release_date;
    int type;

    public String getCertification() {
        return certification;
    }

    public String getIso639_1() {
        return iso_639_1;
    }

    public String getNote() {
        return note;
    }

    /**
     * Get release date in standard ISO 8601 format, i.e.
     * 1970-01-01T00:00:00.000Z.
     *
     * @return
     */
    public String getReleaseDate() {
        return release_date;
    }

    /**
     * Returns the type of release, using the following keys:
     * 1. Premiere
     * 2. Theatrical (limited)
     * 3. Theatrical
     * 4. Digital
     * 5. Physical
     * 6. TV
     *
     * @return
     */
    public int getType() {
        return type;
    }

}
