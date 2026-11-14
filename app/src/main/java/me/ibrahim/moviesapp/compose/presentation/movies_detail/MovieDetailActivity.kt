package me.ibrahim.moviesapp.compose.presentation.movies_detail

import androidx.compose.runtime.Composable
import kotlinx.serialization.json.Json
import me.ibrahim.moviesapp.compose.core.BaseActivity
import me.ibrahim.moviesapp.compose.domain.Movie

class MovieDetailActivity : BaseActivity() {
    @Composable
    override fun InitView() {
        val movieStr = intent.getStringExtra("movie")
        movieStr?.let {
            val movie = Json.decodeFromString<Movie>(it)
            MovieDetailScreen(
                movie = movie,
                onBack = { finish() })
        }
    }
}