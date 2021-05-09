package com.example.movies.Activities.EditMovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.movies.Helpers.DatabaseHelper;
import com.example.movies.Entities.Movie;
import com.example.movies.R;

public class EditMovieActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Movie movie;

    // Screen components
    EditText movieTitleET;
    EditText movieYearET;
    EditText movieDirectorET;
    EditText movieActorsActressesET;
    EditText movieRatingsET;
    EditText movieReviewsET;
    RatingBar movieRatingRB;
    CheckBox isFavouriteCB;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        // initialise database helper
        databaseHelper = new DatabaseHelper(this);

        // initialise screen components
        movieTitleET = (EditText) findViewById(R.id.movieTitleTB);
        movieYearET = (EditText) findViewById(R.id.movieYearTB);
        movieDirectorET = (EditText) findViewById(R.id.movieDirectorTB);
        movieActorsActressesET = (EditText) findViewById(R.id.movieActorsActressesTB);
        movieRatingRB = (RatingBar) findViewById(R.id.movieRatingRB);
        movieReviewsET = (EditText) findViewById(R.id.movieReviewsTB);
        isFavouriteCB = (CheckBox) findViewById(R.id.checkBox13);
        backButton = (Button) findViewById(R.id.backButton);

        // retrieve object with data from previous activity
        movie = (Movie) getIntent().getSerializableExtra("movies");

        // update screen components with object values
        movieTitleET.setText(movie.getMovieTitle());
        movieYearET.setText(String.valueOf(movie.getMovieYear()));
        movieDirectorET.setText(movie.getMovieDirector());
        movieActorsActressesET.setText(movie.getMovieActorsActresses());
        movieRatingRB.setRating(movie.getMovieRatings());

        movieRatingRB.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (rating < 1.0f)
                ratingBar.setRating(1.0f);
        });

        isFavouriteCB.setChecked(movie.getFavourite());

        CompoundButton.OnCheckedChangeListener myCheckChangList = (buttonView, isChecked) -> {
            if (isChecked) buttonView.setText("Favourite");
            else buttonView.setText("Not Favourite");
        };

        isFavouriteCB.setOnCheckedChangeListener(myCheckChangList);


        if (movie.getFavourite()) isFavouriteCB.setText("Favourite");
        else isFavouriteCB.setText("Not Favourite");
        movieReviewsET.setText(movie.getMovieReviews());
    }

    public void dataProcessor(View view) {

        Context context = getApplicationContext();
        CharSequence text = "Movie updated";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);

        // Pass object, update its values and return it.
        Movie updatedMovie = setData(movie);

        boolean correctYear = false;
        boolean correctRatings = false;

        if (updatedMovie.getMovieYear() <= 1895) {
            this.movieYearET.setError("Year mas be greater than 1895");
        } else {
            correctYear = true;
        }

        if (!inRange(updatedMovie.getMovieRatings(), 1, 10)) {
            this.movieRatingsET.setError("Enter value from 1 to 10");
        } else {
            correctRatings = true;
        }

        if (correctRatings && correctYear) {
            databaseHelper.updateById(movie);
            toast.show();
            finish();
        }

    }

    public void goBack(View view) {
        finish();
    }


    public boolean inRange(int number, int from, int to) {
        return (number >= from && number <= to);
    }

    public Movie setData(Movie movie) {
        //        Integer ID = movie.getID();
        String movieTitle = movieTitleET.getText().toString();
        String movieYearString = movieYearET.getText().toString();
        String movieDirector = movieDirectorET.getText().toString();
        String movieActorsActresses = movieActorsActressesET.getText().toString();
        Boolean isFavourite = isFavouriteCB.isChecked();
        Float movieRatingFloat = movieRatingRB.getRating();
        String movieReviews = movieReviewsET.getText().toString();

        Integer movieRating = movieRatingFloat.intValue();
        Integer movieYear = Integer.parseInt(movieYearString);

        movie.setMovieTitle(movieTitle);
        movie.setMovieYear(movieYear);
        movie.setMovieDirector(movieDirector);
        movie.setMovieActorsActresses(movieActorsActresses);
        movie.setMovieRatings(movieRating);
        movie.setMovieReviews(movieReviews);
        movie.setFavourite(isFavourite);
        return movie;

    }

}