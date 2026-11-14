package me.ibrahim.moviesapp.compose.data.database

import android.content.Context
import androidx.room.Room

object DatabaseFactory {
    fun create(context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            FavoriteMoviesDb
        ).fallbackToDestructiveMigration().build()
    }
}