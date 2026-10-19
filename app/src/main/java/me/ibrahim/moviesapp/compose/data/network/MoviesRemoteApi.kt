package me.ibrahim.moviesapp.compose.data.network

import me.ibrahim.moviesapp.compose.data.dto.MovieActorsDto
import me.ibrahim.moviesapp.compose.data.dto.MoviesResponseDto
import me.ibrahim.moviesapp.compose.domain.DataError
import me.ibrahim.moviesapp.compose.domain.Result

interface MoviesRemoteApi {

    suspend fun fetchNowPlayingMovies(): Result<MoviesResponseDto, DataError.Remote>
    suspend fun fetchUpcomingMovies(): Result<MoviesResponseDto, DataError.Remote>
    suspend fun fetchMovieActors(movieId: Int): Result<MovieActorsDto, DataError.Remote>
}