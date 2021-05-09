package com.example.movies.Activities.MovieRatings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.movies.Helpers.DatabaseHelper;
import com.example.movies.ListAdapters.MovieListAdapter_Search;
import com.example.movies.Entities.Movie;
import com.example.movies.R;

import java.util.ArrayList;

public class RatingsActivity extends AppCompatActivity {
    ArrayList<Movie> movieList;
    DatabaseHelper databaseHelper;
    // Screen components
    ListView mListView;

    MovieListAdapter_Search adapter;
    RadioButton mRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        mListView = (ListView) findViewById(R.id.mListView);
        mRadioButton = (RadioButton) findViewById(R.id.radioButton);

        databaseHelper = new DatabaseHelper(this);
        movieList = new ArrayList<>();

        // get movies from db as ArrayList<Movie>
        movieList = databaseHelper.getData();

        // set ListView ready for ArrayList<Movie> movieList
        adapter = new MovieListAdapter_Search(this, R.layout.adapter_find_movie_ratings, movieList);
        mListView.setAdapter(adapter);
    }

    public void requestData(View view) {

        // get selected item's title from movieList
        String movieTitle = movieList.get(adapter.getItemSelected()).getMovieTitle();

        // create JsonReader
        JsonTask JsonReader = new JsonTask(RatingsActivity.this);

        // read url
        JsonReader.execute(movieTitle);
        System.out.println("Status: " + JsonReader.getStatus());
    }


}