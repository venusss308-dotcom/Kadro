package me.ibrahim.moviesapp.compose.presentation.movies_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.ibrahim.moviesapp.compose.domain.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesList(movies: List<Movie>, onMovieClick: (Movie) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { movies.size })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 24.dp),
        pageSpacing = 16.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
    ) { page ->
        val movie = movies[page]
        MovieItem(
            movie = movie,
            onClick = { onMovieClick(movie) }
        )
    }
}
