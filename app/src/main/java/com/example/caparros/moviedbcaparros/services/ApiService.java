package com.example.caparros.moviedbcaparros.services;

/**
 * Created by Nina on 15/11/2017.
 */

import com.example.caparros.moviedbcaparros.models.Movie;
import com.example.caparros.moviedbcaparros.models.TVshow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<Movie.MovieResult> getPopularMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<Movie.MovieResult> searchMovies(@Query("api_key") String apiKey, @Query("query") String recherche);

    @GET("movie/top_rated")
    Call<Movie.MovieResult> getTopRatedMovies(@Query("api_key") String apiKey,@Query("language") String language);

    @GET("movie/now_playing")
    Call<Movie.MovieResult> getNowPlayingMovies(@Query("api_key") String apiKey,@Query("language") String language);


    @GET("tv/popular")
    Call<TVshow.TVshowResult> getPopularTvshows(@Query("api_key") String apiKey);

    @GET("search/tv")
    Call<TVshow.TVshowResult> searchTvshows(@Query("api_key") String apiKey, @Query("query") String recherche);


}
