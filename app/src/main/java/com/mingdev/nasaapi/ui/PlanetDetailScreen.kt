package com.mingdev.nasaapi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mingdev.nasaapi.R
import com.mingdev.nasaapi.planet.PlanetDetailViewModel

@Composable
fun PlanetDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PlanetDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    state.selectedPlanet?.let { planet ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            item {
                AsyncImage(
                    model = planet.hdUrl,
                    fallback = painterResource(R.drawable.ic_launcher_background),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "planet HD image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                )
            }
            item { Text(text = "Title: " + planet.title) }
            item { Text(text = "Version: " + planet.serviceVersion) }
            item { Text(text = "Explanation: \n" + planet.explanation) }

        }
    } ?: Text(text = "error!! unable to get selected planet pls try again")


}