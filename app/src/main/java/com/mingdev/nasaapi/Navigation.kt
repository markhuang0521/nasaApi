package com.mingdev.nasaapi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mingdev.nasaapi.NavRoute.PlanetList
import com.mingdev.nasaapi.NavRoute.PlantDetail
import com.mingdev.nasaapi.ui.PlanetDetailScreen
import com.mingdev.nasaapi.ui.PlanetListScreen


@Composable
fun NasaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = PlanetList.navRoute,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(PlanetList.navRoute) {
            // here is where the json string is used as the unique identifier to navigate to the detail screen
            PlanetListScreen(navigateToDetail = { planetJson ->
                navController.navigateToPlanetDetail(
                    planetJson
                )
            })
        }
        composable(
            PlantDetail.navRoute,
            arguments = listOf(navArgument(PlantDetail.navArg) { type = NavType.StringType })
        ) {
            NavToolbar(
                title = "Planet Detail",
                navigateOnClick = { navController.popBackStack() }) {
                PlanetDetailScreen()
            }


        }
    }
}

private fun NavHostController.navigateToPlanetDetail(planetJson: String) {
    this.navigate("planet_detail/$planetJson")
}


enum class NavRoute(val navRoute: String, val navArg: String = "") {
    PlanetList("planet_list"),
    PlantDetail("planet_detail/{planet}", "planet")
}


