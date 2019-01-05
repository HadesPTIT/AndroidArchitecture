package com.hades.kotlintrainning.data

import com.hades.kotlintrainning.data.api.response.MovieListResponse
import com.hades.kotlintrainning.data.api.response.TvListResponse
import com.hades.kotlintrainning.data.entity.Movie
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository private constructor() : BaseRepository() {

    private object Holder {
        val INSTANCE = MovieRepository()
    }

    companion object {
        val instance: MovieRepository by lazy {
            Holder.INSTANCE
        }
    }

    fun getMovieList(hashMap: HashMap<String, String>): Single<MovieListResponse> {
        return apiService.getMovieList(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMovieDetail(hashMap: HashMap<String, String>): Single<Movie> {
        return apiService.getMovieDetail(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTvList(hashMap: HashMap<String, String>): Single<TvListResponse> {
        return apiService.getTvList(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Database
     */

    fun insertMovie(movie: Movie): Completable {
        return Completable.fromAction {
            appDatabase.movieDao().insert(movie)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteMovie(movie: Movie) : Completable {
        return Completable.fromAction {
            appDatabase.movieDao().deleteMovie(movie.id)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun isFavoriteMovie(id: Long): Boolean {
        return appDatabase.movieDao().isFavoriteMovie(id) > 0
    }

    fun getFavoriteMovie(): Maybe<List<Movie>> {
        return appDatabase.movieDao().getMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}