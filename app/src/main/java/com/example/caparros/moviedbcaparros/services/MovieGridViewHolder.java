package com.example.caparros.moviedbcaparros.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caparros.moviedbcaparros.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Nina on 06/12/2017.
 */

public class MovieGridViewHolder extends RecyclerView.ViewHolder {

public TextView tv_titre;
public TextView tv_description;
public ImageView image,backdrop;

public MovieGridViewHolder(View itemView) {

        super(itemView);
        image=(ImageView)itemView.findViewById(R.id.image);
        tv_titre=(TextView) itemView.findViewById(R.id.tv_title);
        tv_description=(TextView) itemView.findViewById(R.id.tv_description);
        }

public void setImage(Context context, String lienImage)
        {

        Picasso.with(context)
        .load(lienImage)
        .error(R.mipmap.ic_error)
        .into(image);
        }
public void setBackdrop(Context context, String lienImage)
        {

        Picasso.with(context)
        .load(lienImage)
        .error(R.mipmap.ic_error)
        .into(backdrop);
        }
        }

