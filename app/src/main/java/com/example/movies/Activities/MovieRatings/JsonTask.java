package com.example.movies.Activities.MovieRatings;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonToken;

import com.example.movies.Entities.IMDBMovie;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JsonTask extends AsyncTask<String, String, String> {
    ArrayList<IMDBMovie> foundMovies = new ArrayList<>();
    ArrayList<String> foundMoviesIds;
    ProgressDialog pd;
    String movieTitle = null;
    String urlMovie = null;
    String urlRating = null;
    private Context context;

    public JsonTask(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(context);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

    protected String doInBackground(String... params) {

        movieTitle = params[0];
        urlRating = "https://imdb-api.com/en/API/UserRatings/k_rfdz8bdm/";
        urlMovie = "https://imdb-api.com/en/API/SearchTitle/k_rfdz8bdm/";

        //k_rfdz8bdm k_r90gfwxo

        foundMoviesIds = new ArrayList<>();

        try {
            foundMovies = readMovies(urlMovie, movieTitle);
//            readRatings(urlRating, foundMovies);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void readRatings(String url, ArrayList<IMDBMovie> movieId) throws IOException {
        System.out.println("[JsonTask] - Reading movie ratings...");
        JsonReader reader = null;
        HttpURLConnection connection = null;
        String totalRatingVotes;

        try {
            for (IMDBMovie movie : movieId) {
                URL urlMovie = new URL(url + movie.getId());
                connection = (HttpURLConnection) urlMovie.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();

                reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));

                reader.beginObject();

                int i = 0;
                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("title") && reader.peek() != JsonToken.NULL) {
                        System.out.println("Movie: " + reader.nextString());
                    } else if (name.equals("totalRating") && reader.peek() != JsonToken.NULL) {
                        totalRatingVotes = reader.nextString();
                        foundMovies.get(i).setRating(totalRatingVotes);
                        System.out.println("Rating: " + foundMovies.get(i).getRating());
                        System.out.println(totalRatingVotes);
                        i++;
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();

//                System.out.println(ratings / totalRatingVotes);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String readRating(String movieId) throws IOException {

        String url = "https://imdb-api.com/en/API/SearchTitle/k_r90gfwxo/";
        String totalRating = null;
        System.out.println("[JsonTask] - Reading movie ratings...");
        JsonReader reader = null;
        HttpURLConnection connection = null;
        String totalRatingVotes;

        URL urlMovie = new URL(url + movieId);
        connection = (HttpURLConnection) urlMovie.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();

        try {
            reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
            reader.beginObject();

            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("title") && reader.peek() != JsonToken.NULL) {
                    System.out.println("Movie: " + reader.nextString());
                } else if (name.equals("totalRating") && reader.peek() != JsonToken.NULL) {
                    totalRatingVotes = reader.nextString();
//                        foundMovies.get(i).setRating(totalRatingVotes);
//                        System.out.println("Rating: " + foundMovies.get(i).getRating());
                    System.out.println("Read rating: " + totalRatingVotes);
//                        i++;
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();

//                System.out.println(ratings / totalRatingVotes);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return totalRating;
    }

    protected ArrayList<IMDBMovie> readMovies(String url, String movieTitle) throws IllegalStateException, IOException {
        System.out.println("[JsonTask] - Looking for movies with title '" + movieTitle + "'...");
        JsonReader reader = null;
        HttpURLConnection connection = null;
        ArrayList<IMDBMovie> movies = new ArrayList<IMDBMovie>();
        try {
            URL urlMovie = new URL(url + movieTitle);
            connection = (HttpURLConnection) urlMovie.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));

            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("results")) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        movies.add(readTitles(reader));
                    }
                    reader.endArray();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[JsonTask] - Found " + movies.size() + " movies...");
        return movies;
    }

    public IMDBMovie readTitles(JsonReader reader) throws IOException {
        String id = null;
        String title = null;
        String url = null;
        String rating = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextString();
                System.out.println("Movie id: " + id);
            } else if (name.equals("title")) {
                title = reader.nextString();
            } else if (name.equals("image")) {
                url = reader.nextString();
//                System.out.println(url);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();

        rating = readRating(id);
        System.out.println("Rating: "+rating);

        return new IMDBMovie(id, title, url, rating);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (pd.isShowing()) {
            pd.dismiss();
        }

        Intent intent = new Intent(context, FoundMoviesActivity.class);

        intent.putExtra("title", movieTitle);

        intent.putExtra("foundMovies", foundMovies);
//        intent.putExtra("ids", foundMoviesIds);
        context.startActivity(intent);
    }
}