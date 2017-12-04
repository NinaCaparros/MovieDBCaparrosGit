package com.example.caparros.moviedbcaparros.models;

import java.util.List;

/**
 * Created by Nina on 08/11/2017.
 */

public class Movies {

    public List<Movie> movies = null;

    public List<Movie> getMovies(){

        return movies;
    }

    public void setMovies(List<Movie> movies){
        this.movies=movies;
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }
}
