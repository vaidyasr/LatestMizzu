package com.miz.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("NewApi")
public class DatabaseHelper extends SQLiteOpenHelper {

	protected static final String TAG = "Mizuu";

	public static final String DATABASE_NAME = "database.db";
	protected static final int DATABASE_VERSION = 1;

	/**
	 * Create movie table SQL statement
	 */
	private static final String DATABASE_CREATE_MOVIE = "create table movie (tmdbid TEXT PRIMARY KEY, title TEXT, plot TEXT," +
			"imdbid TEXT, rating TEXT, tagline TEXT, release TEXT, certification TEXT, runtime TEXT, trailer TEXT, genres TEXT," +
			"favourite INTEGER, actors TEXT, to_watch TEXT, has_watched TEXT, date_added TEXT, collection_id TEXT);";
	private static final String DATABASE_CREATE_MOVIE_TITLE_INDEX = "create index movie_title_index on " + DbAdapterMovies.DATABASE_TABLE +
			" (" + DbAdapterMovies.KEY_TITLE + ");";
	
	/**
	 * Create movie filepath table SQL statements
	 */
	private static final String DATABASE_CREATE_MOVIE_MAPPING = "create table " + DbAdapterMovieMappings.DATABASE_TABLE + " (" +
			DbAdapterMovieMappings.KEY_FILEPATH + " TEXT, " + DbAdapterMovieMappings.KEY_TMDB_ID + " TEXT, " + DbAdapterMovieMappings.KEY_IGNORED + " INTEGER);";
	private static final String DATABASE_CREATE_TMDB_ID_INDEX = "create index tmdbid_index on " + DbAdapterMovieMappings.DATABASE_TABLE +
			" (" + DbAdapterMovieMappings.KEY_TMDB_ID + ");";

	/**
	 * Create collections table SQL statements
	 */
	private static final String DATABASE_CREATE_MOVIE_COLLECTIONS = "create table " + DbAdapterCollections.DATABASE_TABLE +
			" (" + DbAdapterCollections.KEY_COLLECTION_ID + " TEXT PRIMARY KEY, " + DbAdapterCollections.KEY_COLLECTION + " TEXT);";

	/**
	 * Create TV show table SQL statements
	 */
	private static final String DATABASE_CREATE_TV_SHOWS = "create table tvshows (_id INTEGER PRIMARY KEY AUTOINCREMENT, show_id TEXT," +
			"show_title TEXT, show_description TEXT, show_actors TEXT, show_genres TEXT, show_rating TEXT, show_certification TEXT," +
			"show_runtime TEXT, show_first_airdate TEXT, favourite TEXT);";
	private static final String DATABASE_CREATE_SHOW_ID_INDEX = "create index show_id_index on tvshows (show_id);";

	/**
	 * Create TV show episodes table SQL statements
	 */
	private static final String DATABASE_CREATE_TV_SHOWS_EPISODES = "create table tvshow_episodes (season TEXT, episode TEXT, show_id TEXT," +
			"episode_title TEXT, episode_description TEXT, episode_airdate TEXT, episode_rating TEXT, episode_director TEXT, episode_writer TEXT," +
			"episode_gueststars TEXT, date_added TEXT, to_watch TEXT, has_watched TEXT, favourite TEXT);";
	private static final String DATABASE_CREATE_EPISODE_SHOW_ID_INDEX = "create index episode_show_id_index on tvshow_episodes(show_id);";

	/**
	 * Create TV show episodes filepath table SQL statements
	 */
	private static final String DATABASE_CREATE_TV_SHOWS_EPISODES_MAPPING = "create table " + DbAdapterTvShowEpisodeMappings.DATABASE_TABLE + " (" +
			DbAdapterTvShowEpisodeMappings.KEY_FILEPATH + " TEXT, " + DbAdapterTvShowEpisodeMappings.KEY_SHOW_ID + " TEXT, " +
			DbAdapterTvShowEpisodeMappings.KEY_SEASON + " TEXT, " + DbAdapterTvShowEpisodeMappings.KEY_EPISODE + " TEXT, " +
			DbAdapterTvShowEpisodeMappings.KEY_IGNORED + " INTEGER);";

	/**
	 * Create file sources table SQL statement
	 */
	private static final String DATABASE_CREATE_FILESOURCES = "create table sources (_id INTEGER PRIMARY KEY AUTOINCREMENT, filepath TEXT," +
			"type TEXT, is_smb INTEGER, user TEXT, password TEXT, domain TEXT);";

	private static DatabaseHelper mInstance;

	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static synchronized DatabaseHelper getHelper(Context context) {
		if (mInstance == null)
			mInstance = new DatabaseHelper(context);
		return mInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// Movie table and index
		database.execSQL(DATABASE_CREATE_MOVIE);
		database.execSQL(DATABASE_CREATE_MOVIE_TITLE_INDEX);

		// Movie filepath mapping table and index
		database.execSQL(DATABASE_CREATE_MOVIE_MAPPING);
		database.execSQL(DATABASE_CREATE_TMDB_ID_INDEX);

		// Movie collections
		database.execSQL(DATABASE_CREATE_MOVIE_COLLECTIONS);

		// TV show table and index
		database.execSQL(DATABASE_CREATE_TV_SHOWS);
		database.execSQL(DATABASE_CREATE_SHOW_ID_INDEX);

		// TV show episode table and index
		database.execSQL(DATABASE_CREATE_TV_SHOWS_EPISODES);
		database.execSQL(DATABASE_CREATE_EPISODE_SHOW_ID_INDEX);

		// TV show episode filepath table
		database.execSQL(DATABASE_CREATE_TV_SHOWS_EPISODES_MAPPING);

		// File source table
		database.execSQL(DATABASE_CREATE_FILESOURCES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// Not in use at the moment
	}
}