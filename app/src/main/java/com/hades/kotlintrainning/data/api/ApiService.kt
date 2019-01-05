package com.hades.kotlintrainning.data.api

import com.hades.kotlintrainning.data.api.response.*
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.data.entity.MovieDetail
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("3/discover/movie")
    fun getMovieList(@QueryMap hashMap: HashMap<String, String> = HashMap()): Single<MovieResponse>

    @GET("3/movie/{movie_id}")
    fun getMovieDetail(@QueryMap hashMap: HashMap<String, String> = HashMap()): Single<Movie>

    @GET("3/discover/tv")
    fun getTvList(@QueryMap hashMap: HashMap<String, String> = HashMap()): Single<TvListResponse>

    @GET("/3/movie/{movieId}/{api_key}")
    fun fetchMovieDetail(@Path("movieId") movieId: String,@Path("api_key") api_key: String): Observable<MovieDetail>

    @GET("/3/movie/{movieId}/videos")
    fun fetchMovieVideo(@Path("movieId") movieId: String): Observable<VideoResponse>

    @GET("/3/movie/{movieId}/credits")
    fun fetchCastDetail(@Path("movieId") movieId: String): Observable<CreditResponse>

    @GET("/3/movie/{movieId}/similar")
    fun fetchSimilarMovie(@Path("movieId") movieId: String, @Query("page") page: Long): Observable<MovieListResponse>



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
    const val MOVIE_ID= ""
}