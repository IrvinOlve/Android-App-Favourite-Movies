package com.example.movies.Activities.RegisterMovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movies.Helpers.DatabaseHelper;
import com.example.movies.Entities.Movie;
import com.example.movies.R;

public class RegisterMovieActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Movie movie;

    // Screen components
    EditText movieTitle;
    EditText movieYear;
    EditText movieDirector;
    EditText movieActorsActresses;
    EditText movieRatings;
    EditText movieReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        movieTitle = (EditText) findViewById(R.id.movieTitleTB);
        movieYear = (EditText) findViewById(R.id.movieYearTB);
        movieDirector = (EditText) findViewById(R.id.movieDirectorTB);
        movieActorsActresses = (EditText) findViewById(R.id.movieActorsActressesTB);
        movieRatings = (EditText) findViewById(R.id.movieRatingsTB);
        movieReviews = (EditText) findViewById(R.id.movieReviewsTB);

        databaseHelper = new DatabaseHelper(this);

    }

    public void dataProcessor(View view) {
        String movieTitle = this.movieTitle.getText().toString();
        String movieYear = this.movieYear.getText().toString();
        String movieDirector = this.movieDirector.getText().toString();
        String movieActorsActresses = this.movieActorsActresses.getText().toString();
        String movieRatings = this.movieRatings.getText().toString();
        String movieReviews = this.movieReviews.getText().toString();

        boolean correctYear = false;
        boolean correctRatings = false;

        if (Integer.parseInt(movieYear) <= 1895) {
            this.movieYear.setError("Year mas be greater than 1895");
        } else if (movieYear.length() != 4) {
            this.movieYear.setError("Invalid year format");
        } else {
            correctYear = true;
        }

        if (!inRange(Integer.parseInt(movieRatings), 1, 10)) {
            this.movieRatings.setError("Enter value from 1 to 10");
        } else {
            correctRatings = true;
        }

        Context context = getApplicationContext();
        CharSequence text = "Movie added";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);

        if (correctRatings && correctYear) {
            movie = new Movie(movieTitle, Integer.parseInt(movieYear), movieDirector, movieActorsActresses,
                    Integer.parseInt(movieRatings), movieReviews, false);
            addData(movie);
            toast.show();
            finish();
        }
    }

    public boolean inRange(int number, int from, int to) {
        return (number >= from && number <= to);
    }

    public void addData(Movie movie) {

        boolean request = databaseHelper.addData(movie);

        if (request) System.out.println("Good");
        else System.out.println("what happened");

    }

}