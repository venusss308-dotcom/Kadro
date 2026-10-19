package me.ibrahim.moviesapp.compose.domain

import kotlinx.coroutines.flow.Flow
import me.ibrahim.moviesapp.compose.data.database.MovieEntity

interface MoviesRepository {

    suspend fun fetchNowPlayingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun fetchUpcomingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun fetchMovieActors(movieId: Int): Result<List<Actor>, DataError.Remote>

    //database operations
    suspend fun insertFavoriteMovie(movie: Movie)
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteMoviesIds(): Flow<List<Int>>
    suspend fun deleteFavoriteMovie(movieId: Int)

}