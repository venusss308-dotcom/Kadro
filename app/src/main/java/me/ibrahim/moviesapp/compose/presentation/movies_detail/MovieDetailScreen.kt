package me.ibrahim.moviesapp.compose.presentation.movies_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ibrahim.moviesapp.compose.R
import me.ibrahim.moviesapp.compose.domain.Movie
import me.ibrahim.moviesapp.compose.presentation.movies_detail.components.MovieDetailContent
import me.ibrahim.moviesapp.compose.presentation.movies_detail.components.MovieDetailHeader
import me.ibrahim.moviesapp.compose.presentation.movies_detail.components.MovieDetailToolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailScreen(
    movie: Movie,
    viewModel: MovieDetailViewModel = koinViewModel(),
    onBack: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.onAction(MovieDetailActions.OnMovieClick(movie))
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.black1))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            MovieDetailHeader(movie = state.movie, modifier = Modifier.aspectRatio(1f))

            MovieDetailContent(state = state, modifier = Modifier)
        }

        Image(painter = painterResource(id = R.drawable.bg1), contentDescription = null)

        MovieDetailToolbar(
            modifier = Modifier
                .align(Alignment.TopCenter),
            state = state,
            onAction = { action ->
                if (action == MovieDetailActions.GoBack) onBack()
                else viewModel.onAction(action)
            }
        )
    }
}