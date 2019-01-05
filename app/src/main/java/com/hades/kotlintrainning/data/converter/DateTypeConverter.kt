package com.hades.kotlintrainning.data.converter

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * One of the useful feature of Room is Type Converters.
 * When you have to store in database some custom types, you can use Type Converters.
 * It converts from a unknown type into a known type in terms of database types
 */
class DateTypeConverter {

    @TypeConverter
    fun toDate(timeStamp : Long?) : Date? {
        return if (timeStamp == null) null else Date(timeStamp)
    }

    @TypeConverter
    fun toTimeStamp(date : Date?) : Long? {
        return date?.time
    }



}