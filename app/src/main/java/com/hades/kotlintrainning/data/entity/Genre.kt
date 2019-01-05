package com.hades.kotlintrainning.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Genre(val id : Long, val name : String) : Parcelable