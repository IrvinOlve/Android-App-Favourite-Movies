package com.example.movies.Activities.DisplayFavouriteMovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.movies.Helpers.DatabaseHelper;
import com.example.movies.ListAdapters.MovieListAdapter;
import com.example.movies.Entities.Movie;
import com.example.movies.R;

import java.util.ArrayList;

public class DisplayFavouritesActivity extends AppCompatActivity {
    ArrayList<Movie> movieList;
    DatabaseHelper databaseHelper;

    // Screen components
    ListView mListView;

    MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_favourites);

        mListView = (ListView) findViewById(R.id.mListView);
        databaseHelper = new DatabaseHelper(this);

        // retrieve dat and save in movieList.
        getData();
    }

    void getData() {
        movieList = databaseHelper.getFavouriteMovies();

        adapter = new MovieListAdapter(this, R.layout.adapter_view_layout, movieList);

        mListView.setAdapter(adapter);
    }

    public void updateFavourites(View view) {

        Context context = getApplicationContext();
        CharSequence text = "Movies updated";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);


        for (int i = 0; i < movieList.size(); i++) {

            // get movie object from list
            Movie movieInList = adapter.getItem(i);

            // update isFavourite in database
            databaseHelper.updateFavourite(movieInList);
        }


        toast.show();
        finish();

    }
}