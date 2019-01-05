package com.hades.kotlintrainning.data.api.response

import android.os.Parcelable
import com.hades.kotlintrainning.data.entity.MovieDetail
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieListResponse : BaseListResponse<MovieDetail>(),Parcelable