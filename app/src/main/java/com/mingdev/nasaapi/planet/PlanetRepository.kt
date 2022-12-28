package com.mingdev.nasaapi.planet

import com.mingdev.nasaapi.api.NasaService
import com.mingdev.nasaapi.api.model.PlanetResponse
import com.mingdev.nasaapi.util.BaseRepo
import com.mingdev.nasaapi.util.NetworkResult
import javax.inject.Inject

class PlanetRepository @Inject constructor(private val nasaService: NasaService) : BaseRepo() {

    suspend fun getRandomPlanets(): NetworkResult<List<PlanetResponse>> =
        safeApiCall { nasaService.getRandomPlanets() }

}