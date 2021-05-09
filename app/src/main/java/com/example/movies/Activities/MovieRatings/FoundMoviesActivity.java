package com.example.movies.Activities.MovieRatings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.movies.Entities.IMDBMovie;
import com.example.movies.ListAdapters.FoundIMDBMovies;
import com.example.movies.R;

import java.util.ArrayList;

public class FoundMoviesActivity extends AppCompatActivity {
    ArrayList<IMDBMovie> foundMovies;
    ArrayList<String> foundMoviesIds;
    TextView movieTitleTV;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_movies);

        Intent intent = getIntent();
        foundMovies = (ArrayList<IMDBMovie>) intent.getSerializableExtra("foundMovies");
//        foundMoviesIds = getIntent().getStringArrayListExtra("ids");

        for (IMDBMovie m : foundMovies) {
            System.out.println(m.getRating());
        }

        String title = getIntent().getStringExtra("title");

        movieTitleTV = findViewById(R.id.movieTitleTV);
        listView = findViewById(R.id.movieList);

        movieTitleTV.setText(title);
        FoundIMDBMovies adapter = new FoundIMDBMovies(this, R.layout.adapter_found_imdb_movies, foundMovies);
        listView.setAdapter(adapter);
    }
}