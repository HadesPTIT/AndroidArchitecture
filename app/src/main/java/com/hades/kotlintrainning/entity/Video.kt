package com.hades.kotlintrainning.entity

import com.google.gson.annotations.SerializedName

class Video(@field:SerializedName("id") var id: String?,
                 @field:SerializedName("key") var key: String?,
                 @field:SerializedName("name") var name: String?,
                 @field:SerializedName("site") var site: String?,
                 @field:SerializedName("size") var size: Int?,
                 @field:SerializedName("type") var type: String?)