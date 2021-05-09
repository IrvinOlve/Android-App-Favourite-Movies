package com.example.movies.Activities.EditMovies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.movies.Helpers.DatabaseHelper;
import com.example.movies.Entities.Movie;
import com.example.movies.R;

import java.util.ArrayList;
import java.util.Comparator;

public class EditMoviesActivity extends AppCompatActivity {

    ListView mListView;
    DatabaseHelper databaseHelper;

    ArrayList<String> movieList;
    ArrayList<Movie> _movieList;

    ArrayAdapter adapter;
    private boolean reloadNeed = true;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movies);

        mListView = (ListView) findViewById(R.id.mListView);
        databaseHelper = new DatabaseHelper(this);
        getData();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();

        getData();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void getData() {
        ArrayList<Movie> _movieList = databaseHelper.getData();
//        _movieList = new ArrayList<>();

//        while (data.moveToNext()) {
//            /* Could use getColumnIndexOrThrow() to
//            get indexes */
//
//            Integer id = data.getInt(0);
//            String movieTitle = data.getString(1);
//            Integer movieYear = data.getInt(2);
//            String movieDirector = data.getString(3);
//            String movieActorsActresses = data.getString(4);
//            Integer movieRatings = data.getInt(5);
//            String movieReviews = data.getString(6);
//            Integer isFavourite = data.getInt(7);
//
//            System.out.println("[" + id + "] - " + movieTitle);
//
//            boolean isFavouriteTransformed = isFavourite == 1;
//
//            Movie newMovie = new Movie(id, movieTitle, movieYear, movieDirector, movieActorsActresses,
//                    movieRatings, movieReviews, isFavouriteTransformed);
//
//            _movieList.add(newMovie);
//        }
//        data.close();

        _movieList.sort(Comparator.comparing(Movie::getMovieTitle));

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, _movieList);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener((parent, v, position, id) -> {
            Intent appInfo = new Intent(EditMoviesActivity.this, EditMovieActivity.class);
            Movie movie = (Movie) mListView.getItemAtPosition(position);
            appInfo.putExtra("movies", movie);
            startActivity(appInfo);
        });
    }


}