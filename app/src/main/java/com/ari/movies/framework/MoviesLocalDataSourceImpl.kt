package com.ari.movies.framework

import com.ari.data.contracts.MoviesLocalDataSource
import com.ari.data.model.PlayingNowResponseData
import com.ari.data.model.PopularMoviesResponseData
import com.ari.movies.framework.data.local.dao.PlayingNowMoviesPageDao
import com.ari.movies.framework.data.local.dao.PopularMoviesPageDao
import com.ari.movies.framework.data.local.entities.toData
import com.ari.movies.framework.data.local.entities.toEntity
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(
    private val popularMoviesPageDao: PopularMoviesPageDao,
    private val playingNowMoviesPageDao: PlayingNowMoviesPageDao
): MoviesLocalDataSource {

    override suspend fun getPopularMoviesFromDB(page: Int): PopularMoviesResponseData? =
        popularMoviesPageDao.getPopularMoviesPage(page)?.toData()

    override suspend fun deletePopularMoviesFromDB(page: Int) =
        popularMoviesPageDao.deletePopularMoviePage(page)

    override suspend fun insertPopularMoviesToDB(data: PopularMoviesResponseData) =
        popularMoviesPageDao.insertPopularMoviesPage(data.toEntity())

    override suspend fun getPlayingNowMoviesFromDB(page: Int): PlayingNowResponseData? =
        playingNowMoviesPageDao.getPlayingNowMoviesPage(page)?.toData()

    override suspend fun deletePlayingNowMoviesFromDB(page: Int) =
        playingNowMoviesPageDao.deletePlayingNowMoviesPage(page)

    override suspend fun insertPlayingNowMoviesToDB(data: PlayingNowResponseData) =
        playingNowMoviesPageDao.insertPlayingNowMoviesPage(data.toEntity())
}