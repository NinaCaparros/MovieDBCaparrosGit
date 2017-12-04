package com.example.caparros.moviedbcaparros.services;

/**
 * Created by Nina on 15/11/2017.
 */

import com.example.caparros.moviedbcaparros.models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<Movie.MovieResult> getPopularMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<Movie.MovieResult> searchMovies(@Query("api_key") String apiKey, @Query("query") String recherche);

}
