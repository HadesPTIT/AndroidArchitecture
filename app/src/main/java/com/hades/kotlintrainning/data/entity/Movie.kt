package com.hades.kotlintrainning.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.os.Parcelable
import com.hades.kotlintrainning.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val budget: Int? = 0,
    val homepage: String? = "",
    val imdb_id: String? = "",
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val release_date: String? = "",
    val revenue: Int? = 0,
    val runtime: Int? = 0,
    val status: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0,

    var isFavorite: Boolean? = false

) : Parcelable