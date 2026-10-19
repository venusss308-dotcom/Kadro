package me.ibrahim.moviesapp.compose.presentation.movies_detail

import me.ibrahim.moviesapp.compose.BuildConfig
import me.ibrahim.moviesapp.compose.domain.Actor
import me.ibrahim.moviesapp.compose.domain.Movie
import me.ibrahim.moviesapp.compose.presentation.main.UiText

data class MovieDetailState(
    val actors: List<Actor> = emptyList(),
    val movie: Movie = Movie(
        title = "The Gardener",
        id = 539972,
        posterPath = "${BuildConfig.POSTER_IMAGES_BASEURL}/5T9WR7vIOnHm6xhVt5zBuPbBgt1.jpg",
        overview = "Sonic, Knuckles, and Tails reunite against a powerful new adversary, Shadow, a mysterious villain with powers unlike anything they have faced before. With their abilities outmatched in every way, Team Sonic must seek out an unlikely alliance in hopes of stopping Shadow and protecting the planet."
    ),
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)
