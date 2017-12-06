package com.example.caparros.moviedbcaparros;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.example.caparros.moviedbcaparros.R;
import com.example.caparros.moviedbcaparros.utils.Constantes;

import java.util.Locale;


/**
 * Created by Nina on 06/12/2017.
 */

public class SettingsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioGroup rdgpLangue,rdgpImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);rdgpLangue=(RadioGroup)findViewById(R.id.RGLangue);
        if(Constantes.language.contains("fr")) {
            rdgpLangue.check(R.id.RBFr);
        }
        if(Constantes.language.contains("en")) {
            rdgpLangue.check(R.id.RBEn);
        }

        rdgpImage=(RadioGroup)findViewById(R.id.RGQImage);
        if(Constantes.qualityI.contains("w780")) {
            rdgpImage.check(R.id.RbMAx);
        }
        if(Constantes.qualityI.contains("w500")) {
            rdgpImage.check(R.id.RbMoy);
        }
        if(Constantes.qualityI.contains("w300")) {
            rdgpImage.check(R.id.RbMin);
        }
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movies) {
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_tv_shows) {
            finish();

        } else if (id == R.id.nav_favorites) {
        } else if (id == R.id.nav_settings) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


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
        if(Constantes.language.contains("fr")) {
            rdgpLangue.check(R.id.RBFr);
        }
        if(Constantes.language.contains("en")) {
            rdgpLangue.check(R.id.RBEn);
        }

        if(Constantes.qualityI.contains("780")) {
            rdgpImage.check(R.id.RbMAx);
        }
        if(Constantes.qualityI.contains("w500")) {
            rdgpImage.check(R.id.RbMoy);
        }
        if(Constantes.qualityI.contains("w300")) {
            rdgpImage.check(R.id.RbMin);
        }
    }



    @Override
    public void finish() {

        super.finish();

    }

    public void francais(View v)
    {
        Constantes.language="fr";
        this.recreate();
    }
    public void anglais(View v)
    {
        Constantes.language="en";
        this.recreate();
    }

    public void max(View v)
    {
        Constantes.qualityI="w780";
        Constantes.qualityB="w1280";
    }
    public void moyen(View v)
    {
        Constantes.qualityI="w500";
        Constantes.qualityB="w780";
    }

    public void min(View v)
    {
        Constantes.qualityI="w300";
        Constantes.qualityB="w500";
    }


}



