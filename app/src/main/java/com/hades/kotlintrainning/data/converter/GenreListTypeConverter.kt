package com.hades.kotlintrainning.data.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hades.kotlintrainning.data.entity.Genre

class GenreListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson<List<Genre>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Genre>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
