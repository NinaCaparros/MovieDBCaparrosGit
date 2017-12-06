package com.example.caparros.moviedbcaparros.models;

import java.util.List;

/**
 * Created by Nina on 06/12/2017.
 */

public class TVshows{

        public List<TVshow> TVshows = null;

        public List<TVshow> getTVshows(){

            return TVshows;
        }

        public void setTVshows(List<TVshow> TVshows){
            this.TVshows=TVshows;
        }

        public void addTVshow(TVshow tvshow){
            TVshows.add(tvshow);
        }
}
