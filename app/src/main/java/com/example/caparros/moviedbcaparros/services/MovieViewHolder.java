package com.example.caparros.moviedbcaparros.services;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caparros.moviedbcaparros.R;

/**
 * Created by Nina on 08/11/2017.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder{
    TextView tv_title;
    TextView tv_description;
    ImageView iv_poster;

    public MovieViewHolder(View itemViewUser, boolean all){
        super(itemViewUser);
        if(all)
        {
            tv_title=(TextView)itemView.findViewById(R.id.tv_title);

            tv_description=(TextView)itemView.findViewById(R.id.tv_description);

        }
        iv_poster=(ImageView)itemView.findViewById(R.id.imageView);
    }

}
