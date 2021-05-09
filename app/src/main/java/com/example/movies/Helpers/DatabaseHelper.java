package com.example.movies.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.movies.Entities.Movie;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "movies_1.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "movies";
    private static final String COL1 = "ID";
    private static final String COL2 = "movieTitle";
    private static final String COL3 = "movieYear";
    private static final String COL4 = "movieDirector";
    private static final String COL5 = "movieActorsActresses";
    private static final String COL6 = "movieRatings";
    private static final String COL7 = "movieReviews";
    private static final String COL8 = "isFavourite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "
                + TABLE_NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT, "
                + COL3 + " INTEGER, "
                + COL4 + " TEXT, "
                + COL5 + " TEXT, "
                + COL6 + " INTEGER, "
                + COL7 + " TEXT, "
                + COL8 + " INTEGER);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onUpgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(Movie movie) {
        int isFavouriteTransformed = movie.getFavourite() ? 1 : 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, movie.getMovieTitle());
        contentValues.put(COL3, movie.getMovieYear());
        contentValues.put(COL4, movie.getMovieDirector());
        contentValues.put(COL5, movie.getMovieActorsActresses());
        contentValues.put(COL6, movie.getMovieRatings());
        contentValues.put(COL7, movie.getMovieReviews());
        contentValues.put(COL8, isFavouriteTransformed);


//        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);


        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     *
     * @return
     */
    public ArrayList<Movie> getData() {
        ArrayList<Movie> movieList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        while (data.moveToNext()) {
            Integer id = data.getInt(0);
            String movieTitle = data.getString(1);
            Integer movieYear = data.getInt(2);
            String movieDirector = data.getString(3);
            String movieActorsActresses = data.getString(4);
            Integer movieRatings = data.getInt(5);
            String movieReviews = data.getString(6);
            Integer isFavourite = data.getInt(7);

            boolean isFavouriteTransformed = isFavourite == 1;

            Movie newMovie = new Movie(id, movieTitle, movieYear, movieDirector, movieActorsActresses,
                    movieRatings, movieReviews, isFavouriteTransformed);

            movieList.add(newMovie);
        }

        data.close();
        return movieList;
    }

    public ArrayList<Movie> getFavouriteMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        while (data.moveToNext()) {
            Integer id = data.getInt(0);
            String movieTitle = data.getString(1);
            Integer movieYear = data.getInt(2);
            String movieDirector = data.getString(3);
            String movieActorsActresses = data.getString(4);
            Integer movieRatings = data.getInt(5);
            String movieReviews = data.getString(6);
            Integer isFavourite = data.getInt(7);

            boolean isFavouriteTransformed = isFavourite == 1;

            Movie newMovie = new Movie(id, movieTitle, movieYear, movieDirector, movieActorsActresses,
                    movieRatings, movieReviews, isFavouriteTransformed);

            if (isFavourite == 1) movieList.add(newMovie);
        }

        data.close();
        return movieList;
    }

    /**
     * Returns only the ID that matches the name passed in
     *
     * @param
     * @return
     */
    public Cursor getItemById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        System.out.println(query);
        return data;
    }

    public void updateData(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_NAME + " SET " + COL8 + " = '" + movie.getFavourite() + "' WHERE "
                + COL1 + " = '" + movie.getID() + "'";


        Log.d(TAG, "updateName: query: " + query);
//        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void updateById(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer isFavourite = transformBoolean(movie.getFavourite());

        String query = "UPDATE " + TABLE_NAME + " SET " +
                COL2 + " = '" + movie.getMovieTitle() + "', " +
                COL3 + " = '" + movie.getMovieYear() + "', " +
                COL4 + " = '" + movie.getMovieDirector() + "', " +
                COL5 + " = '" + movie.getMovieActorsActresses() + "', " +
                COL6 + " = '" + movie.getMovieRatings() + "', " +
                COL7 + " = '" + movie.getMovieReviews() + "', " +
                COL8 + " = '" + isFavourite + "' WHERE " +
                COL1 + " = '" + movie.getID() + "'";

        Log.d(TAG, "updateFavourite: query: " + query);

        try {
            db.execSQL(query);

        } catch (SQLException e) {
            System.out.println("What happened in the sql: " + e);
        }
    }

    public void updateFavourite(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer isFavourite = transformBoolean(movie.getFavourite());

        String query = "UPDATE " + TABLE_NAME + " SET " + COL8 + " = '" + isFavourite + "' WHERE "
                + COL1 + " = '" + movie.getID() + "'";

        Log.d(TAG, "updateFavourite: query: " + query);

        try {
            db.execSQL(query);

        } catch (SQLException e) {
            System.out.println("What happened in the sql: " + e);
        }
    }

    /**
     * Returns all movies that match with the passed string
     *
     * @param
     * @return - ArrayList
     */
    public ArrayList<Movie> searchMovie(String word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Movie> movieList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL2 + " LIKE '%" + word + "%' OR "
                + COL4 + " LIKE '%" + word + "%' OR " + COL5 + " LIKE '%" + word + "%'";

        Log.d(TAG, "updateFavourite: query: " + query);

        Cursor data = db.rawQuery(query, null);

        while (data.moveToNext()) {
            Integer id = data.getInt(0);
            String movieTitle = data.getString(1);
            Integer movieYear = data.getInt(2);
            String movieDirector = data.getString(3);
            String movieActorsActresses = data.getString(4);
            Integer movieRatings = data.getInt(5);
            String movieReviews = data.getString(6);
            Integer isFavourite = data.getInt(7);

            boolean isFavouriteTransformed = isFavourite == 1;

            Movie newMovie = new Movie(id, movieTitle, movieYear, movieDirector, movieActorsActresses,
                    movieRatings, movieReviews, isFavouriteTransformed);

            movieList.add(newMovie);
        }
        return movieList;
    }

    /**
     * Delete from database
     *
     * @param id
     * @param name
     */
    public void deleteName(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    private int transformBoolean(Boolean b) {
        return b ? 1 : 0;
    }


}
