package com.mingdev.nasaapi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import com.mingdev.nasaapi.planet.PlanetViewModel
import com.mingdev.nasaapi.planet.PlanetViewModel.Action
import com.mingdev.nasaapi.planet.model.toJson
import com.mingdev.nasaapi.util.Lce
import com.mingdev.nasaapi.util.LceSection

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlanetListScreen(
    viewModel: PlanetViewModel = hiltViewModel(),
    navigateToDetail: (planetJson: String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(state.isRefreshing, {
        viewModel.performAction(Action.RefreshList)
    })
    PlanetListSection(state, pullRefreshState, navigateToDetail)


}

@Composable
@OptIn(ExperimentalMaterialApi::class)
internal fun PlanetListSection(
    state: PlanetViewModel.State,
    pullRefreshState: PullRefreshState,
    navigateToDetail: (planetJson: String) -> Unit
) {
    Box(modifier = Modifier
        .pullRefresh(pullRefreshState)
        .fillMaxSize()) {

        LceSection(state = state.planetList,
            loading = { BlockingCircularProgressIndicator(isShowing = true) },
            error = {
                LazyColumn {
                    item {
                        if (!state.isRefreshing)
                            Text(
                                modifier = Modifier.fillMaxSize(),
                                text = (state.planetList as Lce.Error).exception.localizedMessage
                                    ?: "network error please try to refresh again again"
                            )
                    }
                }
            }) { planets ->
            if (!state.isRefreshing) {
                if (planets.isEmpty()) Text(text = "OOPS!! no planets available please refresh again")
                else {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        items(items = planets) { planet ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                                    .clickable { navigateToDetail(planet.toJson()) },
                                elevation = 10.dp
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Date: " + planet.date)

                                    AsyncImage(
                                        model = planet.url,
                                        fallback = painterResource(R.drawable.ic_launcher_background),
                                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                                        contentDescription = "planet image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                    )
                                }

                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(
                state.isRefreshing,
                pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )

        }


    }
}