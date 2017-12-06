package com.example.caparros.moviedbcaparros.services.impl;

import android.media.tv.TvContentRating;
import android.provider.MediaStore;

import com.example.caparros.moviedbcaparros.models.Movie;
import com.example.caparros.moviedbcaparros.models.TVshow;
import com.example.caparros.moviedbcaparros.models.TVshows;
import com.example.caparros.moviedbcaparros.services.ApiService;
import com.example.caparros.moviedbcaparros.utils.Constantes;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.caparros.moviedbcaparros.utils.Constantes.*;

/**
 * Created by Nina on 15/11/2017.
 */


public class ApiServiceImpl{
    public static ApiService getMovieApiService() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return restAdapter.create(ApiService.class);
    }


    public void getPopularMovies(final CustomCallBack<Movie> customCallBack) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.getPopularMovies(API_KEY).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les films populaires");
            }
        });
    }


        public interface CustomCallBack<T> {
            void onSuccess(List<T> movies);

            void onError(String message);
        }

    public void searchMovies(final CustomCallBack<Movie> customCallBack, String recherche){
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.searchMovies(API_KEY, recherche).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("La recherche n'a donné aucun résultat");
            }
        });
    }

    public void getNowPlayingMovies(final CustomCallBack<Movie> customCallBack, String language) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.getNowPlayingMovies(API_KEY,language).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupÃ©rer les films populaires");
            }
        });
    }

    public void getTopRatedMovies(final CustomCallBack<Movie> customCallBack,String language) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.getTopRatedMovies(API_KEY,language).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupÃ©rer les films populaires");
            }
        });
    }

    public void getPopularTvshows(final CustomCallBack<TVshow> customCallBack) {
        final List<TVshow> tvshow = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.getPopularTvshows(API_KEY).enqueue(new Callback<TVshow.TVshowResult>() {
            @Override
            public void onResponse(Call<TVshow.TVshowResult> call, Response<TVshow.TVshowResult> response) {

                TVshow.TVshowResult tvResult = response.body();

                if (tvResult != null) {
                    for (TVshow tv : tvResult.getResults()) {
                        if (tv.getBackdrop() != null && tv.getPoster() != null) {
                            tvshow.add(tv);
                        }
                    }
                }
                customCallBack.onSuccess(tvshow);
            }

            @Override
            public void onFailure(Call<TVshow.TVshowResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les séries populaires");
            }
        });

    }




    public void searchTvshows(final CustomCallBack<TVshow> customCallBack, String query){
        final List<TVshow> tvshows = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.searchTvshows(API_KEY, query).enqueue(new Callback<TVshow.TVshowResult>() {
            @Override
            public void onResponse(Call<TVshow.TVshowResult> call, Response<TVshow.TVshowResult> response) {

                TVshow.TVshowResult tvResult = response.body();

                if (tvResult != null) {
                    for (TVshow tvshow : tvResult.getResults()) {
                        if (tvshow.getBackdrop() != null && tvshow.getPoster() != null) {
                            tvshows.add(tvshow);
                        }
                    }
                }
                customCallBack.onSuccess(tvshows);
            }

            @Override
            public void onFailure(Call<TVshow.TVshowResult> call, Throwable t) {
                customCallBack.onError("La recherche n'a donné aucun résultat");
            }
        });
    }




}


