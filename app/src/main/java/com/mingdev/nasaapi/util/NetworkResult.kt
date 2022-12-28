package com.mingdev.nasaapi.util

/**
 * A generic class that holds a value or an exception for api response as a wrapper from google
 * https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/data/Result.kt
 */
sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Throwable) : NetworkResult<Nothing>()

    /**
     * A map function that unwraps the Result wrapper and returns a top level object
     * for the success or failure.
     */
    fun <R : Any> map(successBlock: (T) -> R, errorBlock: (Throwable) -> R): R {
        return when (this) {
            is Success -> successBlock(data)
            is Error -> errorBlock(exception)
        }
    }
}

