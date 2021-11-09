package com.macamps.harencycomposedemo.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun FeaturedCircularProgressIndicator() {
    CircularProgressIndicator(
        progress = 0.8f,
        modifier = Modifier.padding(8.dp),
        color = Color.Green,
        strokeWidth = 2.dp
    )
}