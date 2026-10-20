package me.ibrahim.moviesapp.compose.presentation.main.components

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
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
import androidx.compose.foundation.Canvas

@Preview(showSystemUi = true)
@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val barContainerColor = colorResource(id = R.color.black3)
    val greenColor = colorResource(id = R.color.green)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Fundo com a curva personalizada (entalhe para o botão central)
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
        ) {
            val width = size.width
            val height = size.height
            val curveRadius = 42.dp.toPx()

            val path = Path().apply {
                moveTo(0f, 0f)
                // Linha até o início da curva central
                lineTo(width / 2f - curveRadius * 1.5f, 0f)
                
                // Curva suave para cima (o entalhe)
                cubicTo(
                    width / 2f - curveRadius * 0.8f, 0f,
                    width / 2f - curveRadius * 0.9f, -curveRadius * 1.1f,
                    width / 2f, -curveRadius * 1.1f
                )
                cubicTo(
                    width / 2f + curveRadius * 0.9f, -curveRadius * 1.1f,
                    width / 2f + curveRadius * 0.8f, 0f,
                    width / 2f + curveRadius * 1.5f, 0f
                )

                // Continua a barra até o canto direito
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

        // Ícones e Botões da Navegação distribuídos por cima do Canvas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Home
            NavItemView(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.Home,
                label = "Início",
                isSelected = selectedIndex == 0,
                selectedColor = greenColor,
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
                label = "Pesquisa",
                isSelected = selectedIndex == 1,
                selectedColor = greenColor,
                onClick = {
                    selectedIndex = 1
                    navController.navigate(SearchMoviesRoute) {
                        popUpTo<MoviesListRoute> { inclusive = false }
                    }
                }
            )

            // Espaço central reservado para o botão flutuante não ficar em cima dos textos vizinhos
            Box(modifier = Modifier.weight(1f))

            // 4. Notificações / Favoritos
            NavItemView(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.Notifications,
                label = "Favoritos",
                isSelected = selectedIndex == 3,
                selectedColor = greenColor,
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
                label = "Perfil",
                isSelected = selectedIndex == 4,
                selectedColor = greenColor,
                onClick = {
                    selectedIndex = 4
                    navController.navigate(SettingsRoute) {
                        popUpTo<MoviesListRoute> { inclusive = false }
                    }
                }
            )
        }

        // Botão Central Flutuante (+) encaixado perfeitamente na curva
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-14).dp)
        ) {
            FloatingActionButton(
                onClick = {
                    // Ação futura do botão central de destaque
                },
                shape = CircleShape,
                containerColor = Color.White,
                contentColor = Color.Black,
                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                modifier = Modifier.size(58.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Adicionar",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Composable
fun NavItemView(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    selectedColor: Color,
    onClick: () -> Unit
) {
    val tint = if (isSelected) selectedColor else Color.White
    androidx.compose.foundation.layout.Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        IconButton(onClick = onClick, modifier = Modifier.size(32.dp)) {
            Icon(imageVector = icon, contentDescription = label, tint = tint)
        }
        Text(
            text = label,
            color = tint,
            fontSize = androidx.compose.ui.unit.TextUnit(10f, androidx.compose.ui.unit.TextUnitType.Sp)
        )
    }
}
