package com.hades.kotlintrainning.data.api.response

import com.google.gson.annotations.SerializedName
import com.hades.kotlintrainning.entity.Video

data class VideoResponse(@field:SerializedName("id") var id: String?,
                         @field:SerializedName("results") var videos: List<Video>?)