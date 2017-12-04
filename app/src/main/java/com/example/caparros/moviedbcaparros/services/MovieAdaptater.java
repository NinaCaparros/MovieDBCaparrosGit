package com.example.caparros.moviedbcaparros.services;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.caparros.moviedbcaparros.R;
import com.example.caparros.moviedbcaparros.models.Movie;
import com.example.caparros.moviedbcaparros.models.Movies;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Created by Nina on 08/11/2017.
 */

public class MovieAdaptater extends RecyclerView.Adapter<MovieViewHolder>{


    private Context mContext;
    private Movies mMovies;

    public MovieAdaptater(Context context, Movies movies) {
        mContext = context;
        mMovies=movies;
    }

    public MovieAdaptater(Context context, Movie movie){
        mContext=context;
        mMovies.addMovie(movie);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_user_view, viewGroup, false);



        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position){

        final Movie movie = mMovies.getMovies().get(position);

        View view = holder.itemView;
        holder.tv_title.setText(movie.getTitle());
        holder.tv_description.setText(movie.getDescription());

        Picasso.with(mContext).load(movie.getPoster()).into(holder.iv_poster);

       view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MovieActivity.class);
                intent.putExtra(MovieActivity.EXTRA_MOVIE,movie);
                view.getContext().startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount(){
        return mMovies.getMovies().size();

    }


}
