package com.ari.domain.usecases

import com.ari.data.model.PlayingNowResponseData
import com.ari.data.model.Response
import com.ari.data.repository.MoviesRepository
import com.ari.domain.model.PlayingNowResponseDomain
import com.ari.domain.model.ResultDomain
import com.ari.domain.model.toDomain
import javax.inject.Inject

class GetPlayingNowMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(page: Int): ResultDomain<PlayingNowResponseDomain> {
        if (page < 1) {
            return ResultDomain.Error("Page invalid")
        }

        return when (val response: Response<PlayingNowResponseData> = moviesRepository.getPlayingNowMovies(page)) {
            is Response.Success -> {
                moviesRepository.deletePlayingNowMoviesFromDB(page)
                moviesRepository.insertPlayingNowMoviesToDB(response.result)
                ResultDomain.Success(response.result.toDomain())
            }
            is Response.Error -> {
                //ResultDomain.Error(response.error)
                val data: PlayingNowResponseDomain? = moviesRepository.getPlayingNowMoviesFromDB(page)?.toDomain()
                if (data != null) {
                    ResultDomain.Success(data)
                } else {
                    ResultDomain.Error("No data from local database")
                }
            }
        }
    }


}