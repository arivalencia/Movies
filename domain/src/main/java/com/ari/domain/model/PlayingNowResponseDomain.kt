package com.ari.domain.model

import com.ari.data.model.DatesData
import com.ari.data.model.PlayingNowResponseData

data class PlayingNowResponseDomain(
    val dates: DatesDomain,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<MovieDomain>
)

data class DatesDomain(
    val maximum: String,
    val minimum: String
)

fun DatesData.toDomain(): DatesDomain = DatesDomain(maximum = maximum, minimum = minimum)

fun PlayingNowResponseData.toDomain(): PlayingNowResponseDomain = PlayingNowResponseDomain(
    dates = dates.toDomain(),
    page = page,
    totalPages = total_pages,
    totalResults = total_results,
    results = results.map { it.toDomain() }
)