package com.ari.movies.framework.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ari.movies.framework.data.local.entities.PlayingNowPageEntity

@Dao
interface PlayingNowMoviesPageDao {

    @Query("SELECT * FROM playing_now_movies_page_table WHERE page = :page")
    suspend fun getPlayingNowMoviesPage(page: Int): PlayingNowPageEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayingNowMoviesPage(data: PlayingNowPageEntity)

    @Query("DELETE FROM playing_now_movies_page_table WHERE page = :page")
    suspend fun deletePlayingNowMoviesPage(page: Int)

}