package com.example.themoviedb.api

import com.example.themoviedb.model.MovieDetail
import com.example.themoviedb.model.NowPlaying
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {

    @GET("now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String
    ) : Call<NowPlaying>

    @GET("{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetail>
}