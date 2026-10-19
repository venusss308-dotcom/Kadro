package me.ibrahim.moviesapp.compose.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.ibrahim.moviesapp.compose.data.database.MoviesDao
import me.ibrahim.moviesapp.compose.data.mappers.toActor
import me.ibrahim.moviesapp.compose.data.mappers.toMovie
import me.ibrahim.moviesapp.compose.data.mappers.toMovieEntity
import me.ibrahim.moviesapp.compose.data.network.MoviesRemoteApi
import me.ibrahim.moviesapp.compose.domain.Actor
import me.ibrahim.moviesapp.compose.domain.DataError
import me.ibrahim.moviesapp.compose.domain.Movie
import me.ibrahim.moviesapp.compose.domain.MoviesRepository
import me.ibrahim.moviesapp.compose.domain.Result
import me.ibrahim.moviesapp.compose.domain.map

class MoviesRepositoryImpl(
    private val moviesRemoteApi: MoviesRemoteApi,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override suspend fun fetchNowPlayingMovies(): Result<List<Movie>, DataError.Remote> {
        return moviesRemoteApi.fetchNowPlayingMovies()
            .map { response ->
                response.results?.let { moviesDto ->
                    moviesDto.map { movieDto ->
                        movieDto.toMovie()
                    }
                } ?: emptyList()
            }
    }

    override suspend fun fetchUpcomingMovies(): Result<List<Movie>, DataError.Remote> {
        return moviesRemoteApi.fetchUpcomingMovies()
            .map { response ->
                response.results?.let { moviesDto ->
                    moviesDto.map { movieDto ->
                        movieDto.toMovie()
                    }
                } ?: emptyList()
            }
    }

    override suspend fun fetchMovieActors(movieId: Int): Result<List<Actor>, DataError.Remote> {
        return moviesRemoteApi.fetchMovieActors(movieId = movieId)
            .map { response ->
                response.castList?.let { casts ->
                    casts.map { cast -> cast.toActor() }
                } ?: emptyList()
            }
    }


    //database operations
    override suspend fun insertFavoriteMovie(movie: Movie) {
        moviesDao.upsert(movie.toMovieEntity())
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return moviesDao.getFavoriteMovies().map {
            it.map { movieEntity -> movieEntity.toMovie() }
        }
    }

    override fun getFavoriteMoviesIds(): Flow<List<Int>> {
        return moviesDao.getFavoriteMoviesIds()
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        return moviesDao.deleteFavoriteMovie(movieId = movieId)
    }
}