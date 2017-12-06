package com.example.caparros.moviedbcaparros;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.caparros.moviedbcaparros.SingleMovieActivity.EXTRA_MOVIE_RESUME;

/**
 * Created by Nina on 06/12/2017.
 */

public class SingleTvshowActivity extends AppCompatActivity {
    public static String EXTRA_TV_TITLE ="EXTRA_MOVIE_TITLE";
    public static String EXTRA_TV_RESUME ="EXTRA_MOVIE_RESUME";
    public static String EXTRA_TV_IMAGE ="EXTRA_MOVIE_IMAGE";
    String fileMovieFav="fileMovieFav";
    TextView singleTvTitle;
    TextView singleTvResume;
    ImageView singleTvImage;
    ImageView butFav;
    Button sharingButton;
    public String tvTitle;
    public String tvResume;
    public String tvImage;


    Context mContext;
    String  listTitleMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_tv_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        tvTitle = extras.getString(EXTRA_TV_TITLE);
        tvResume = extras.getString(EXTRA_TV_RESUME);
        tvImage = extras.getString(EXTRA_TV_IMAGE);
        setView(tvTitle,tvResume,tvImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSharedPreferences(tvTitle);
                setStarFav(listTitleMovie,tvTitle);


            }
        });

        FloatingActionButton fabshare = (FloatingActionButton) findViewById(R.id.fabshare);
        Log.d("log",fabshare.toString());
        fabshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareTVshow(tvTitle,tvResume,tvImage);


            }
        });
    }
    public void setView(String movieTitle,String movieResume,String movieImage){


        singleTvTitle = (TextView) this.findViewById(R.id.single_tv_title);
        singleTvResume = (TextView) this.findViewById(R.id.single_tv_resume);
        singleTvImage= (ImageView) this.findViewById(R.id.single_tv_poster);
        butFav=(ImageView) this.findViewById(R.id.fab);
        singleTvTitle.setText(movieTitle);
        singleTvResume.setText(movieResume);
        Picasso.with(mContext).load(movieImage).into(singleTvImage);

        SharedPreferences sharedPref = mContext.getSharedPreferences("fileFav",Context.MODE_PRIVATE);
        String  listTitleMovie= sharedPref.getString("fileMovieFav", "test");
        setStarFav(listTitleMovie,movieTitle);

    }


    public void setStarFav(String listTitleMovieFav, String movieTitle){
        String[] listTitleMovieCut = listTitleMovieFav.split(";");
        boolean testFav=false;
        for (String m: listTitleMovieCut){
            if(m.equals(movieTitle)) {
                butFav.setImageResource(R.drawable.ic_favorite);
                testFav = true;
            }
        }
        if(!testFav){
            butFav.setImageResource(R.mipmap.ic_favorite_border);
        }
    }


    public void updateSharedPreferences(String movieTitle){

        SharedPreferences sharedPref = mContext.getSharedPreferences("fileFav",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        ArrayList<String> arrayListTitleMovieCut = new ArrayList<String>();
        boolean testMovieFav= false;
        String  listTitleMovieFav= sharedPref.getString("fileMovieFav", "test");
        String[] listTitleMovieCut = listTitleMovieFav.split(";");
        for (String m: listTitleMovieCut){

            if(m.equals(movieTitle)) {
                testMovieFav = true;
            }else{
                arrayListTitleMovieCut.add(m);
            }
        }
        if(!testMovieFav){
            arrayListTitleMovieCut.add(movieTitle);
        }
        listTitleMovie="";
        for(String m : arrayListTitleMovieCut){
            if(!m.equals("")) {
                listTitleMovie += m + ";";
            }
        }
        editor.putString("fileMovieFav", listTitleMovie);
        editor.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_drawer, menu);
        return true;
    }
    public void shareTVshow(String title, String resume, String image){
        Intent sharingIntent= new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Je te conseille de regarder : " + title + ". Résumé : " + resume + " " + image);
        startActivity(sharingIntent);
    }

}
