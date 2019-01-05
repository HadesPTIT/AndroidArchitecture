package com.hades.kotlintrainning.data.api.response

import android.arch.persistence.room.TypeConverters
import android.os.Parcelable
import com.hades.kotlintrainning.data.converter.CastListTypeConverter
import com.hades.kotlintrainning.data.converter.CrewListTypeConverter
import com.hades.kotlintrainning.data.entity.Cast
import com.hades.kotlintrainning.data.entity.Crew
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

@Parcelize
class CreditResponse(
    @TypeConverters(CrewListTypeConverter::class)
    var crew: List<Crew> = ArrayList(),
    @TypeConverters(CastListTypeConverter::class)
    var cast: List<Cast> = ArrayList()
) : Parcelable