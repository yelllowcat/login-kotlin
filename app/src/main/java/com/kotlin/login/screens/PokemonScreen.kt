package com.kotlin.login.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.kotlin.login.R
import com.kotlin.login.components.CustomButton
import com.kotlin.login.components.CustomTextField
import com.kotlin.login.models.PokemonSimple
import com.kotlin.login.viewmodel.PokemonViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(
    onBackClick: () -> Unit,
    viewModel: PokemonViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }

    val pokemon by viewModel.pokemon.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    val architectsDaughter = FontFamily(
        Font(R.font.architects_daughter, FontWeight.Normal)
    )

    // Carga pokemon inicial si no hay datos cargados
    LaunchedEffect(Unit) {
        if (pokemon == null && error == null) {
            viewModel.fetchPokemon("pikachu")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pokédex",
                        fontFamily = architectsDaughter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Barra de Búsqueda
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = "Nombre o ID del Pokémon",
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .shadow(
                            elevation = 6.dp,
                            shape = RoundedCornerShape(26.dp),
                            clip = false,
                            ambientColor = Color.Black.copy(alpha = 0.2f),
                            spotColor = Color.Black.copy(alpha = 0.2f)
                        )
                        .background(Color(0xFF5A44EC), RoundedCornerShape(26.dp))
                        .clickable { viewModel.fetchPokemon(searchQuery) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Contenido Principal
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false),
                contentAlignment = Alignment.Center
            ) {
                when {
                    loading -> {
                        CircularProgressIndicator(
                            color = Color(0xFF5A44EC),
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    error != null -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(vertical = 40.dp)
                        ) {
                            Text(
                                text = error ?: "",
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            CustomButton(
                                text = "Reintentar",
                                onClick = { viewModel.fetchPokemon(searchQuery.ifBlank { "pikachu" }) },
                                modifier = Modifier.width(140.dp)
                            )
                        }
                    }
                    pokemon != null -> {
                        PokemonCard(pokemon = pokemon!!)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PokemonCard(pokemon: PokemonSimple) {
    val primaryType = pokemon.types.firstOrNull()?.type?.name ?: "normal"
    val typeColor = getPokemonTypeColor(primaryType)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(32.dp),
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.15f),
                spotColor = Color.Black.copy(alpha = 0.15f)
            ),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            typeColor.copy(alpha = 0.12f),
                            Color.White
                        )
                    )
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Cabecera: Nombre e ID
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = String.format("#%04d", pokemon.id),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Imagen del Sprite
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.White.copy(alpha = 0.6f), RoundedCornerShape(100.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = pokemon.sprites.frontDefault,
                    contentDescription = pokemon.name,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    error = painterResource(id = R.drawable.ic_launcher_foreground)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Lista de Tipos
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                pokemon.types.forEach { typeSlot ->
                    val typeName = typeSlot.type.name
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(getPokemonTypeColor(typeName))
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = typeName.uppercase(Locale.ROOT),
                            color = if (typeName == "electric") Color.Black else Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Estadísticas: Peso y Altura
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${pokemon.weight / 10.0} kg",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "PESO",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }

                Box(
                    modifier = Modifier
                        .height(36.dp)
                        .width(1.dp)
                        .background(Color.LightGray)
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${pokemon.height / 10.0} m",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ALTURA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

fun getPokemonTypeColor(type: String): Color {
    return when (type.lowercase(Locale.ROOT)) {
        "grass" -> Color(0xFF4CAF50)
        "fire" -> Color(0xFFF44336)
        "water" -> Color(0xFF2196F3)
        "electric" -> Color(0xFFFFC107)
        "poison" -> Color(0xFF9C27B0)
        "bug" -> Color(0xFF8BC34A)
        "flying" -> Color(0xFF90CAF9)
        "normal" -> Color(0xFF9E9E9E)
        "ground" -> Color(0xFF8D6E63)
        "fairy" -> Color(0xFFF48FB1)
        "fighting" -> Color(0xFFD84315)
        "psychic" -> Color(0xFFE91E63)
        "rock" -> Color(0xFFBCAAA4)
        "steel" -> Color(0xFFB0BEC5)
        "ice" -> Color(0xFF80DEEA)
        "ghost" -> Color(0xFF673AB7)
        "dragon" -> Color(0xFF3F51B5)
        "dark" -> Color(0xFF424242)
        else -> Color(0xFFE0E0E0)
    }
}
