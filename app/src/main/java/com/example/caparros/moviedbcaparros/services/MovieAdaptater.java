package com.example.caparros.moviedbcaparros.services;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.caparros.moviedbcaparros.R;
import com.example.caparros.moviedbcaparros.SingleMovieActivity;
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
    private boolean mAll;

    public MovieAdaptater(Context context, Movies movies) {
        mContext = context;
        mMovies=movies;
        mAll=true;
    }

    public MovieAdaptater(Context context, Movie movie){
        mContext=context;
        mMovies.addMovie(movie);
        mAll=true;
    }
    public MovieAdaptater(Context context, Movies movies, boolean all) {
        mContext = context;
        mMovies=movies;
        mAll=all;
    }


    public MovieAdaptater(Context context, Movie movie, boolean all){
        mContext=context;
        mMovies.addMovie(movie);
        mAll=all;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_user_view, viewGroup, false);

        return new MovieViewHolder(view, mAll);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position){

        final Movie movie = mMovies.getMovies().get(position);

        View view = holder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SingleMovieActivity.class);
                Bundle extras = new Bundle();
                extras.putString(SingleMovieActivity.EXTRA_MOVIE_TITLE,movie.getTitle());
                extras.putString(SingleMovieActivity.EXTRA_MOVIE_RESUME,movie.getDescription());
                extras.putString(SingleMovieActivity.EXTRA_MOVIE_IMAGE,movie.getPoster());
                intent.putExtras(extras);
                view.getContext().startActivity(intent);
            }
        });
        if(mAll)
        {
            holder.tv_title.setText(String.valueOf(movie.getTitle()));

            holder.tv_description.setText(String.valueOf(movie.getDescription()));

        }
        Picasso.with(mContext).load(movie.getPoster()).into(holder.iv_poster);


    }
    @Override
    public int getItemCount(){
        return mMovies.getMovies().size();

    }


}
