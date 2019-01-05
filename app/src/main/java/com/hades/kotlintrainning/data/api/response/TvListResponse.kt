package com.hades.kotlintrainning.data.api.response

import android.os.Parcelable
import com.hades.kotlintrainning.data.entity.Tv
import kotlinx.android.parcel.Parcelize

@Parcelize
class TvListResponse : BaseListResponse<Tv>(),Parcelable