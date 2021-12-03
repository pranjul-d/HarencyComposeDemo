package com.macamps.harencycomposedemo.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//definition
@Composable
fun ColumnFor(
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Column (modifier = modifier) {
        content()
    }
}
