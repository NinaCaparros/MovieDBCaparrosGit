package com.example.caparros.moviedbcaparros.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caparros.moviedbcaparros.R;
import com.example.caparros.moviedbcaparros.SingleMovieActivity;
import com.example.caparros.moviedbcaparros.SingleTvshowActivity;
import com.example.caparros.moviedbcaparros.models.Movie;
import com.example.caparros.moviedbcaparros.models.Movies;
import com.example.caparros.moviedbcaparros.models.TVshow;
import com.example.caparros.moviedbcaparros.models.TVshows;
import com.squareup.picasso.Picasso;

/**
 * Created by Nina on 06/12/2017.
 */

public class TVshowAdapter extends RecyclerView.Adapter<TVshowViewHolder>{


    private Context mContext;
    private TVshows mTvshows;
    private boolean mAll;

    public TVshowAdapter(Context context, TVshows tvshows) {
        mContext = context;
        mTvshows=tvshows;
        mAll=true;
    }

    public TVshowAdapter(Context context, TVshow tvshow){
        mContext=context;
        mTvshows.addTVshow(tvshow);
        mAll=true;

    }

    public TVshowAdapter(Context context, TVshows tvshows, boolean all) {
        mContext = context;
        mTvshows=tvshows;
        mAll=all;

    }

    public TVshowAdapter(Context context, TVshow tvshow, boolean all){
        mContext=context;
        mTvshows.addTVshow(tvshow);
        mAll=all;

    }

    @Override
    public TVshowViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_user_view, viewGroup, false);

        return new TVshowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TVshowViewHolder holder, int position){

        final TVshow tvshow = mTvshows.getTVshows().get(position);

        View view = holder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SingleTvshowActivity.class);
                Bundle extras = new Bundle();
                extras.putString(SingleTvshowActivity.EXTRA_TV_TITLE,tvshow.getTitle());
                extras.putString(SingleTvshowActivity.EXTRA_TV_RESUME,tvshow.getDescription());
                extras.putString(SingleTvshowActivity.EXTRA_TV_IMAGE,tvshow.getPoster());
                intent.putExtras(extras);
                view.getContext().startActivity(intent);
            }
        });
        if(mAll)
        {

            holder.tv_title.setText(String.valueOf(tvshow.getTitle()));
            holder.tv_description.setText(String.valueOf(tvshow.getDescription()));
        }
        Picasso.with(mContext).load(tvshow.getPoster()).into(holder.iv_poster);


    }
    @Override
    public int getItemCount(){
        return mTvshows.getTVshows().size();

    }


}
