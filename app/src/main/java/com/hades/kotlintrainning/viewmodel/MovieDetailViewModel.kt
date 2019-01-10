package com.hades.kotlintrainning.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import com.hades.kotlintrainning.BuildConfig
import com.hades.kotlintrainning.base.BaseViewModel
import com.hades.kotlintrainning.data.MovieRepository
import com.hades.kotlintrainning.data.api.ApiParams
import com.hades.kotlintrainning.data.entity.MovieDetail
import com.hades.kotlintrainning.data.entity.Video
import com.hades.kotlintrainning.utils.Navigation
import com.hades.kotlintrainning.youtube.YoutubePlayerActivity
import io.reactivex.Observable

class MovieDetailViewModel(application: Application) : BaseViewModel(application) {

    private val movieRepository = MovieRepository.instance

    private val movieDetailLiveData = MutableLiveData<MovieDetail>()

    fun fetchMovieDetail(movieId: Long) {

        subscribe(movieRepository.fetchMovieDetail(movieId)
            .doOnSubscribe {
                loadingLiveData.postValue(true)
            }
            .subscribe({
                loadingLiveData.postValue(false)
                movieDetailLiveData.postValue(it)
            }, {
            })
        )
    }

    fun getMovieDetailLiveData(): MutableLiveData<MovieDetail> {
        return movieDetailLiveData
    }

}