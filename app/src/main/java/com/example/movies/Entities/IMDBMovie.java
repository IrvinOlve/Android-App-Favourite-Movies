package com.example.movies.Entities;

import java.io.Serializable;

public class IMDBMovie implements Serializable {
    String id;
    String title;
    String url;
    String rating;


    public IMDBMovie(String id, String title, String url, String rating) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String toString() {
        return title;
    }
}