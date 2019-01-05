package com.hades.kotlintrainning.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hades.kotlintrainning.data.converter.*
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

@Parcelize
@Entity(tableName = "movie_detail")
class MovieDetail(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    var page: Long,
    var totalPages: Long,
    @SerializedName(value = "header", alternate = ["title", "name"])
    val header: String,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName(value = "description", alternate = ["overview", "synopsis"])
    var description: String?,
    @SerializedName("release_date")
    var releaseDate: String?,

    @TypeConverters(GenreListTypeConverter::class)
    var genres: List<Genre>? = ArrayList(),
    @SerializedName("videos")
    @TypeConverters(VideoListTypeConverter::class)
    var videos: List<Video>? = ArrayList(),
    @TypeConverters(StringListConverter::class)
    var categoryTypes: List<String>? = ArrayList(),
    @TypeConverters(CrewListTypeConverter::class)
    var crews: List<Crew>? = ArrayList(),
    @TypeConverters(CastListTypeConverter::class)
    var casts: List<Cast>? = ArrayList(),
    @TypeConverters(MovieListTypeConverter::class)
    var similarMovies: List<MovieDetail>? = ArrayList(),
    @SerializedName("runtime")
    var runTime: Long,
    var status: String?
) : Parcelable