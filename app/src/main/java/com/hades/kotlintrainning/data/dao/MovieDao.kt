package com.hades.kotlintrainning.data.dao

import android.arch.persistence.room.*
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.data.entity.Tv
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovieList(): Maybe<List<Movie>>

    @Query("SELECT * FROM movie WHERE movie.id = :id")
    fun getMovie(id: String): Maybe<List<Tv>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(list: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: Movie)

    @Query("DELETE FROM movie WHERE movie.id = :id")
    fun deleteMovie(id: String)

    @Query("SELECT count(*) FROM movie WHERE movie.id LIKE :id")
    fun isFavoriteMovie(id : Long) : Int

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Query("SELECT * FROM movie LIMIT :pageSize OFFSET :pageIndex")
    fun getMoviePage(pageSize: Int, pageIndex: Int): Maybe<List<Movie>>

    @Query("SELECT * FROM movie WHERE movie.isFavorite = 1 LIMIT :pageSize OFFSET ((:pageIndex-1)* :pageSize)")
    fun getFavorite(pageSize: Int, pageIndex: Int): Maybe<List<Movie>>

}