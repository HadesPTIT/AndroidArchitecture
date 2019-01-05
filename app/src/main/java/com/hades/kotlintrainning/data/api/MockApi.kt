package com.hades.kotlintrainning.data.api

import com.hades.kotlintrainning.data.api.response.MovieResponse
import com.hades.kotlintrainning.data.api.response.TvListResponse
import com.hades.kotlintrainning.data.api.response.VideoResponse
import com.hades.kotlintrainning.data.entity.Movie
import io.reactivex.Single

abstract class MockApi : ApiService {

    override fun getMovieList(hashMap: HashMap<String, String>): Single<MovieResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovieDetail(hashMap: HashMap<String, String>): Single<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTvList(hashMap: HashMap<String, String>): Single<TvListResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovieVideos(movieId: Int?, apiKey: String): Single<VideoResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}