package com.hades.kotlintrainning.data.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hades.kotlintrainning.data.entity.Video
import kotlinx.android.parcel.Parcelize

@Parcelize
class VideoResponse(@field:SerializedName("id") var id: String?,
                         @field:SerializedName("results") var videos: List<Video>?) : Parcelable