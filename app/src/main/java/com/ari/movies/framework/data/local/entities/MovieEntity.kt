package com.ari.movies.framework.data.local.entities

import com.ari.data.model.MovieData

data class MovieEntity(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Double
)

fun MovieEntity.toData(): MovieData = MovieData(
    adult = adult,
    backdrop_path = backdropPath,
    genre_ids = genreIds,
    id = id,
    original_language = originalLanguage,
    original_title = originalTitle,
    overview = overview,
    popularity = popularity,
    poster_path = posterPath,
    release_date = releaseDate,
    title = title,
    video = video,
    vote_average = voteAverage,
    vote_count = voteCount
)

fun MovieData.toEntity(): MovieEntity = MovieEntity(
    adult = adult,
    backdropPath = backdrop_path,
    genreIds = genre_ids,
    id = id,
    originalLanguage = original_language,
    originalTitle = original_title,
    overview = overview,
    popularity = popularity,
    posterPath = poster_path,
    releaseDate = release_date,
    title = title,
    video = video,
    voteAverage = vote_average,
    voteCount = vote_count
)