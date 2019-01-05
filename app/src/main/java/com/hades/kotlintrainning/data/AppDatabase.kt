package com.hades.kotlintrainning.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.support.annotation.VisibleForTesting
import com.hades.kotlintrainning.data.converter.*
import com.hades.kotlintrainning.data.dao.MovieDao
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.data.entity.MovieDetail

@Database(entities = [Movie::class,MovieDetail::class], version = 1, exportSchema = false)
@TypeConverters(
    DateTypeConverter::class,
    GenreListTypeConverter::class,
    CastListTypeConverter::class,
    CrewListTypeConverter::class,
    MovieListTypeConverter::class,
    StringListConverter::class,
    VideoListTypeConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @VisibleForTesting
        const val DATABASE_NAME = "movie.db"

        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE!!
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }


}

