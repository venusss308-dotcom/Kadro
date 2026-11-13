package me.ibrahim.moviesapp.compose.presentation.movies_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ibrahim.moviesapp.compose.domain.Movie
import me.ibrahim.moviesapp.compose.presentation.movies_list.components.MoviesList
import me.ibrahim.moviesapp.compose.presentation.movies_list.components.TitledMoviesList
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesListViewModel = koinViewModel(),
    onMovieClick: (Movie) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedCategory by remember { mutableStateOf("Filmes") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Barra de pesquisa minimalista no topo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .statusBarsPadding()
                    .height(52.dp)
                    .background(
                        color = Color(0xFF1E1E1E),
                        shape = RoundedCornerShape(26.dp)
                    )
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Procurar títulos",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Pesquisar",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Cabeçalho "Em Alta" com setinha e seletor "Filmes" / "Séries"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Em Alta",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Opções",
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                }

                // Seletor interativo Filmes / Séries
                Text(
                    text = selectedCategory,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            selectedCategory = if (selectedCategory == "Filmes") "Séries" else "Filmes"
                        }
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Lista horizontal de "Em Alta" exibida direto sem loading
            TitledMoviesList(title = "") {
                MoviesList(movies = state.nowPlayingMovies, onMovieClick = onMovieClick)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Seção de Novos Episódios
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Novos Episódios",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            TitledMoviesList(title = "") {
                MoviesList(movies = state.upcomingMovies, onMovieClick = onMovieClick)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
