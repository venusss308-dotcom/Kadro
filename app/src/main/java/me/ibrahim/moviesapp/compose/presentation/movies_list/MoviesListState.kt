package me.ibrahim.moviesapp.compose.presentation.movies_list

import me.ibrahim.moviesapp.compose.domain.Movie
import me.ibrahim.moviesapp.compose.domain.TvShow

data class MoviesListState(
    val isLoading: Boolean = false,
    val nowPlayingMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val tvShows: List<TvShow> = emptyList(), // Lista real de séries
    val errorMsg: String? = null
)
