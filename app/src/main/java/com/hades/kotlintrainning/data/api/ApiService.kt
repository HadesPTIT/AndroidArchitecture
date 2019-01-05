package com.hades.kotlintrainning.data.api

import com.hades.kotlintrainning.data.api.response.MovieListResponse
import com.hades.kotlintrainning.data.api.response.TvListResponse
import com.hades.kotlintrainning.data.api.response.VideoResponse
import com.hades.kotlintrainning.data.entity.Movie
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("3/discover/movie")
    fun getMovieList(@QueryMap hashMap: HashMap<String, String> = HashMap()): Single<MovieListResponse>

    @GET("3/movie/{movie_id}")
    fun getMovieDetail(@QueryMap hashMap: HashMap<String, String> = HashMap()): Single<Movie>

    @GET("3/discover/tv")
    fun getTvList(@QueryMap hashMap: HashMap<String, String> = HashMap()): Single<TvListResponse>

    /**
     * import kotlinx.coroutines.experimental.Deferred
     * TODO : for exact case : using Deferred<TvListResponse>
     */
//    @GET("3/discover/tv")
//    fun getTvList(@QueryMap hashMap: HashMap<String, String> = HashMap()): Deferred<TvListResponse>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") movieId: Int?, @Query("api_key") apiKey: String): Single<VideoResponse>

}

object ApiParams {
    const val PAGE = "page"
    const val SORT_BY = "sort_by"
    const val API_KEY = "api_key"
}