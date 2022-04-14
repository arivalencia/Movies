package com.ari.movies.framework.di

import com.ari.data.contracts.MoviesLocalDataSource
import com.ari.data.contracts.MoviesRemoteDataSource
import com.ari.movies.framework.MoviesLocalDataSourceImpl
import com.ari.movies.framework.MoviesRemoteDataSourceImpl
import com.ari.movies.framework.data.local.dao.PlayingNowMoviesPageDao
import com.ari.movies.framework.data.local.dao.PopularMoviesPageDao
import com.ari.movies.framework.data.remote.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideMoviesRemoteDataSource(
        moviesApi: MoviesApi,
        @Named("api_key") apiKey: String
    ): MoviesRemoteDataSource = MoviesRemoteDataSourceImpl(moviesApi, apiKey)

    @Provides
    fun provideMoviesLocalDataSource(
        popularMoviesPageDao: PopularMoviesPageDao,
        playingNowMoviesPageDao: PlayingNowMoviesPageDao
    ): MoviesLocalDataSource = MoviesLocalDataSourceImpl(popularMoviesPageDao, playingNowMoviesPageDao)
}
