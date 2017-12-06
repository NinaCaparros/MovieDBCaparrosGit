package com.example.caparros.moviedbcaparros;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.caparros.moviedbcaparros.models.Movie;
import com.example.caparros.moviedbcaparros.models.Movies;
import com.example.caparros.moviedbcaparros.models.TVshow;
import com.example.caparros.moviedbcaparros.models.TVshows;
import com.example.caparros.moviedbcaparros.services.TVshowAdapter;
import com.example.caparros.moviedbcaparros.services.impl.ApiServiceImpl;
import com.example.caparros.moviedbcaparros.services.MovieAdaptater;
import com.example.caparros.moviedbcaparros.utils.Constantes;
import com.google.gson.Gson;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.R.attr.id;
import static android.R.attr.type;
import static android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM;
import static android.view.MenuItem.SHOW_AS_ACTION_NEVER;
import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String type = "movies";
    ApiServiceImpl apiService = new ApiServiceImpl();
    MenuItem menuGrid, menuList, menuDescription;
    String view="list";
    Movies movies;
    TVshows tvshows;

    public static final String EXTRA_SEARCH="EXTRA_SEARCH";

    @Override
    public void recreate() {
        super.recreate();
        Locale locale = new Locale(Constantes.language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);
    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getPopularMovies();

        view="list";

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        menuList = menu.findItem(R.id.list);
        menuGrid = menu.findItem(R.id.grid);
        menuDescription = menu.findItem(R.id.vue_description);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        // Assumes current activity is the searchable activity
        String pkg = "com.example.caparros.moviedbcaparros";
        String cls = "com.example.caparros.moviedbcaparros.MainActivity";
        ComponentName cn = new ComponentName(pkg,cls);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(cn));
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Called when the user submits the query.
                if(type=="movies")
                {
                    doMySearch(query);
                }
                else if(type=="series")
                {
                    doMyTvSearch(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Called when the query text is changed by the user.
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.grid) {
            view="grid";
            if(type=="movies")
            {
                setViewGrid(movies);


            }
            else
            {
                setViewGridTVshow(tvshows);
            }
            menuGrid.setShowAsAction(SHOW_AS_ACTION_IF_ROOM);
            menuList.setShowAsAction(SHOW_AS_ACTION_NEVER);
            menuDescription.setShowAsAction(SHOW_AS_ACTION_NEVER);
            return true;
        }
        if (id == R.id.list) {
            view="list";
            if(type=="movies")
            {
                setView(movies);

            }
            else
            {
                setViewTV(tvshows);
            }
            menuGrid.setShowAsAction(SHOW_AS_ACTION_NEVER);
            menuList.setShowAsAction(SHOW_AS_ACTION_IF_ROOM);
            menuDescription.setShowAsAction(SHOW_AS_ACTION_NEVER);
            return true;
        }
        if (id == R.id.vue_description) {
            view="poster";
            if(type=="movies")
            {
                setViewPoster(movies);


            }
            else
            {
                setViewPosterTVshow(tvshows);
            }
            menuGrid.setShowAsAction(SHOW_AS_ACTION_NEVER);
            menuList.setShowAsAction(SHOW_AS_ACTION_NEVER);
            menuDescription.setShowAsAction(SHOW_AS_ACTION_IF_ROOM);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movies) {
            type="movies";
            getPopularMovies();
            // Handle the camera action
        } else if (id == R.id.nav_tv_shows) {
            type="series";
            getPopularTvshows();


        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent,0);
            this.recreate();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    private void getPopularMovies() {
        apiService.getPopularMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onSuccess(List<Movie> listMovies) {
                movies = new Movies();
                movies.setMovies(listMovies);
                //if(movies!=null)
                //{
                    if(movies.getMovies().size()!=0)
                    {
                        if(view.contains("list"))
                        {
                            setView(movies);
                        }
                        else if(view.contains("grid"))
                        {
                            setViewGrid(movies);
                        }
                        else if(view.contains("poster"))
                        {
                            setViewPoster(movies);
                        }
                        else
                        {
                            setView(movies);
                        }
                    }
               // }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPopularTvshows(){
        apiService.getPopularTvshows(new ApiServiceImpl.CustomCallBack<TVshow>() {
            @Override
           public void onSuccess(List<TVshow> listTvShow) {
                tvshows=new TVshows();
                tvshows.setTVshows(listTvShow);
                if(tvshows.getTVshows()!=null)
                {
                    if (tvshows.getTVshows().size() != 0)
                    {
                        if (view.contains("list"))
                            setViewListTVshow(tvshows);
                        if (view.contains("grid"))
                            setViewGridTVshow(tvshows);
                        if (view.contains("poster"))
                            setViewPosterTVshow(tvshows);
                    }
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void setView(Movies movies){
        RecyclerView mRvMovie = (RecyclerView)findViewById(R.id.recyclerpopular);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvMovie.setLayoutManager(layoutManager);

        MovieAdaptater movieAdapter = new MovieAdaptater(getApplicationContext(), movies);

        mRvMovie.setAdapter(movieAdapter);
    }

    private void setViewTV(TVshows tvshows){
        RecyclerView mRvMovie = (RecyclerView)findViewById(R.id.recyclerpopular);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvMovie.setLayoutManager(layoutManager);

        TVshowAdapter tvshowAdapter = new TVshowAdapter(getApplicationContext(), tvshows);

        mRvMovie.setAdapter(tvshowAdapter);
    }

    public void doMySearch(String query){
        apiService.searchMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onSuccess(List<Movie> listMovies) {
                Movies movies = new Movies();
                movies.setMovies(listMovies);
                setView(movies);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }, query);
    }

    public void doMyTvSearch(String query){
        apiService.searchTvshows(new ApiServiceImpl.CustomCallBack<TVshow>() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onSuccess(List<TVshow> listTvs) {
                TVshows tvshows = new TVshows();
                tvshows.setTVshows(listTvs);
                Log.d("Film", listTvs.get(0).getTitle());
                setViewTV(tvshows);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }, query);
    }

    public void setViewGrid(Movies movies)
    {

        RecyclerView mRvFilms = (RecyclerView) findViewById(R.id.recyclerpopular);


        MovieAdaptater movieAdapter = new MovieAdaptater(this, movies, false);
        GridLayoutManager manager = new GridLayoutManager(this,4, GridLayoutManager.VERTICAL, false);
        mRvFilms.setLayoutManager(manager);

        mRvFilms.setAdapter(movieAdapter);


    }


    public void setViewPoster(Movies movies)
    {

        RecyclerView mRvFilms = (RecyclerView) findViewById(R.id.recyclerpopular);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvFilms.setLayoutManager(layoutManager);

        MovieAdaptater movieAdapter = new MovieAdaptater(this, movies, false);
        mRvFilms.setAdapter(movieAdapter);


    }

    public void setViewGridTVshow(TVshows tvshows)
    {
        RecyclerView mRvFilms = (RecyclerView) findViewById(R.id.recyclerpopular);

        GridLayoutManager manager = new GridLayoutManager(this,4, GridLayoutManager.VERTICAL, false);
        mRvFilms.setLayoutManager(manager);

        TVshowAdapter tvshowAdapter = new TVshowAdapter(this, tvshows, false);
        mRvFilms.setAdapter(tvshowAdapter);
    }

    public void setViewListTVshow(TVshows tvshows)
    {

        RecyclerView mRvFilms = (RecyclerView) findViewById(R.id.recyclerpopular);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvFilms.setLayoutManager(layoutManager);

        TVshowAdapter tvshowAdapter = new TVshowAdapter(this, tvshows);
        mRvFilms.setAdapter(tvshowAdapter);
    }

    public void setViewPosterTVshow(TVshows tvshows)
    {

        RecyclerView mRvFilms = (RecyclerView) findViewById(R.id.recyclerpopular);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvFilms.setLayoutManager(layoutManager);

        TVshowAdapter tvshowAdapter = new TVshowAdapter(this, tvshows, false);
        mRvFilms.setAdapter(tvshowAdapter);

    }




}
