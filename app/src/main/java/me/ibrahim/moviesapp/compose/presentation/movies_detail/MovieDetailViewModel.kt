package me.ibrahim.moviesapp.compose.presentation.movies_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.ibrahim.moviesapp.compose.domain.Movie
import me.ibrahim.moviesapp.compose.domain.MoviesRepository
import me.ibrahim.moviesapp.compose.domain.onError
import me.ibrahim.moviesapp.compose.domain.onSuccess
import me.ibrahim.moviesapp.compose.presentation.main.toUiText

class MovieDetailViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private var markFavoriteJob: Job? = null

    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state
        .onStart {
            fetchActorsList()
            observeFavoriteMoviesId()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private fun observeFavoriteMoviesId() {
        viewModelScope.launch {
            moviesRepository
                .getFavoriteMoviesIds()
                .onEach { ids ->
                    _state.update {
                        it.copy(
                            isFavorite = it.movie.id in ids
                        )
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun onAction(action: MovieDetailActions) {
        when (action) {
            MovieDetailActions.GoBack -> Unit
            is MovieDetailActions.MarkFavorite -> markFavorite(action.movie)
            is MovieDetailActions.OnMovieClick -> {
                _state.update { it.copy(movie = action.movie) }
            }
        }
    }


    private fun fetchActorsList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            moviesRepository.fetchMovieActors(movieId = _state.value.movie.id)
                .onSuccess { actors ->
                    _state.update {
                        it.copy(actors = actors, isLoading = false)
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(isLoading = false, errorMessage = error.toUiText())
                    }
                }
        }
    }

    private fun markFavorite(movie: Movie) {

        markFavoriteJob?.cancel()

        markFavoriteJob = viewModelScope.launch(Dispatchers.IO) {
            if (state.value.isFavorite.not()) {
                moviesRepository.insertFavoriteMovie(movie)
            } else {
                moviesRepository.deleteFavoriteMovie(movieId = movie.id)
            }
        }
    }
}