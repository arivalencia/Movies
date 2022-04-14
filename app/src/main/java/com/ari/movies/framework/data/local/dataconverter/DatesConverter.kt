package com.ari.movies.framework.data.local.dataconverter

import androidx.room.TypeConverter
import com.ari.movies.framework.data.local.entities.DatesEntity
import com.google.gson.Gson

class DatesConverter {

    @TypeConverter
    fun fromDatesEntity(dates: DatesEntity): String {
        return Gson().toJson(dates, DatesEntity::class.java)
    }

    @TypeConverter
    fun toMDatesEntity(str: String): DatesEntity {
        return Gson().fromJson<DatesEntity>(str, DatesEntity::class.java)
    }

}