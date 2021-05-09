package com.example.movies.Activities.MainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.movies.Activities.DisplayFavouriteMovies.DisplayFavouritesActivity;
import com.example.movies.Activities.DisplayAllMovies.DisplayMoviesActivity;
import com.example.movies.Activities.EditMovies.EditMoviesActivity;
import com.example.movies.Activities.MovieRatings.RatingsActivity;
import com.example.movies.Activities.RegisterMovie.RegisterMovieActivity;
import com.example.movies.Activities.SearchMovie.SearchMovieActivity;
import com.example.movies.R;

public class MainActivity extends AppCompatActivity {

    Intent RegisterMovieIntent;
    Intent DisplayMoviesIntent;
    Intent DisplayFavouritesIntent;
    Intent EditMoviesIntent;
    Intent SearchMovieIntent;
    Intent RatingsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RegisterMovieIntent = new Intent(this, RegisterMovieActivity.class);
        DisplayMoviesIntent = new Intent(this, DisplayMoviesActivity.class);
        DisplayFavouritesIntent = new Intent(this, DisplayFavouritesActivity.class);
        EditMoviesIntent = new Intent(this, EditMoviesActivity.class);
        SearchMovieIntent = new Intent(this, SearchMovieActivity.class);
        RatingsIntent = new Intent(this, RatingsActivity.class);
    }

    public void registerMovieActivity(View view) {
        startActivity(RegisterMovieIntent);
    }

    public void displayMoviesActivity(View view) {
        startActivity(DisplayMoviesIntent);
    }

    public void displayFavourites(View view) {
        startActivity(DisplayFavouritesIntent);
    }

    public void editMovies(View view) {
        startActivity(EditMoviesIntent);
    }

    public void searchMovies(View view) {
        startActivity(SearchMovieIntent);
    }

    public void movieRatings(View view) {
        startActivity(RatingsIntent);
    }


}