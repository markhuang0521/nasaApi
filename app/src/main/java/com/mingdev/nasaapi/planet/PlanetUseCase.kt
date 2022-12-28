package com.mingdev.nasaapi.planet

import com.mingdev.nasaapi.api.model.toPlanet
import com.mingdev.nasaapi.planet.model.Planet
import com.mingdev.nasaapi.util.Lce
import javax.inject.Inject

class PlanetUseCase @Inject constructor(private val planetRepository: PlanetRepository) {

    suspend fun getRandomPlanets(): Lce<List<Planet>> = planetRepository.getRandomPlanets().map(
        // here for success and error response we will wrap them in LCE as well as map the success result into domain obj
        successBlock = { result-> Lce.Content(data = result.map {planet-> planet.toPlanet() })},
        errorBlock = {exception-> Lce.Error(exception = exception)}
    )

}