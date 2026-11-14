package me.ibrahim.moviesapp.compose.presentation.movies_detail

import me.ibrahim.moviesapp.compose.domain.Movie

sealed interface MovieDetailActions {
    data object GoBack : MovieDetailActions
    data class MarkFavorite(val movie: Movie) : MovieDetailActions
    data class OnMovieClick(val movie: Movie) : MovieDetailActions
}