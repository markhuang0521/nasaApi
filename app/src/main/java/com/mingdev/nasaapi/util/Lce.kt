package com.mingdev.nasaapi.util

import androidx.compose.runtime.Composable

/**
 * LCE (Loading | Content | Error)
 * https://tech.instacart.com/lce-modeling-data-loading-in-rxjava-b798ac98d80
 */
sealed class Lce<out T> {

    object Loading : Lce<Nothing>()
    data class Content<T>(val data: T) : Lce<T>()
    data class Error(val exception: Throwable) : Lce<Nothing>()
    }

/**
 * Generic section that can use the [Lce] info. Will display the corresponding
 * composable depending on the subtype of [state] and will pass the value [T] inside
 * of [state] to the [content] composable.
 */
@Composable
fun <T> LceSection(
    state: Lce<T>,
    loading: @Composable () -> Unit,
    error: @Composable () -> Unit,
    content: @Composable (T) -> Unit
) {
    when (state) {
        is Lce.Content -> content(state.data)
        is Lce.Error -> error()
        Lce.Loading -> loading()
    }
}