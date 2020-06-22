package com.example.themoviedb.ui.movie_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.MovieApi
import com.example.themoviedb.model.MovieDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var movieDetail: MutableLiveData<MovieDetail> = MutableLiveData()
    var loadError: MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getMovieDetail() : LiveData<MovieDetail> = movieDetail
    fun getLoadError() : LiveData<Boolean> = loadError

    var movieApi = MovieApi()

    fun loadMovieDetail(movieID: Int, apiKey: String) {
        Log.d("movieID", movieID.toString())
        loading.value = true
        var movieDetailApiCall =  movieApi.getMovieDetail(movieID,apiKey)
        movieDetailApiCall.enqueue(object : Callback<MovieDetail>{
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                loading.value = false
                loadError.value = true
                Log.d("Failure", t.toString())
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                loadError.value = false
                var movieDetailList = response.body()
                movieDetail.value = movieDetailList
                Log.d("Response", response.body().toString())
            }

        })
    }

}