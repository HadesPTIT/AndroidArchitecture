package com.hades.kotlintrainning.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.map
import android.arch.lifecycle.Transformations.switchMap
import android.arch.paging.PagedList
import com.hades.kotlintrainning.BuildConfig
import com.hades.kotlintrainning.base.BaseViewModel
import com.hades.kotlintrainning.data.MovieRepository
import com.hades.kotlintrainning.data.NetworkResource
import com.hades.kotlintrainning.data.api.ApiParams
import com.hades.kotlintrainning.data.api.NetworkState
import com.hades.kotlintrainning.data.entity.Movie
import java.util.concurrent.TimeUnit

class MovieViewModel(application: Application) : BaseViewModel(application) {

    private val movieRepository = MovieRepository.instance
    private val mMovies = MutableLiveData<List<Movie>>()

    private val isInserted = MutableLiveData<Boolean>()

    val mFavoriteMovies = MutableLiveData<List<Movie>>()

    // Save scroll position of Recyclerview when it stops or destroy
    var movieScrollPosition = 0

    // Movie
    private var movieResult = MutableLiveData<NetworkResource<LiveData<PagedList<Movie>>>>()
    val movies = switchMap(movieResult) { it.data }!!
    val movieNetworkState = switchMap(movieResult) { it.networkState }!!
    private val movieRefreshState = switchMap(movieResult) { it.refreshState }
    val isShowLoading = map(movieRefreshState) { it == NetworkState.RUNNING }!!


    fun getDiscoverMovies() {
        movieRepository.init()
        movieResult.value = movieRepository.fetchDiscoverMovies()
    }

    fun getListMovie(page: Int) {
        val hashMap = HashMap<String, String>()
        hashMap[ApiParams.SORT_BY] = POPULARITY_DESC
        hashMap[ApiParams.PAGE] = page.toString()
        hashMap[ApiParams.API_KEY]= BuildConfig.API_KEY
        subscribe(
            movieRepository.getMovieList(hashMap)
                .timeout(10, TimeUnit.SECONDS)
                .doOnSubscribe {
                    loadingLiveData.postValue(true)
                }
                .doAfterSuccess {
                    loadingLiveData.postValue(false)
                }
                .subscribe(
                    {
                        it.results?.let { list ->
                            list.forEach { movie ->
                                if (isInFavorite(movie.id.toLong())) {
                                    movie.isFavorite = true
                                }
                            }
                            mMovies.postValue(list)
                        }
                    }, {
                        mMovies.postValue(null)
                    }
                )
        )
    }

    fun getMovies(): MutableLiveData<List<Movie>> {
        return mMovies
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
        subscribe(movieRepository.deleteMovie(movie).subscribe())
    }

    fun getFavoriteMovies() {
        subscribe(movieRepository.getFavoriteMovie().subscribe {
            mFavoriteMovies.postValue(it)
        })
    }

    fun isInserted(): MutableLiveData<Boolean> {
        return isInserted
    }

    companion object {
        const val POPULARITY_DESC = "popularity.desc"
        const val VOTE_AVERAGE_DESC = "vote_average.desc"
    }
}