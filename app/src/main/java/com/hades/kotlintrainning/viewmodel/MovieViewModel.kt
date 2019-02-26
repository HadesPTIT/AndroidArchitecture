package com.hades.kotlintrainning.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.map
import android.arch.lifecycle.Transformations.switchMap
import android.arch.paging.PagedList
import com.hades.kotlintrainning.ui.base.BaseViewModel
import com.hades.kotlintrainning.data.MovieRepository
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.data.network.NetworkResource
import com.hades.kotlintrainning.data.network.NetworkState

class MovieViewModel(application: Application) : BaseViewModel(application) {

    private val movieRepository = MovieRepository.instance

    private val isInserted = MutableLiveData<Boolean>()

    val mFavoriteMovies = MutableLiveData<List<Movie>>()

    val movieResult = MutableLiveData<NetworkResource<LiveData<PagedList<Movie>>>>()

    val movies = switchMap(movieResult) { it.data }!!

    val movieNetworkState = switchMap(movieResult) { it.networkState }!!

    val movieRefreshState = switchMap(movieResult) { it.refreshState }!!

    val isShowLoading = map(movieRefreshState) { it == NetworkState.RUNNING }!!

    fun fetchDiscoverMovies() {
        movieRepository.init()
        movieResult.value = movieRepository.fetchDiscoverMovies()
    }

    fun isInFavorite(id: Long): Boolean {
        return movieRepository.isFavoriteMovie(id)
    }

    fun insertMovieToFavorite(movie: Movie) {
        subscribe(movieRepository.insertMovie(movie).subscribe {
            isInserted.postValue(true)
        })
    }

    fun deleteMovieFromFavorite(movie: Movie) {
        subscribe(movieRepository.deleteMovie(movie).subscribe {
            isInserted.postValue(false)
        })
    }

    fun getFavoriteMovies() {
        subscribe(movieRepository.getFavoriteMovie().subscribe {
            mFavoriteMovies.postValue(it)
        })
    }

    fun isInserted(): MutableLiveData<Boolean> {
        return isInserted
    }

    fun retry() {
        val listing = movieResult.value
        listing?.retry?.invoke()
    }

    companion object {
        const val POPULARITY_DESC = "popularity.desc"
        const val VOTE_AVERAGE_DESC = "vote_average.desc"
    }
}