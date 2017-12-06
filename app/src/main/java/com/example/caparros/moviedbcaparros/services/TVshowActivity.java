package com.example.caparros.moviedbcaparros.services;

/**
 * Created by Nina on 06/12/2017.
 */
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.caparros.moviedbcaparros.R;
import com.example.caparros.moviedbcaparros.models.Movies;
import com.example.caparros.moviedbcaparros.models.TVshow;
import com.example.caparros.moviedbcaparros.models.TVshows;

import java.util.List;

import static android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM;
import static android.view.MenuItem.SHOW_AS_ACTION_NEVER;

public class TVshowActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MenuItem mItemL, mItemG,mItemD;
    TVshows tvshows;
    String vue;
    final static String EXTRA_VUE="EXTRA_VUE" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tvshow_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.grid) {
            vue="grille";
            setViewGrid(tvshows);
            mItemG.setShowAsAction(SHOW_AS_ACTION_IF_ROOM);
            mItemL.setShowAsAction(SHOW_AS_ACTION_NEVER);
            mItemD.setShowAsAction(SHOW_AS_ACTION_NEVER);
            return true;
        }
        if (id == R.id.list) {
            vue="liste";
            setViewList(tvshows);
            mItemG.setShowAsAction(SHOW_AS_ACTION_NEVER);
            mItemL.setShowAsAction(SHOW_AS_ACTION_IF_ROOM);
            mItemD.setShowAsAction(SHOW_AS_ACTION_NEVER);
            return true;
        }
        if (id == R.id.vue_description) {
            vue="description";
            setViewDescription(tvshows);
            mItemG.setShowAsAction(SHOW_AS_ACTION_NEVER);
            mItemL.setShowAsAction(SHOW_AS_ACTION_NEVER);
            mItemD.setShowAsAction(SHOW_AS_ACTION_IF_ROOM);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) { int id = item.getItemId();

        if (id == R.id.nav_movies) {
            // Handle the camera action
        } else if (id == R.id.nav_tv_shows) {


        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setViewGrid(TVshows tvshows)
    {

        RecyclerView mRvFilms = (RecyclerView) findViewById(R.id.rv_tvshow);


        TVshowAdapter tvAdapter = new TVshowAdapter(this, tvshows, false);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRvFilms.setLayoutManager(manager);

        mRvFilms.setAdapter(tvAdapter);


    }

    public void setViewList(TVshows tvshows)
    {

        RecyclerView mRvFilms = (RecyclerView) findViewById(R.id.rv_tvshow);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvFilms.setLayoutManager(layoutManager);

        TVshowAdapter tvAdapter = new TVshowAdapter(this, tvshows);
        mRvFilms.setAdapter(tvAdapter);


    }
    public void setViewDescription(TVshows tvshows)
    {

        RecyclerView mRvFilms = (RecyclerView) findViewById(R.id.rv_tvshow);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvFilms.setLayoutManager(layoutManager);

       TVshowAdapter tvAdapter = new TVshowAdapter(this, tvshows, false);
        mRvFilms.setAdapter(tvAdapter);


    }
}
