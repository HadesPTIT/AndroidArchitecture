package com.hades.kotlintrainning.data

import com.hades.kotlintrainning.BuildConfig
import com.hades.kotlintrainning.data.api.response.*
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.data.entity.MovieDetail
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function4
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

    fun getMovieList(hashMap: HashMap<String, String>): Single<MovieResponse> {
        return apiService.getMovieList(hashMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchMovieDetail(movieId : Long) : Observable<MovieDetail> {
        val id = movieId.toString()
        return Observable.combineLatest(apiService.fetchMovieDetail(id,BuildConfig.API_KEY),
            apiService.fetchMovieVideo(id,BuildConfig.API_KEY),
            apiService.fetchCastDetail(id,BuildConfig.API_KEY),
            apiService.fetchSimilarMovie(id, 1,BuildConfig.API_KEY),
            Function4 { movieEntity: MovieDetail,
                        videoResponse: VideoResponse,
                        creditResponse: CreditResponse,
                        movieApiResponse: MovieListResponse ->
                movieEntity.videos = videoResponse.videos
                movieEntity.crews = creditResponse.crew
                movieEntity.casts = creditResponse.cast
                movieEntity.similarMovies = movieApiResponse.results
                return@Function4 movieEntity
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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