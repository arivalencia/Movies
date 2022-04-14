package com.ari.movies.framework.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ari.movies.framework.data.local.dao.PlayingNowMoviesPageDao
import com.ari.movies.framework.data.local.dataconverter.DatesConverter
import com.ari.movies.framework.data.local.dataconverter.MoviesPageConverter
import com.ari.movies.framework.data.local.entities.PlayingNowPageEntity

@Database(
    entities = [PlayingNowPageEntity::class],
    version = 1
)
@TypeConverters(MoviesPageConverter::class, DatesConverter::class)
abstract class PlayingNowMoviesPageDB: RoomDatabase() {
    abstract fun getDao(): PlayingNowMoviesPageDao
}