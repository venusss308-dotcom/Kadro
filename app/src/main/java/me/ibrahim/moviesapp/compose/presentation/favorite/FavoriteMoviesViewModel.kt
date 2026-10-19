package me.ibrahim.moviesapp.compose.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.ibrahim.moviesapp.compose.domain.MoviesRepository

class FavoriteMoviesViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteMoviesState())
    val state = _state
        .onStart {
            observeFavoriteMovies()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private fun observeFavoriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getFavoriteMovies().collect { movies ->
                _state.update {
                    it.copy(favoriteMovies = movies)
                }
            }
        }
    }
}