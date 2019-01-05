package com.hades.kotlintrainning.data.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hades.kotlintrainning.data.entity.MovieDetail

class MovieListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<MovieDetail>? {
        val listType = object : TypeToken<List<MovieDetail>>() {}.type
        return Gson().fromJson<List<MovieDetail>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<MovieDetail>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
