package com.hades.kotlintrainning.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations.switchMap
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.hades.kotlintrainning.BuildConfig
import com.hades.kotlintrainning.constant.AppConstant
import com.hades.kotlintrainning.data.api.response.*
import com.hades.kotlintrainning.data.datasource.MoviesDataSource
import com.hades.kotlintrainning.data.datasource.SchedulerProvider
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.data.entity.MovieDetail
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MovieRepository private constructor() : BaseRepository() {

    lateinit var schedulerProvider: SchedulerProvider
    lateinit var networkExecutor: Executor

    private object Holder {
        val INSTANCE = MovieRepository()
    }

    fun init() {
        networkExecutor = Executors.newFixedThreadPool(5)
        schedulerProvider = object : SchedulerProvider {
            override fun io(): Scheduler {
                return Schedulers.io()
            }

            override fun ui(): Scheduler {
                return AndroidSchedulers.mainThread()
            }

        }
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

    fun fetchDiscoverMovies(): NetworkResource<LiveData<PagedList<Movie>>> {
        val sourceFactory = MoviesDataSource.Factory(apiService, schedulerProvider, networkExecutor)
        val livePageList = LivePagedListBuilder(sourceFactory, AppConstant.PAGE_SIZE)
            .setFetchExecutor(networkExecutor)
            .build()
        val sourceData = sourceFactory.sourceLiveData

        return NetworkResource(
            data = livePageList,
            networkState = switchMap(sourceData) { it.networkState },
            refreshState = switchMap(sourceData) { it.initialLoadingState },
            refresh = { sourceData.value?.invalidate() },
            retry = { sourceData.value?.retryWhenAllFailed() },
            clear = { sourceData.value?.clear() }
        )
    }


    fun fetchMovieDetail(movieId: Long): Observable<MovieDetail> {
        val id = movieId.toString()
        return Observable.combineLatest(apiService.fetchMovieDetail(id, BuildConfig.API_KEY),
            apiService.fetchMovieVideo(id, BuildConfig.API_KEY),
            apiService.fetchCastDetail(id, BuildConfig.API_KEY),
            apiService.fetchSimilarMovie(id, 1, BuildConfig.API_KEY),
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

    fun deleteMovie(movie: Movie): Completable {
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