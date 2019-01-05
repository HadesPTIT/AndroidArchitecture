package com.hades.kotlintrainning.data.api.response

import android.os.Parcelable
import com.hades.kotlintrainning.data.entity.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieResponse : BaseListResponse<Movie>(),Parcelable