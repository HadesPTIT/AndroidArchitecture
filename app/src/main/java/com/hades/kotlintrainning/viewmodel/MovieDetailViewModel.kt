package com.hades.kotlintrainning.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.hades.kotlintrainning.data.MovieRepository
import com.hades.kotlintrainning.data.entity.MovieDetail

import com.hades.kotlintrainning.ui.base.BaseViewModel


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