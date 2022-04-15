package com.ari.domain.usecases

import com.ari.data.model.PopularMoviesResponseData
import com.ari.data.model.Response
import com.ari.data.repository.MoviesRepository
import com.ari.domain.model.PopularMoviesResponseDomain
import com.ari.domain.model.ResultDomain
import com.ari.domain.model.toDomain
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(page: Int): ResultDomain<PopularMoviesResponseDomain> {
        if (page < 1) {
            return ResultDomain.Error("Page invalid")
        }

        return when (val response: Response<PopularMoviesResponseData> = moviesRepository.getPopularMovies(page)) {
            is Response.Success -> {
                moviesRepository.deletePopularMoviesFromDB(page)
                moviesRepository.insertPopularMoviesToDB(response.result)
                ResultDomain.Success(response.result.toDomain())
            }
            is Response.Error -> {
                //ResultDomain.Error(response.error)
                val data: PopularMoviesResponseDomain? = moviesRepository.getPopularMoviesFromDB(page)?.toDomain()
                if (data != null) {
                    ResultDomain.Success(data)
                } else {
                    ResultDomain.Error("No data from local database")
                }
            }
        }
    }

}
