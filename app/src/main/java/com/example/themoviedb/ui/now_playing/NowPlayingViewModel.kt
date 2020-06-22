package com.example.themoviedb.ui.now_playing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.MovieApi
import com.example.themoviedb.model.NowPlaying
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.themoviedb.model.Result

class NowPlayingViewModel : ViewModel() {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var nowPlaying:MutableLiveData<List<Result>> = MutableLiveData()
    var loadError:MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading
    fun getNowPlaying() : LiveData<List<Result>> = nowPlaying
    fun getLoadError(): LiveData<Boolean> = loadError

    var movieApi = MovieApi()

    fun loadNowPlaying() {
        loading.value = true
       var nowPlayingApiCall = movieApi.getNowPlaying("25bbf11781e6e6277f75651ecbe8c85b")
        nowPlayingApiCall.enqueue(object : Callback<NowPlaying>{
            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
                loading.value = false
                loadError.value = true
            }

            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                var nowPlayingList = response.body()?.results ?: emptyList()
                nowPlaying.value = nowPlayingList
            }

        })
    }

}