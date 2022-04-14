package com.ari.data.model

data class PlayingNowResponseData(
    val dates: DatesData,
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
    val results: List<MovieData>
)

data class DatesData(
    val maximum: String,
    val minimum: String
)