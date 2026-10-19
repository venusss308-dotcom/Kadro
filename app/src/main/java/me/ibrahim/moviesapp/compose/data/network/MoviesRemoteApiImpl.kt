package me.ibrahim.moviesapp.compose.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import me.ibrahim.moviesapp.compose.BuildConfig
import me.ibrahim.moviesapp.compose.data.dto.MovieActorsDto
import me.ibrahim.moviesapp.compose.data.dto.MoviesResponseDto
import me.ibrahim.moviesapp.compose.domain.DataError
import me.ibrahim.moviesapp.compose.domain.Result

class MoviesRemoteApiImpl(private val httpClient: HttpClient) : MoviesRemoteApi {

    override suspend fun fetchNowPlayingMovies(): Result<MoviesResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("${BuildConfig.BASE_URL}${RemoteApiEndpoints.NOW_PLAYING}")
        }
    }

    override suspend fun fetchUpcomingMovies(): Result<MoviesResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("${BuildConfig.BASE_URL}${RemoteApiEndpoints.UPCOMING}")
        }
    }

    override suspend fun fetchMovieActors(movieId: Int): Result<MovieActorsDto, DataError.Remote> {
        return safeCall {
            httpClient.get("${BuildConfig.BASE_URL}$movieId/${RemoteApiEndpoints.CREDITS}")
        }
    }

}