package com.example.movies.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movies.Entities.Movie;
import com.example.movies.R;

import java.util.ArrayList;

public class MovieListAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = "MovieListAdapter";

    ArrayList<Movie> movieList;
    boolean[] checkBoxState;

    Context context;
    int resource;

    ViewHolder holder;

    public MovieListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.movieList = objects;
        checkBoxState = new boolean[objects.size()];
    }

    private static class ViewHolder {
        TextView movieTitle;
        CheckBox isFavourite;
    }

    @Nullable
    @Override
    public Movie getItem(int position) {
        return super.getItem(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String movieTitle = getItem(position).getMovieTitle();
        boolean isFavourite = getItem(position).getFavourite();

        //Create the person object with the information
        Movie movie = new Movie();

        movie.setMovieTitle(movieTitle);
        movie.setFavourite(isFavourite);

        //create the view result for showing the animation

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();

            // listen for changes in checkboxes
//            holder.isFavourite.setOnCheckedChangeListener(myCheckChangList);

            holder.movieTitle = (TextView) convertView.findViewById(R.id.textView);
            holder.isFavourite = (CheckBox) convertView.findViewById(R.id.checkBox);

            // add tag/identifier to checkbox
            holder.isFavourite.setTag(position);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // listen for changes in checkboxes
        holder.isFavourite.setOnCheckedChangeListener(myCheckChangList);

        // save state of box
        holder.isFavourite.setChecked(checkBoxState[position]);

        // edit screen components
        holder.movieTitle.setText(movieTitle);
        holder.isFavourite.setChecked(isFavourite);

        return convertView;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int pos = Integer.parseInt(buttonView.getTag().toString());
            getItem((Integer) buttonView.getTag()).setFavourite(isChecked);
            checkBoxState[pos] = isChecked;
        }
    };
}