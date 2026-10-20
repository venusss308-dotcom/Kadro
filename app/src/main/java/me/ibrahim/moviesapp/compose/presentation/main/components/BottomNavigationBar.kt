package me.ibrahim.moviesapp.compose.presentation.main.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import me.ibrahim.moviesapp.compose.R
import me.ibrahim.moviesapp.compose.core.FavoriteMoviesRoute
import me.ibrahim.moviesapp.compose.core.MoviesListRoute
import me.ibrahim.moviesapp.compose.core.SearchMoviesRoute
import me.ibrahim.moviesapp.compose.core.SettingsRoute

@Preview(showSystemUi = true)
@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val barContainerColor = colorResource(id = R.color.black3)
    
    // Cores customizadas solicitadas
    val selectedCircleColor = Color(0xFFDCC9A9)
    val selectedIconColor = Color(0xFFB83A2D)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Fundo com a curva personalizada sutil
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            val width = size.width
            val height = size.height
            val curveRadius = 38.dp.toPx()

            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(width / 2f - curveRadius * 1.4f, 0f)
                
                cubicTo(
                    width / 2f - curveRadius * 0.7f, 0f,
                    width / 2f - curveRadius * 0.8f, -curveRadius * 0.85f,
                    width / 2f, -curveRadius * 0.85f
                )
                cubicTo(
                    width / 2f + curveRadius * 0.8f, -curveRadius * 0.85f,
                    width / 2f + curveRadius * 0.7f, 0f,
                    width / 2f + curveRadius * 1.4f, 0f
                )

                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(
                path = path,
                color = barContainerColor,
                style = Fill
            )
        }

        // Ícones distribuídos uniformemente
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Home
            NavItemView(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.Home,
                contentDescription = "Início",
                isSelected = selectedIndex == 0,
                selectedCircleColor = selectedCircleColor,
                selectedIconColor = selectedIconColor,
                onClick = {
                    selectedIndex = 0
                    navController.navigate(MoviesListRoute) {
                        popUpTo<MoviesListRoute> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )

            // 2. Search (Pesquisa)
            NavItemView(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.Search,
                contentDescription = "Pesquisa",
                isSelected = selectedIndex == 1,
                selectedCircleColor = selectedCircleColor,
                selectedIconColor = selectedIconColor,
                onClick = {
                    selectedIndex = 1
                    navController.navigate(SearchMoviesRoute) {
                        popUpTo<MoviesListRoute> { inclusive = false }
                    }
                }
            )

            // Espaço central reservado para o botão flutuante (+)
            Box(modifier = Modifier.weight(1f))

            // 4. Notificações / Favoritos
            NavItemView(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.Notifications,
                contentDescription = "Favoritos",
                isSelected = selectedIndex == 3,
                selectedCircleColor = selectedCircleColor,
                selectedIconColor = selectedIconColor,
                onClick = {
                    selectedIndex = 3
                    navController.navigate(FavoriteMoviesRoute) {
                        popUpTo<MoviesListRoute> { inclusive = false }
                    }
                }
            )

            // 5. Perfil / Configurações
            NavItemView(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.Person,
                contentDescription = "Perfil",
                isSelected = selectedIndex == 4,
                selectedCircleColor = selectedCircleColor,
                selectedIconColor = selectedIconColor,
                onClick = {
                    selectedIndex = 4
                    navController.navigate(SettingsRoute) {
                        popUpTo<MoviesListRoute> { inclusive = false }
                    }
                }
            )
        }

        // Botão Central Flutuante (+)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-10).dp)
        ) {
            FloatingActionButton(
                onClick = {
                    // Ação futura do botão central
                },
                shape = CircleShape,
                containerColor = Color.White,
                contentColor = Color.Black,
                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                modifier = Modifier.size(54.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Adicionar",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun NavItemView(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    isSelected: Boolean,
    selectedCircleColor: Color,
    selectedIconColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(42.dp)
                .then(
                    if (isSelected) {
                        Modifier.background(selectedCircleColor, CircleShape)
                    } else {
                        Modifier
                    }
                )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = if (isSelected) selectedIconColor else Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
