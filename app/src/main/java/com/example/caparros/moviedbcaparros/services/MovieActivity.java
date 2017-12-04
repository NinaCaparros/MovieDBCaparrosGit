package com.example.caparros.moviedbcaparros.services;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.caparros.moviedbcaparros.R;
import com.example.caparros.moviedbcaparros.models.Movie;
import com.example.caparros.moviedbcaparros.models.Movies;

/**
 * Created by Nina on 08/11/2017.
 */

public class MovieActivity extends AppCompatActivity {
    public static String EXTRA_MOVIE = "EXTRA_MOVIE";

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_user_view);
        Intent intent = getIntent();
        Movie movie = (Movie)intent.getSerializableExtra(EXTRA_MOVIE);
        setView(movie);


    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void setView(Movie movie) {

        RecyclerView recycler = (RecyclerView) findViewById(R.id.rv_movie);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        MovieAdaptater movieAdapter = new MovieAdaptater(getApplicationContext(), movie);

        recycler.setAdapter(movieAdapter);


    }


    @Override
    public void finish() {

        super.finish();
    }
}
