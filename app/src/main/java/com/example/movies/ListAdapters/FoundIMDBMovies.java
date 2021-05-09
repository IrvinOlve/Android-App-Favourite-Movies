package com.example.movies.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movies.Entities.IMDBMovie;
import com.example.movies.Entities.Movie;
import com.example.movies.R;

import java.util.ArrayList;

public class FoundIMDBMovies extends ArrayAdapter<IMDBMovie> {

    private static final String TAG = "MovieListAdapter";

    private int mSelectedPosition = -1;
    private RadioButton mSelectedRB;

    ArrayList<IMDBMovie> movieList;

    Context context;
    int resource;

    ViewHolder holder;

    public FoundIMDBMovies(@NonNull Context context, int resource, @NonNull ArrayList<IMDBMovie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.movieList = objects;

    }

    private static class ViewHolder {
        TextView movieTitle;
        TextView movieRating;
    }

    @Nullable
    @Override
    public IMDBMovie getItem(int position) {
        return super.getItem(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //get the movie title
        String movieTitle = getItem(position).getTitle();
        String movieRating =  getItem(position).getRating();
        System.out.println("inside movies: " + movieRating);

        //Create the movie object with the information
        Movie movie = new Movie();

        movie.setMovieTitle(movieTitle);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();

            holder.movieTitle = (TextView) convertView.findViewById(R.id.textView3);
            holder.movieRating = (TextView) convertView.findViewById(R.id.textView1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // edit screen components
        holder.movieTitle.setText(movieTitle);
        holder.movieRating.setText(movieRating);

        return convertView;
    }

    public int getItemSelected() {
        return mSelectedPosition;
    }
}