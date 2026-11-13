package me.ibrahim.moviesapp.compose.presentation.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.ibrahim.moviesapp.compose.domain.MoviesRepository
import me.ibrahim.moviesapp.compose.domain.onError
import me.ibrahim.moviesapp.compose.domain.onSuccess
import me.ibrahim.moviesapp.compose.presentation.main.toUiText

class MoviesListViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var nowPlayingMoviesListJob: Job? = null
    private var upcomingMoviesListJob: Job? = null
    private var tvShowsListJob: Job? = null

    private val _state = MutableStateFlow(MoviesListState())
    val state = _state
        .onStart {
            fetchNowPlayingMovies()
            fetchUpcomingMovies()
            fetchTvShows() // Dispara a busca de séries reais ao abrir
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private fun fetchUpcomingMovies() {
        upcomingMoviesListJob?.cancel()
        upcomingMoviesListJob = viewModelScope.launch(Dispatchers.IO) {
            repository.fetchUpcomingMovies().onSuccess { movies ->
                _state.update { it.copy(upcomingMovies = movies, errorMsg = null) }
            }.onError { error ->
                _state.update { it.copy(errorMsg = error.toUiText()) }
            }
        }
    }

    private fun fetchNowPlayingMovies() {
        nowPlayingMoviesListJob?.cancel()
        nowPlayingMoviesListJob = viewModelScope.launch(Dispatchers.IO) {
            repository.fetchNowPlayingMovies().onSuccess { movies ->
                _state.update { it.copy(nowPlayingMovies = movies, errorMsg = null) }
            }.onError { error ->
                _state.update { it.copy(errorMsg = error.toUiText()) }
            }
        }
    }

    private fun fetchTvShows() {
        tvShowsListJob?.cancel()
        tvShowsListJob = viewModelScope.launch(Dispatchers.IO) {
            repository.fetchTvShows().onSuccess { shows ->
                _state.update { it.copy(tvShows = shows, errorMsg = null) }
            }.onError { error ->
                _state.update { it.copy(errorMsg = error.toUiText()) }
            }
        }
    }
}
