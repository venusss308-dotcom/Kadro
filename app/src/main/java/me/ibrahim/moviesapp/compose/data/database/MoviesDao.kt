package me.ibrahim.moviesapp.compose.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface MoviesDao {

    @Upsert
    suspend fun upsert(movieEntity: MovieEntity)

    @Query("SELECT * FROM FavoriteMovie")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT id FROM FavoriteMovie")
    fun getFavoriteMoviesIds(): Flow<List<Int>>

    @Query("DELETE FROM FavoriteMovie WHERE id = :movieId")
    suspend fun deleteFavoriteMovie(movieId: Int)
}