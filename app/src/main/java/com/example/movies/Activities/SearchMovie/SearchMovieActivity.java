package com.example.movies.Activities.SearchMovie;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.movies.Entities.Movie;
import com.example.movies.Helpers.DatabaseHelper;
import com.example.movies.ListAdapters.MovieListAdapter_Search;
import com.example.movies.R;

import java.util.ArrayList;

public class SearchMovieActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    Button searchButton;
    EditText movieToSearch;
    TextView txt1;
    ListView movieList;

    ArrayList<Movie> foundMovies;
    ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        movieToSearch = (EditText) findViewById(R.id.textBox);
        movieList = (ListView) findViewById(R.id.movieList);
        txt1 = (TextView) findViewById(android.R.id.text1);

        databaseHelper = new DatabaseHelper(this);

    }

    public void getData(View view) {

        // movie that user typed in text box that intends to search
        String movie = movieToSearch.getText().toString();

        System.out.println("Looking for movie: " + movie);

        this.foundMovies = databaseHelper.searchMovie(movie);

        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, this.foundMovies);
        movieList.setAdapter(listAdapter);
    }
}