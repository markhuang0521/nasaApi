package com.mingdev.nasaapi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// CircularProgressIndicator that blocks user input while loading
//https://stackoverflow.com/questions/69211853/how-to-disable-interaction-when-circularprogressindicator-is-active/69222822#69222822
@Composable
fun BlockingCircularProgressIndicator(
    isShowing: Boolean,
    modifier: Modifier = Modifier
) {
    if (isShowing) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {},
                ),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}