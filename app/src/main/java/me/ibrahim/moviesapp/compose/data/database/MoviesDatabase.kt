package me.ibrahim.moviesapp.compose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}

const val FavoriteMoviesDb = "favoriteMovies.db"