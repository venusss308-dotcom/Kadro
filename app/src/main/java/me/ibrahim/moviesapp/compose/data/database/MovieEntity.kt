package me.ibrahim.moviesapp.compose.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "FavoriteMovie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val backdropPath: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)
