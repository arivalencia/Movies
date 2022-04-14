package com.ari.movies.framework.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ari.data.model.DatesData
import com.ari.data.model.PlayingNowResponseData

@Entity(
    tableName = "playing_now_movies_page_table"
)
data class PlayingNowPageEntity(
    @PrimaryKey
    val page: Int,

    val dates: DatesEntity,
    val total_pages: Int,
    val total_results: Int,
    val results: List<MovieEntity>
)

data class DatesEntity(
    val maximum: String,
    val minimum: String
)

fun DatesEntity.toData(): DatesData = DatesData(maximum = maximum, minimum = minimum)
fun DatesData.toEntity(): DatesEntity = DatesEntity(maximum = maximum, minimum = minimum)

fun PlayingNowPageEntity.toData(): PlayingNowResponseData = PlayingNowResponseData(
    dates = dates.toData(),
    page = page,
    total_pages = total_pages,
    total_results = total_results,
    results = results.map { it.toData() }
)

fun PlayingNowResponseData.toEntity(): PlayingNowPageEntity = PlayingNowPageEntity(
    page = page,
    dates = dates.toEntity(),
    total_pages = total_pages,
    total_results = total_results,
    results = results.map { it.toEntity() }
)