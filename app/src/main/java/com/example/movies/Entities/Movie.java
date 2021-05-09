package com.example.movies.Entities;

import java.io.Serializable;

public class Movie implements Serializable {
    private Integer ID;
    private String movieTitle;
    private Integer movieYear;
    private String movieDirector;
    private String movieActorsActresses;
    private Integer movieRatings;
    private String movieReviews;
    private Boolean isFavourite;

    @Override
    public String toString() {
        return movieTitle;
    }

    public Movie() {

    }


    public Movie(Integer ID, String movieTitle, Integer movieYear, String movieDirector, String movieActorsActresses, Integer movieRatings,
                 String movieReviews, boolean isFavourite) {
        this.ID = ID;
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.movieDirector = movieDirector;
        this.movieActorsActresses = movieActorsActresses;
        this.movieRatings = movieRatings;
        this.movieReviews = movieReviews;
        this.isFavourite = isFavourite;

    }

    public Movie(String movieTitle, Integer movieYear, String movieDirector, String movieActorsActresses, Integer movieRatings,
                 String movieReviews, boolean isFavourite) {
        this.ID = ID;
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.movieDirector = movieDirector;
        this.movieActorsActresses = movieActorsActresses;
        this.movieRatings = movieRatings;
        this.movieReviews = movieReviews;
        this.isFavourite = isFavourite;

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public void setMovieActorsActresses(String movieActorsActresses) {
        this.movieActorsActresses = movieActorsActresses;
    }

    public void setMovieRatings(int movieRatings) {
        this.movieRatings = movieRatings;
    }

    public void setMovieReviews(String movieReviews) {
        this.movieReviews = movieReviews;
    }

    public int getID() {
        return ID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public String getMovieActorsActresses() {
        return movieActorsActresses;
    }

    public int getMovieRatings() {
        return movieRatings;
    }

    public String getMovieReviews() {
        return movieReviews;
    }

    public void setFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public boolean getFavourite() {
        return isFavourite;
    }


}


