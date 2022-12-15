package com.mingdev.nasaapi.planet.model


import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import java.net.URLDecoder
import java.net.URLEncoder

@JsonClass(generateAdapter = true)
data class Planet(
    val copyright: String?,
    val date: String,
    val explanation: String,
    val hdUrl: String?,
    val mediaType: String,
    val serviceVersion: String,
    val title: String,
    val url: String?
)

// using moshi adapter/codegen to transform obj into json string,
// the URLEncoder/decoder is needed since the class contains url
fun Planet.toJson(): String {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(Planet::class.java).lenient()
    return URLEncoder.encode(jsonAdapter.toJson(this), "utf-8")
}

fun String.toPlanet(): Planet? {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(Planet::class.java).lenient()
    return jsonAdapter.fromJson(URLDecoder.decode(this, "utf-8"))
}