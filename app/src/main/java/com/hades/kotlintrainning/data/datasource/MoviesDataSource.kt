package com.hades.kotlintrainning.data.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.hades.kotlintrainning.BuildConfig
import com.hades.kotlintrainning.data.api.ApiParams
import com.hades.kotlintrainning.data.api.ApiService
import com.hades.kotlintrainning.data.api.NetworkState
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.viewmodel.MovieViewModel
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executor

class MoviesDataSource(
    private val apiService: ApiService,
    private val schedulerProvider: SchedulerProvider,
    private val retryExecutor: Executor
) : PageKeyedDataSource<Int, Movie>() {

    val networkState = MutableLiveData<NetworkState>()
    val initialLoadingState = MutableLiveData<NetworkState>()
    var retry: (() -> Unit)? = null // retry event
    val compositeDisposable = CompositeDisposable()

    private val hashMap = HashMap<String, String>()

    init {
        hashMap[ApiParams.SORT_BY] = MovieViewModel.POPULARITY_DESC
        hashMap[ApiParams.API_KEY] = BuildConfig.API_KEY
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        initialLoadingState.postValue(NetworkState.RUNNING)
        networkState.postValue(NetworkState.RUNNING)

        hashMap[ApiParams.PAGE] = "1"
        compositeDisposable.add(apiService.getMovieList(hashMap)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                {
                    initialLoadingState.postValue(NetworkState.SUCCESS)
                    networkState.postValue(NetworkState.SUCCESS)
                    retry = null
                    callback.onResult(it.results!!, null, 2)
                }, {
                    retry = { loadInitial(params, callback) }
                }
            )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.RUNNING)
        hashMap[ApiParams.PAGE] = params.key.toString()
        val disposable = apiService.getMovieList(hashMap)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                retry = null
                networkState.postValue(NetworkState.SUCCESS)
                val body = it.results!!
                val nextKey = if (params.key.toInt() == it.totalPages) null else params.key.plus(1)
                callback.onResult(body, nextKey)
            }, {
                retry = {
                    loadAfter(params, callback)
                }
                val error = NetworkState.error(it.message ?: "<Unknown Error>")
                networkState.postValue(error)
            })
        compositeDisposable.add(disposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        // Ignored, since we only append data to init load
    }


    fun retryWhenAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let { retryExecutor.execute { it.invoke() } }
    }

    fun clear() {
        compositeDisposable.clear()
    }


    class Factory(
        private val apiService: ApiService,
        private val schedulerProvider: SchedulerProvider,
        private val retryExecutor: Executor
    ) : DataSource.Factory<Int, Movie>() {

        val sourceLiveData = MutableLiveData<MoviesDataSource>()

        override fun create(): DataSource<Int, Movie> {
            val source = MoviesDataSource(apiService, schedulerProvider, retryExecutor)
            sourceLiveData.postValue(source)
            return source
        }

    }


}