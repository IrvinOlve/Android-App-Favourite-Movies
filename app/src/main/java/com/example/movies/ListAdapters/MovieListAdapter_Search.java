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

import com.example.movies.Entities.Movie;
import com.example.movies.R;

import java.util.ArrayList;

public class MovieListAdapter_Search extends ArrayAdapter<Movie> {

    private static final String TAG = "MovieListAdapter";

    private int mSelectedPosition = -1;
    private RadioButton mSelectedRB;

    ArrayList<Movie> movieList;

    Context context;
    int resource;

    ViewHolder holder;

    public MovieListAdapter_Search(@NonNull Context context, int resource, @NonNull ArrayList<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.movieList = objects;

    }

    private static class ViewHolder {
        TextView movieTitle;
        RadioButton isSelected;
    }

    @Nullable
    @Override
    public Movie getItem(int position) {
        return super.getItem(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //get the movie title
        String movieTitle = getItem(position).getMovieTitle();

        //Create the movie object with the information
        Movie movie = new Movie();

        movie.setMovieTitle(movieTitle);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();

            holder.movieTitle = (TextView) convertView.findViewById(R.id.textView);
            holder.isSelected = (RadioButton) convertView.findViewById(R.id.radioButton);

            // add tag/identifier to checkbox
            holder.isSelected.setTag(position);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // set listener to radio button
        holder.isSelected.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (position != mSelectedPosition && mSelectedRB != null) {
                    mSelectedRB.setChecked(false); // uncheck the last one
                }

                mSelectedPosition = position; // change the item selected index
                mSelectedRB = (RadioButton) v; // assign the new item selected
            }
        });

        // swap selected radio button
        if (mSelectedPosition != position) {
            holder.isSelected.setChecked(false);
        } else {
            holder.isSelected.setChecked(true);
            if (mSelectedRB != null && holder.isSelected != mSelectedRB) {
                mSelectedRB = holder.isSelected;
            }
        }

        // edit screen components
        holder.movieTitle.setText(movieTitle);

        return convertView;
    }

    public int getItemSelected() {
        return mSelectedPosition;
    }
}