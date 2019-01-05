package com.hades.kotlintrainning.data.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hades.kotlintrainning.data.entity.Crew

class CrewListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<Crew>? {
        val listType = object : TypeToken<List<Crew>>() {}.type
        return Gson().fromJson<List<Crew>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Crew>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
