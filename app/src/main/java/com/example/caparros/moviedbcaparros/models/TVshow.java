package com.example.caparros.moviedbcaparros.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.caparros.moviedbcaparros.utils.Constantes;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nina on 06/12/2017.
 */

public class TVshow implements Parcelable{

        @SerializedName("name")
        private String title;
        @SerializedName("poster_path")
        private String poster;
        @SerializedName("overview")
        private String description;
        @SerializedName("backdrop_path")
        private String backdrop;
        @SerializedName("vote_average")
        private String rating;
        @SerializedName("first_air_date")
        private String firstAirDate;

        @SerializedName("id")
        private int id;


        public TVshow() {
            super();
        }

        protected TVshow(Parcel in) {
            title = in.readString();
            poster = in.readString();
            id = in.readInt();
            description = in.readString();
            rating = in.readString();
            backdrop = in.readString();
            firstAirDate = in.readString();
        }

        public void transformDate()
        {
            String date=firstAirDate.substring(8,10)+"/"+firstAirDate.substring(5,7)+"/"+firstAirDate.substring(0,4);
            firstAirDate=date;
        }

        public static final Parcelable.Creator<TVshow> CREATOR = new Parcelable.Creator<TVshow>() {
            @Override
            public TVshow createFromParcel(Parcel in) {
                return new TVshow(in);
            }

            @Override
            public TVshow[] newArray(int size) {
                return new TVshow[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster() {
            if (poster != null) {
                return "http://image.tmdb.org/t/p/"+ Constantes.qualityI+"/"+ poster;
            }
            return null;
        }

        public String getReleaseDate() {
            if(firstAirDate.contains("-"))
                transformDate();
            return firstAirDate;
        }

        public void setReleaseDate(String firstAirDate) {
            this.firstAirDate = firstAirDate;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getBackdrop() {
            if (backdrop != null) {
                return "http://image.tmdb.org/t/p/"+ Constantes.qualityB+"/"+ backdrop;
            }
            return null;
        }

        public void setBackdrop(String backdrop) {
            this.backdrop = backdrop;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(title);
            parcel.writeString(poster);
            parcel.writeString(description);
            parcel.writeString(rating);
            parcel.writeInt(id);
            parcel.writeString(backdrop);
            parcel.writeString(firstAirDate);

        }

        public static class TVshowResult {
            private List<TVshow> results;

            public List<TVshow> getResults() {
                return results;
            }
        }
    }


