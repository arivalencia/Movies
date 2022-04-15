package com.ari.movies.framework.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ari.movies.framework.data.local.entities.PopularMoviesPageEntity

@Dao
interface PopularMoviesPageDao {

    @Query("SELECT * FROM popular_movies_page_table where page = :page")
    suspend fun getPopularMoviesPage(page: Int): PopularMoviesPageEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMoviesPage(page: PopularMoviesPageEntity)

    @Query("DELETE FROM popular_movies_page_table WHERE page = :page")
    suspend fun deletePopularMoviePage(page: Int)

}