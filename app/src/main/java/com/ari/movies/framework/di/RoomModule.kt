package com.ari.movies.framework.di

import android.content.Context
import androidx.room.Room
import com.ari.movies.framework.data.local.dao.PlayingNowMoviesPageDao
import com.ari.movies.framework.data.local.dao.PopularMoviesPageDao
import com.ari.movies.framework.data.local.db.PlayingNowMoviesPageDB
import com.ari.movies.framework.data.local.db.PopularMoviesPageDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val POPULAR_MOVIES_PAGE_DB = "PopularMoviesPageDB"
    private const val PLAYING_NOW_MOVIES_PAGE_DB = "PlayingNowMoviesPageDB"

    @Singleton
    @Provides
    fun providePopularMoviesPageDB(@ApplicationContext context: Context): PopularMoviesPageDB = Room
        .databaseBuilder(context, PopularMoviesPageDB::class.java, POPULAR_MOVIES_PAGE_DB)
        .build()

    @Singleton
    @Provides
    fun providePopularMoviesPageDao(popularMoviesPageDB: PopularMoviesPageDB): PopularMoviesPageDao =
        popularMoviesPageDB.getDao()

    @Singleton
    @Provides
    fun providePlayingNowMoviesPageDB(@ApplicationContext context: Context): PlayingNowMoviesPageDB = Room
        .databaseBuilder(context, PlayingNowMoviesPageDB::class.java, PLAYING_NOW_MOVIES_PAGE_DB)
        .build()

    @Singleton
    @Provides
    fun providePlayingNowMoviesPageDao(playingNowMoviesPageDB: PlayingNowMoviesPageDB): PlayingNowMoviesPageDao =
        playingNowMoviesPageDB.getDao()

}