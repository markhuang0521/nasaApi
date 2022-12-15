package com.mingdev.nasaapi.api

import com.mingdev.nasaapi.api.model.PlanetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NasaService {
    @GET("planetary/apod")
    suspend fun getRandomPlanets(
        @Query("api_key") apiKey: String = DEMO_KEY,
        @Query("count") count: Int = 2, // default number to return
    ): Response< List<PlanetResponse>>
}

// since this is a public repo I will place the fake api key here to ensure api call will be successful
const val NASA_BASE_URL = "https://api.nasa.gov/"
private const val DEMO_KEY = "6FNzdVEZXIxPFmFwLflk5q3CVD63IZTUiZsntW2O"