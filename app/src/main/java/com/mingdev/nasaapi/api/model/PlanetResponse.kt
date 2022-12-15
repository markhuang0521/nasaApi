package com.mingdev.nasaapi.api.model

import com.mingdev.nasaapi.planet.model.Planet
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetResponse(
    val copyright: String?,
    val date: String,
    val explanation: String,
    @Json(name = "hdurl") val hdUrl: String?,
    @Json(name = "media_type")  val mediaType: String,
    @Json(name = "service_version") val serviceVersion: String,
    val title: String,
    val url: String?
)

// backend model to domain class
fun PlanetResponse.toPlanet(): Planet = Planet(
    copyright = copyright,
    date=date,
    explanation=explanation,
    hdUrl=hdUrl,
    mediaType = mediaType,
    serviceVersion=serviceVersion,
    title=title,
    url=url

)