package com.ari.movies.framework.data.local.dataconverter

import androidx.room.TypeConverter
import com.ari.movies.framework.data.local.entities.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MoviesPageConverter {

    @TypeConverter
    fun fromMovieEntityList(list: List<MovieEntity>): String? {
        if (list == null) {
            return null
        }
        val type: Type = object : TypeToken<List<MovieEntity>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toMovieEntityList(str: String?): List<MovieEntity> {
        if (str == null) {
            return emptyList()
        }
        val type: Type = object : TypeToken<List<MovieEntity>>() {}.type
        return Gson().fromJson<List<MovieEntity>>(str, type)
    }
}