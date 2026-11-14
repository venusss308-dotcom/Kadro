package me.ibrahim.moviesapp.compose.core

import kotlinx.serialization.Serializable
import me.ibrahim.moviesapp.compose.domain.Movie

@Serializable
object MoviesListRoute

@Serializable
data class MovieDetailRoute(val movie: Movie)

@Serializable
object FavoriteMoviesRoute

@Serializable
object SearchMoviesRoute

@Serializable
object SettingsRoute