package com.ari.movies.framework.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ari.movies.framework.data.local.dao.PopularMoviesPageDao
import com.ari.movies.framework.data.local.dataconverter.MoviesPageConverter
import com.ari.movies.framework.data.local.entities.PopularMoviesPageEntity

@Database(
    entities = [PopularMoviesPageEntity::class],
    version = 1
)
@TypeConverters(MoviesPageConverter::class)
abstract class PopularMoviesPageDB: RoomDatabase() {
    abstract fun getDao(): PopularMoviesPageDao
}